/**
 * This file is part of lib-ephemeris-jeanmeeus.
 *
 * lib-ephemeris-jeanmeeus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * lib-ephemeris-jeanmeeus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with lib-ephemeris-jeanmeeus.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * @programmed by: Andi Hasan A
 * @github: https://github.com/hasanelfalakiy
 * 
 *
 */
 
package com.andihasan7.lib.ephemeris.jeanmeeus.sunposition

import com.andihasan7.lib.ephemeris.jeanmeeus.correction.Correction
import com.andihasan7.lib.ephemeris.jeanmeeus.earthposition.EarthPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DistanceType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.SunAltType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.DeltaT
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.Nutation
import kotlin.mod
import kotlin.math.*

object SunPosition {
	
	// Sun Geocentric Coordinate
    
    /**
    * Sun True Geocentric Longitude FK5 System default in degree, theta
    *
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sunTrueGeocentricLongitude FK5 System degree or radian
    */
    fun sunTrueGeocentricLongitude(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val earthHeliocentricLongitude = EarthPosition.earthHeliocentricLongitude(jd, deltaT, UnitType.DEGREES)
        val earthHeliocentricLatitude = EarthPosition.earthHeliocentricLatitude(jd, deltaT, UnitType.DEGREES)
        val jce = TimeUtil.julianType(jd, deltaT, JulianType.JCE)
        val sunTrueGeocentricLatitude = -earthHeliocentricLatitude
        val thetaZero = (earthHeliocentricLongitude + 180).mod(360.0)
        val lambdaP = thetaZero - 1.397 * jce - 0.00031 * jce.pow(2)
        val deltaTheta = (-0.09033 + 0.03916 * (cos(Math.toRadians(lambdaP)) + sin(Math.toRadians(lambdaP))) * tan(sunTrueGeocentricLatitude)) / 3600.0
        
        val sunTrueGeocentricLongitude = (thetaZero + deltaTheta).mod(360.0)
        
        return when (unitType) {
            UnitType.DEGREES -> sunTrueGeocentricLongitude
            UnitType.RADIANS -> (Math.toRadians(sunTrueGeocentricLongitude))
        }
    }
	
	/**
    * Sun True Geocentric Latitude FK5 System default in degree, beta
    * can be considered apparent 
    * 
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sunTrueGeocentricLatitude in degree or radian
    */
    fun sunTrueGeocentricLatitude(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val earthHelioLon = EarthPosition.earthHeliocentricLongitude(jd, deltaT, UnitType.DEGREES)
        val earthHelioLat = EarthPosition.earthHeliocentricLatitude(jd, deltaT, UnitType.DEGREES)
        val jce = TimeUtil.julianType(jd, deltaT, JulianType.JCE)
        val betaZero = -earthHelioLat
        val theta = (earthHelioLon + 180).mod(360.0)
        val lambdaP = theta - 1.397 * jce - 0.00031 * jce.pow(2)
        val deltaBeta = (0.03916 * (cos(Math.toRadians(lambdaP)) - sin(Math.toRadians(lambdaP)))) / 3600.0
        val beta = betaZero + deltaBeta
        
        return when (unitType) {
            UnitType.DEGREES -> beta
            UnitType.RADIANS -> (Math.toRadians(beta))
        }
    }
    
    /**
    * Sun Apparent Geocentric Longitude default in degree
    *
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    * 
    * @return sunApparentGeocentricLongitude in degree or radian
    */
    fun sunApparentGeocentricLongitude(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val sunTrueGeocentricLongitude = sunTrueGeocentricLongitude(jd, deltaT)
        val deltaPsi = Nutation.nutationInLonAndObliquity(jd, deltaT)[0]
        val abr = Correction.abration(jd, deltaT) / 3600.0
        
        val sunApparentGeocentricLongitude = (sunTrueGeocentricLongitude + deltaPsi + abr).mod(360.0)
        
        return when (unitType) {
            UnitType.DEGREES -> sunApparentGeocentricLongitude
            UnitType.RADIANS -> (Math.toRadians(sunApparentGeocentricLongitude))
        }
    }
    
    /**
    * Sun Apparent Geocentric Semidiameter, s
    *
    * @param jd Julian Day
    * @param deltaT in second
    *
    * @return sunApparentGeoSemidiameter
    */
    fun sunApparentGeoSemidiameter(jd: Double, deltaT: Double = 0.0): Double {
        
        val r = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.AU)
        val s = 0.266563888889 / r
        return s
    }
    
    /**
    * Sun Apparent Geocentric Right Ascension FK5 System default in degree, a
    *
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sunApparentGeoRightAscension in degree or radian
    */
    fun sunApparentGeoRightAscension(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambda = sunApparentGeocentricLongitude(jd, deltaT)
        val beta = sunTrueGeocentricLatitude(jd, deltaT)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        
        val raDeg = (Math.toDegrees(atan2(sin(Math.toRadians(lambda)) * cos(Math.toRadians(epsilon)) - tan(Math.toRadians(beta)) * sin(Math.toRadians(epsilon)), cos(Math.toRadians(lambda))))).mod(360.0)
        val raRad = Math.toRadians(raDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> raDeg
            UnitType.RADIANS -> raRad
        }
    }
    
    /**
    * Sun Apparent Geocentric Declination default in degree, d
    * 
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sun geocentric declination in degree or radian
    */
    fun sunApparentGeoDeclination(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambda = sunApparentGeocentricLongitude(jd, deltaT)
        val beta = sunTrueGeocentricLatitude(jd, deltaT)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        
        val sunDecDeg = Math.toDegrees(asin(sin(Math.toRadians(beta)) * cos(Math.toRadians(epsilon)) + cos(Math.toRadians(beta)) * sin(Math.toRadians(epsilon)) * sin(Math.toRadians(lambda))))
        val sunDecRad = Math.toRadians(sunDecDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> sunDecDeg
            UnitType.RADIANS -> sunDecRad
        }
    }
    
    /**
    * Greenwich Mean Sidereal Time default in degree, GMST
    *
    * @param jd Julian Day
    * @param unitType degree or radian
    *
    * @return gmst in degree or radian
    */
    fun greenwichMeanSiderealTime(jd: Double, unitType: UnitType = UnitType.DEGREES): Double {
        
        val jc = TimeUtil.julianType(jd, 0.0, JulianType.JC)
        val gmstDeg = (280.46061837 + 360.98564736629 * (jd - 2451545.0) + 0.000387933 * jc.pow(2) - (jc.pow(3) / 38710000)).mod(360.0)
        val gmstRad = Math.toRadians(gmstDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> gmstDeg
            UnitType.RADIANS -> gmstRad
        }
    }
    
    /**
    * Greenwich Apparent Sidereal Time default in degree, GAST
    *
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return gast in degree or radian
    */
    fun greenwichApparentSiderealTime(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gmst = greenwichMeanSiderealTime(jd)
        val deltaPsi = Nutation.nutationInLonAndObliquity(jd, deltaT)[0]
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        
        val gastDeg = (gmst + deltaPsi * cos(Math.toRadians(epsilon))).mod(360.0)
        val gastRad = Math.toRadians(gastDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> gastDeg
            UnitType.RADIANS -> gastRad
        }
    }
    
    /**
    * Local Apparent Sidereal Time default in degree, LAST
    *
    * @param jd Julian Day
    * @param lon Longitude of Observer
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return last in degree or radian
    */
    fun localApparentSiderealTime(jd: Double, lon: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gast = greenwichApparentSiderealTime(jd, deltaT)
        
        val lastDeg = (gast + lon).mod(360.0)
        val lastRad = Math.toRadians(lastDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> lastDeg
            UnitType.RADIANS -> lastRad
        }
    }
    
    /**
    * Sun Geocentric Greenwich Hour Angle default in degree, GHA, Ho
    *
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return gha in degree or radian
    */
    fun sunGeoGreenwichHourAngle(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gast = greenwichApparentSiderealTime(jd, deltaT)
        val ra = sunApparentGeoRightAscension(jd, deltaT)
        
        val ghaDeg = (gast - ra).mod(360.0)
        val ghaRad = Math.toRadians(ghaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> ghaDeg
            UnitType.RADIANS -> ghaRad
        }
    }
    
    /**
    * Sun Geocentric Local Hour Angle FK5 System default in degree, LHA, H
    *
    * @param jd Julian Day
    * @param lon Longitude of Observer
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return lha in degree or radian
    */
    fun sunGeoLocalHourAngle(jd: Double, lon: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gast = greenwichApparentSiderealTime(jd, deltaT)
        val ra = sunApparentGeoRightAscension(jd, deltaT)
        
        val lhaDeg = (gast + lon - ra).mod(360.0)
        val lhaRad = Math.toRadians(lhaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> lhaDeg
            UnitType.RADIANS -> lhaRad
        }
    }
    
    /**
    * Sun Geocentric Azimuth Measured from True North default in degree, A
    *
    * @param jd Julian Day
    * @param lon Longitude of Observer
    * @param lat Latitude of Observer
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sunGeoAzimuth in degree or radian
    */
    fun sunGeoAzimuth(jd: Double, lon: Double, lat: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lha = sunGeoLocalHourAngle(jd, lon, deltaT)
        val dec = sunApparentGeoDeclination(jd, deltaT)
        
        val azDeg = (Math.toDegrees(atan2(sin(Math.toRadians(lha)), cos(Math.toRadians(lha)) * sin(Math.toRadians(lat)) - tan(Math.toRadians(dec)) * cos(Math.toRadians(lat)))) + 180).mod(360.0)
        val azRad = Math.toRadians(azDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> azDeg
            UnitType.RADIANS -> azRad
        }
    }
    
    /**
    * Sun Geocentric Altitude default in degree, h
    *
    * @param jd Julian Day
    * @param lon Longitude of Observer
    * @param lat Latitude of Observer
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sunGeoAzimuth in degree or radian
    */
    fun sunGeoAltitude(jd: Double, lon: Double, lat: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lha = sunGeoLocalHourAngle(jd, lon, deltaT)
        val dec = sunApparentGeoDeclination(jd, deltaT)
        
        val altDeg = Math.toDegrees(asin(sin(Math.toRadians(lat)) * sin(Math.toRadians(dec)) + cos(Math.toRadians(lat)) * cos(Math.toRadians(dec)) * cos(Math.toRadians(lha))))
        val altRad = Math.toRadians(altDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> altDeg
            UnitType.RADIANS -> altRad
        }
    }
    
    // Sun Topocentric Coordinate 
    
    /**
    * Sun Equatorial Horizontal Parallax 
    * @param jd Julian Day
    * @param deltaT in arcsecond
     * @param unitType degree or radian
    *
    * @return phi Sun Equatorial Horizontal Parallax
    */
    fun sunEquatorialHorizontalParallax(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val er = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.AU)
        val phiDeg = 8.794 / (er * 3600)
        val phiRad = Math.toRadians(phiDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> phiDeg
            UnitType.RADIANS -> phiRad
        }
    }
    
    /**
    * Sun term n in radian
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    *
    * @return n in radian
    */
    fun sunTermN(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0): Double {
        
        val lambda = sunApparentGeocentricLongitude(jd, deltaT)
        val beta = sunTrueGeocentricLatitude(jd, deltaT)
        val theta = localApparentSiderealTime(jd, lon, deltaT)
        val x = Correction.termX(lat, elev)
        val phi = sunEquatorialHorizontalParallax(jd, deltaT)
        val n = cos(Math.toRadians(lambda)) * cos(Math.toRadians(beta)) - x * sin(Math.toRadians(phi)) * cos(Math.toRadians(theta))
        
        return n
    }
    
    /**
    * Parallax in the Sun Right Ascension default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degrees or radians
    *
    * @return deltaAlpha in degree or radian
    */
    fun parallaxInTheSunRightAscension(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev)
        val phi = sunEquatorialHorizontalParallax(jd, deltaT)
        val lha = sunGeoLocalHourAngle(jd, lon, deltaT)
        val dec = sunApparentGeoDeclination(jd, deltaT)
        
        val deltaAlphaDeg = Math.toDegrees(atan2(-x * sin(Math.toRadians(phi)) * sin(Math.toRadians(lha)), cos(Math.toRadians(dec)) - x * sin(Math.toRadians(phi)) * cos(Math.toRadians(lha))))
        val deltaAlphaRad = Math.toRadians(deltaAlphaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> deltaAlphaDeg
            UnitType.RADIANS -> deltaAlphaRad
        }
    }
    
    /**
    * Parallax in the Sun Altitude default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return p in degree or radian
    */
    fun parallaxInTheSunAltitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val h = sunGeoAltitude(jd, lon, lat, deltaT)
        val phi = sunEquatorialHorizontalParallax(jd, deltaT)
        
        val pDeg = Math.toDegrees(asin(sqrt(y.pow(2) + x.pow(2)) * sin(Math.toRadians(phi)) * cos(Math.toRadians(h))))
        val pRad = Math.toRadians(pDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> pDeg
            UnitType.RADIANS -> pRad
        }
    }
	
	/**
    * Sun Topocentric Longitude default in degree, lambda apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return lambdaP in degree or radian
    */
    fun sunTopoLongitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambda = sunApparentGeocentricLongitude(jd, deltaT)
        val beta = sunTrueGeocentricLatitude(jd, deltaT)
        val theta = localApparentSiderealTime(jd, lon, deltaT)
        val phi = sunEquatorialHorizontalParallax(jd, deltaT)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val n = sunTermN(jd, lon, lat, elev, deltaT)
        
        val lambdaPDeg = (Math.toDegrees(atan2((sin(Math.toRadians(lambda)) * cos(Math.toRadians(beta)) - sin(Math.toRadians(phi)) * (y * sin(Math.toRadians(epsilon)) + x * cos(Math.toRadians(epsilon)) * sin(Math.toRadians(theta)))), n))).mod(360.0)
        val lambdaPRad = Math.toRadians(lambdaPDeg)
        return when (unitType) {
            UnitType.DEGREES -> lambdaPDeg
            UnitType.RADIANS -> lambdaPRad
        }
    }
    
    /**
    * Sun Topocentric Latitude default in degree, beta apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return betaP in degree or radian
    */
    fun sunTopoLatitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        val lambdaP = sunTopoLongitude(jd, lon, lat, elev, deltaT)
        val beta = sunTrueGeocentricLatitude(jd, deltaT)
        val theta = localApparentSiderealTime(jd, lon, deltaT)
        val phi = sunEquatorialHorizontalParallax(jd, deltaT)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val n = sunTermN(jd, lon, lat, elev, deltaT)
        
        val betaPDeg = Math.toDegrees(atan(cos(Math.toRadians(lambdaP)) * (sin(Math.toRadians(beta)) - sin(Math.toRadians(phi)) * (y * cos(Math.toRadians(epsilon)) - x * sin(Math.toRadians(epsilon)) * sin(Math.toRadians(theta)))) / n))
        val betaPRad = Math.toRadians(betaPDeg)
        return when (unitType) {
            UnitType.DEGREES -> betaPDeg
            UnitType.RADIANS -> betaPRad
        }
    }
    
    /**
    * Sun Topocentric Right Ascension default in degree, alpha apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return alphaP in degree or radian
    */
    fun sunTopoRightAscension(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val alpha = sunApparentGeoRightAscension(jd, deltaT)
        val deltaAlpha = parallaxInTheSunRightAscension(jd, lon, lat, elev, deltaT)
        
        val alphaPDeg = alpha + deltaAlpha
        val alphaPRad = Math.toRadians(alphaPDeg)
        return when (unitType) {
            UnitType.DEGREES -> alphaPDeg
            UnitType.RADIANS -> alphaPRad
        }
    }
    
    /**
    * Sun Topocentric Declination default in degree, delta apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return deltaP in degree or radian
    */
    fun sunTopoDeclination(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val phi = sunEquatorialHorizontalParallax(jd, deltaT)
        val dec = sunApparentGeoDeclination(jd, deltaT)
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val parallaxRA = parallaxInTheSunRightAscension(jd, lon, lat, elev, deltaT)
        val lha = sunGeoLocalHourAngle(jd, lon, deltaT)
        
        val deltaPDeg = Math.toDegrees(atan2((sin(Math.toRadians(dec)) - y * sin(Math.toRadians(phi))) * cos(Math.toRadians(parallaxRA)), cos(Math.toRadians(dec)) - x * sin(Math.toRadians(phi)) * cos(Math.toRadians(lha))))
        val deltaPRad = Math.toRadians(deltaPDeg)
        return when (unitType) {
            UnitType.DEGREES -> deltaPDeg
            UnitType.RADIANS -> deltaPRad
        }
    }
    
    /**
    * Sun Topocentric Local Hour Angle default in degree, H apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return lhaP in degree or radian
    */
    fun sunTopoLocalHourAngle(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lha = sunGeoLocalHourAngle(jd, lon, deltaT)
        val parallaxRA = parallaxInTheSunRightAscension(jd, lon, lat, elev, deltaT)
        
        val lhaPDeg = (lha - parallaxRA)
        val lhaPRad = Math.toRadians(lhaPDeg)
        return when (unitType) {
            UnitType.DEGREES -> lhaPDeg
            UnitType.RADIANS -> lhaPRad
        }
    }
    
    /**
    * Sun Topocentric Azimuth default in degree, A apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return azP in degree or radian
    */
    fun sunTopoAzimuth(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lhaP = sunTopoLocalHourAngle(jd, lon, lat, elev, deltaT)
        val decP = sunTopoDeclination(jd, lon, lat, elev, deltaT)
        
        val azPDeg = (Math.toDegrees(atan2(sin(Math.toRadians(lhaP)), cos(Math.toRadians(lhaP)) * sin(Math.toRadians(lat)) - tan(Math.toRadians(decP)) * cos(Math.toRadians(lat)))) + 180.0).mod(360.0)
        val azPRad = Math.toRadians(azPDeg)
        return when (unitType) {
            UnitType.DEGREES -> azPDeg
            UnitType.RADIANS -> azPRad
        }
    }
    
    /**
    * Sun Topocentric Altitude default in degree, h apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param temperature in C°
    * @param pressure in millibars
    * @param sunAltitudeType AIRLESS, APPARENT, or OBSERVER based in SunAltType enum class
    * @param unitType degree or radian
    *
    * @return altP in degree or radian in airless, apparent, or observer
    */
    fun sunTopoAltitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, sunAltitudeType: SunAltType = SunAltType.AIRLESS, unitType: UnitType = UnitType.DEGREES, temperature: Double = 10.0, pressure: Double = 1010.0): Double {
        
        val lhaP = sunTopoLocalHourAngle(jd, lon, lat, elev, deltaT)
        val decP = sunTopoDeclination(jd, lon, lat, elev, deltaT)
        val h = sunGeoAltitude(jd, lon, lat, deltaT)
        val refc = Correction.atmosphericRefractionFromAirlessAltitude(h, temperature, pressure)
        val dip = Correction.dip(elev)
        
        val hAirlessDeg = Math.toDegrees(asin(sin(Math.toRadians(lat)) * sin(Math.toRadians(decP)) + cos(Math.toRadians(lat)) * cos(Math.toRadians(decP)) * cos(Math.toRadians(lhaP))))
        val hApparentDeg = hAirlessDeg + refc
        val hObserverDeg = hApparentDeg + dip
        
        val hAirlessRad = Math.toRadians(hAirlessDeg)
        val hApparentRad = Math.toRadians(hApparentDeg)
        val hObserverRad = Math.toRadians(hObserverDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> when (sunAltitudeType) {
                SunAltType.AIRLESS -> hAirlessDeg
                SunAltType.APPARENT -> hApparentDeg
                SunAltType.OBSERVER -> hObserverDeg
            }
            UnitType.RADIANS -> when (sunAltitudeType) {
                SunAltType.AIRLESS -> hAirlessRad
                SunAltType.APPARENT -> hApparentRad
                SunAltType.OBSERVER -> hObserverRad
            }
        }
    }
    
    /**
    * Sun Topocentric Semidiameter default in degree, s apostrophe
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return sP in degree or radian
    */
    fun sunTopoSemidiameter(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambdaP = sunTopoLongitude(jd, lon, lat, elev, deltaT)
        val betaP = sunTopoLatitude(jd, lon, lat, elev, deltaT)
        val s = sunApparentGeoSemidiameter(jd, deltaT)
        val n = sunTermN(jd, lon, lat, elev, deltaT)
        
        val sPDeg = Math.toDegrees(asin(cos(Math.toRadians(lambdaP)) * cos(Math.toRadians(betaP)) * sin(Math.toRadians(s)) / n))
        val sPRad = Math.toRadians(sPDeg)
        return when (unitType) {
            UnitType.DEGREES -> sPDeg
            UnitType.RADIANS -> sPRad
        }
    }
    
    /**
    * Sun Equation of Time default in degree, e
    *
    * @param jd Julian Day
    * @param deltaT in arc second
     * @param unitType  degree or radian
    * 
    * @return e in degree or radian
    */
    fun equationOfTime(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {

        val jme = TimeUtil.julianType(jd, deltaT, JulianType.JME)
        val alpha = sunApparentGeoRightAscension(jd, deltaT)
        val deltaPsi = Nutation.nutationInLonAndObliquity(jd, deltaT)[0]
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        
        val lo = (280.4664567 + 360007.6982779 * jme + 0.03032028 * jme.pow(2) + jme.pow(3) / 49931 - jme.pow(4) / 15300 - jme.pow(5) / 2000000).mod(360.0)
        val e = (lo - 0.0057183 - alpha + deltaPsi * cos(Math.toRadians(epsilon)))
        
        val equationOfTimeMinute = e * 4
        
        val equationOfTimeHour = when {
    
            abs(equationOfTimeMinute) < 20.0 -> {
                e / 15
            }
        
            abs(equationOfTimeMinute) >= 20.0 && e > 0.0 -> {
                e / 15 - 24
            }
        
            abs(equationOfTimeMinute) >= 20.0 && e < 0.0 -> {
                e / 15 + 24
            }
        
            else -> {
                e / 15
            }
        }
        
        val eqRad = Math.toRadians(equationOfTimeHour)
        
        return when (unitType) {
            UnitType.DEGREES -> equationOfTimeHour
            UnitType.RADIANS -> eqRad
        }
    }

    /**
     * function to calc jd when maghrib, this formula is taken from hisab astronomis that developed by Ust. Abu Sabda
     *
     * @param jdNewMoon is julian day when new moon
     * @param lon is longitude of observer
     * @param lat is latitude of observer
     * @param elev is elevation of observer
     * @param timeZone is time zone of observer
     *
     * @return jdMaghribFinal
     */
    fun jdMaghrib(jdNewMoon: Double, lon: Double, lat: Double, elev: Double, timeZone: Double): Double {

        var set = 17.0
        var haMghrb: Double
        var kwd: Double
        val cjdn = floor(jdNewMoon + 0.5 + (timeZone / 24.0))
        for (i in 1..3) {
            var jdMghrb = cjdn - 0.5 + (set - timeZone) / 24.0
            var jdeMghrb = jdMghrb + DeltaT.deltaT(jdMghrb) / 86400.0
            var dekMghrb = sunApparentGeoDeclination(jdeMghrb)
            var semiMghrb = sunApparentGeoSemidiameter(jdeMghrb)
            var eotMghrb = equationOfTime(jdeMghrb)
            var refMghrb = 34.16 / 60.0
            // var dip = Correction.dip(elev)
            var dip = 2.1 * sqrt(elev) / 60.0
            var altMghrb = 0 - semiMghrb - refMghrb - dip + 0.0024
            var coshaMghrb = (sin(Math.toRadians(altMghrb)) - sin(Math.toRadians(lat)) * sin(Math.toRadians(dekMghrb))) / (cos(Math.toRadians(lat)) * cos(Math.toRadians(dekMghrb)))
            if (abs(coshaMghrb) < 1) {
                haMghrb = Math.toDegrees(acos(coshaMghrb))
                kwd = lon / 15.0 - timeZone
                set = haMghrb / 15.0 + 12.0 - eotMghrb - kwd
            } else {
                set = Double.NaN
            }
        }

        val jdMaghribFinal = cjdn - 0.5 + (set - timeZone) / 24.0
        return jdMaghribFinal
    }
	
	
}