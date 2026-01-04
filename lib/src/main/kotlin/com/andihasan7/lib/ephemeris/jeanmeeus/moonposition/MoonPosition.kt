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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.moonposition

import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.asin
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan
import kotlin.math.pow
import com.andihasan7.lib.ephemeris.jeanmeeus.correction.Correction
import com.andihasan7.lib.ephemeris.jeanmeeus.earthposition.EarthPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DistanceType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonAltType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.PositionType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.Nutation
import com.andihasan7.lib.ephemeris.jeanmeeus.moonterms.*
import com.andihasan7.lib.ephemeris.jeanmeeus.readerutil.MoonLBRReader
import com.andihasan7.lib.ephemeris.jeanmeeus.sunposition.SunPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil


object MoonPosition {
	
	// Moon Geocentric Coordinate
    /**
    * Moon True Geocentric Longitude default in true and degrees
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param positionType True or Apparent
    * @param unitType Degrees or Radians
    *
    * @return 
    */
    fun moonGeocentricLongitude(jd: Double, deltaT: Double = 0.0, positionType: PositionType = PositionType.TRUE, unitType: UnitType = UnitType.DEGREES): Double {
        
        val moonL = MOON_L.moon_L
        
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JCE) // nilaiT
        
		/**
        * bujur rata-rata bulan, L`, l1
        */
        val l1_r = Math.toRadians((218.3164591 + 481267.88134236 * t - 0.0013268 * t.pow(2) + t.pow(3) / 538841 - t.pow(4) / 65194000).mod(360.0))
		

        val l000 = MoonLBRReader.moonLongitudeReader(t, moonL)
        
		val lambdaAp = (l1_r + l000).mod(360.0)
        val lambdaApRad = Math.toRadians(lambdaAp)
        
		val deltaPsi = Nutation.nutationInLonAndObliquity(jd, deltaT)[0]
        val abr = -0.00019524 - 0.00001059 * sin(Math.toRadians(225 + 477198.9 * t))
        
        val lambda = lambdaAp + deltaPsi // + abr
        val lambdaRad = Math.toRadians(lambda)
        
        return when (unitType) {
            UnitType.DEGREES -> when (positionType) {
                PositionType.TRUE -> lambdaAp
                PositionType.APPARENT -> lambda
            }
            UnitType.RADIANS -> when (positionType) {
                PositionType.TRUE -> lambdaApRad
                PositionType.APPARENT -> lambdaRad
            }
        }
    }
    
    /**
    * Moon Geocentric Latitude default in true and degrees
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param positionType True or Apparent
    * @param unitType Degrees or Radians
    *
    * @return 
    */
    fun moonGeocentricLatitude(jd: Double, deltaT: Double = 0.0, positionType: PositionType = PositionType.TRUE, unitType: UnitType = UnitType.DEGREES): Double {
        
        val moonB = MOON_B.moon_B
        
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JCE)

        val b000 = MoonLBRReader.moonLatitudeReader(t, moonB)
        
		val betaAp = b000
        val betaApRad = Math.toRadians(betaAp)
        
        val abr = -0.00001754 * sin(Math.toRadians(183.3 + 483202 * t))
        
        val beta = betaAp + abr
        val betaRad = Math.toRadians(beta)
        
        return when (unitType) {
            UnitType.DEGREES -> when (positionType) {
                PositionType.TRUE -> betaAp
                PositionType.APPARENT -> beta
            }
            UnitType.RADIANS -> when (positionType) {
                PositionType.TRUE -> betaApRad
                PositionType.APPARENT -> betaRad
            }
        }
    }
    
    /**
    * Moon Geocentric Distance default in true and KM
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param positionType True or Apparent
    * @param distanceType KM, AU, or ER
    *
    * @return 
    */
    fun moonGeocentricDistance(jd: Double, deltaT: Double = 0.0, positionType: PositionType = PositionType.TRUE, distanceType: DistanceType = DistanceType.KM): Double {
        
        val moonR = MOON_R.moon_R
        
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JCE)

        val r000 = MoonLBRReader.moonRadiusReader(t, moonR)
                
        // r true km
        val rTrueKM = 385000.56 + r000
        val rTrueAU = rTrueKM / 149597870.7
        val rTrueER = rTrueKM / 6371.0
        
        val abr = 0.0708 * cos(Math.toRadians(225.0 + 477198.9 * t))
        
        // apparent km
        val rAppaKM = rTrueKM + abr
        val rAppaAU = rAppaKM / 149597870.7
        val rAppaER = rAppaKM / 6371.0
        
        return when (positionType) {
            PositionType.TRUE -> when (distanceType) {
                DistanceType.KM -> rTrueKM
                DistanceType.AU -> rTrueAU
                DistanceType.ER -> rTrueER
                // else -> throw IllegalArgumentException("Invalid input $distanceType, see the documentation")
            }
            PositionType.APPARENT -> when (distanceType) {
                DistanceType.KM -> rAppaKM
                DistanceType.AU -> rAppaAU
                DistanceType.ER -> rAppaER
            }
            // else -> throw IllegalArgumentException("Invalid input $positionType, see the documentation")
        }
    }
    
    /**
    * Moon Apparent Geocentric Right Ascension default in degree
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return alpha in degree or radian
    */
    fun moonAppaGeocentricRightAscension(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambda = moonGeocentricLongitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val beta = moonGeocentricLatitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        
        val alphaDeg = (Math.toDegrees(atan2(sin(Math.toRadians(lambda)) * cos(Math.toRadians(epsilon)) - tan(Math.toRadians(beta)) * sin(Math.toRadians(epsilon)), cos(Math.toRadians(lambda))))).mod(360.0)
        val alphaRad = Math.toRadians(alphaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> alphaDeg
            UnitType.RADIANS -> alphaRad
        }
    }
    
    /**
    * Moon Apparent Geocentric Declination default in degree
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return delta in degree or radian
    */
    fun moonAppaGeocentricDeclination(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambda = moonGeocentricLongitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val beta = moonGeocentricLatitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        
        val deltaDeg = Math.toDegrees(asin(sin(Math.toRadians(beta)) * cos(Math.toRadians(epsilon)) + cos(Math.toRadians(beta)) * sin(Math.toRadians(epsilon)) * sin(Math.toRadians(lambda))))
        val deltaRad = Math.toRadians(deltaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> deltaDeg
            UnitType.RADIANS -> deltaRad
        }
    }
    
    /**
    * Moon Geocentric Greenwich Hour Angle default in degree
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return gha in degree or radian
    */
    fun moonGeoGreenwichHourAngle(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gast = SunPosition.greenwichApparentSiderealTime(jd, deltaT)
        val alpha = moonAppaGeocentricRightAscension(jd, deltaT)
        
        val ghaDeg = (gast - alpha).mod(360.0)
        val ghaRad = Math.toRadians(ghaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> ghaDeg
            UnitType.RADIANS -> ghaRad
        }
    }
    
    /**
    * Moon Geocentric Local Hour Angle FK5 System default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return lha in degree or radian
    */
    fun moonGeoLocalHourAngle(jd: Double, lon: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gast = SunPosition.greenwichApparentSiderealTime(jd, deltaT)
        val alpha = moonAppaGeocentricRightAscension(jd, deltaT)
        
        val lhaDeg = (gast + lon - alpha).mod(360.0)
        val lhaRad = Math.toRadians(lhaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> lhaDeg
            UnitType.RADIANS -> lhaRad
        }
    }
    
    /**
    * Moon Geocentric Azimuth default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return az in degree or radian
    */
    fun moonGeoAzimuth(jd: Double, lon: Double, lat: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lha = moonGeoLocalHourAngle(jd, lon, deltaT)
        val dec = moonAppaGeocentricDeclination(jd, deltaT)
        
        val azDeg = (Math.toDegrees(atan2(sin(Math.toRadians(lha)), cos(Math.toRadians(lha)) * sin(Math.toRadians(lat)) - tan(Math.toRadians(dec)) * cos(Math.toRadians(lat)))) + 180).mod(360.0)
        
        val azRad = Math.toRadians(azDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> azDeg
            UnitType.RADIANS -> azRad
        }
    }
    
    /**
    * Moon Geocentric Altitude default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return alt in degree or radian
    */
    fun moonGeoAltitude(jd: Double, lon: Double, lat: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lha = moonGeoLocalHourAngle(jd, lon, deltaT)
        val dec = moonAppaGeocentricDeclination(jd, deltaT)
        
        val altDeg = Math.toDegrees(asin(sin(Math.toRadians(lat)) * sin(Math.toRadians(dec)) + cos(Math.toRadians(lat)) * cos(Math.toRadians(dec)) * cos(Math.toRadians(lha))))
        
        val altRad = Math.toRadians(altDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> altDeg
            UnitType.RADIANS -> altRad
        }
    }
    
    /**
    * Moon Equatorial Horizontal Parallax default in degree
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return phi in degree or radian
    */
    fun moonEquatorialHorizontalParallax(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val r = moonGeocentricDistance(jd, deltaT, PositionType.APPARENT, DistanceType.KM)
        
        val phiDeg = Math.toDegrees(asin(6378.14 / r))
        val phiRad = Math.toRadians(phiDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> phiDeg
            UnitType.RADIANS -> phiRad
        }
    }
    
    /**
    * Moon Geocentric Semidiameter default in degree, s
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return s in degree or radian
    */
    fun moonGeoSemidiameter(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val k = 0.272481
        val phi = moonEquatorialHorizontalParallax(jd, deltaT, UnitType.DEGREES)
        
        val sDeg = Math.toDegrees(asin(k * sin(Math.toRadians(phi))))
        val sRad = Math.toRadians(sDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> sDeg
            UnitType.RADIANS -> sRad
        }
    }
    
    /**
    * Moon-Sun Geocentric Elongation default in degree, d
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return d in degree or radian
    */
    fun moonSunGeoElongation(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val deltaSun = SunPosition.sunApparentGeoDeclination(jd, deltaT, UnitType.DEGREES)
        val alphaSun = SunPosition.sunApparentGeoRightAscension(jd, deltaT, UnitType.DEGREES)
        val deltaMoon = moonAppaGeocentricDeclination(jd, deltaT)
        val alphaMoon = moonAppaGeocentricRightAscension(jd, deltaT)
        
        val dDeg = Math.toDegrees(acos(sin(Math.toRadians(deltaSun)) * sin(Math.toRadians(deltaMoon)) + cos(Math.toRadians(deltaSun)) * cos(Math.toRadians(deltaMoon)) * cos(Math.toRadians(alphaSun - alphaMoon))))
        val dRad = Math.toRadians(dDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> dDeg
            UnitType.RADIANS -> dRad
        }
    }
    
    /**
    * Moon Geocentric Phase Angle default in degree, i
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return i in degree or radian
    */
    fun moonGeoPhaseAngle(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val rSun = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.KM)
        val rMoon = moonGeocentricDistance(jd, deltaT, PositionType.APPARENT, DistanceType.KM)
        val d = moonSunGeoElongation(jd, deltaT)
        
        val iDeg = Math.toDegrees(atan2(rSun * sin(Math.toRadians(d)), rMoon - rSun * cos(Math.toRadians(d))))
        val iRad = Math.toRadians(iDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> iDeg
            UnitType.RADIANS -> iRad
        }
    }
    
    /**
    * Moon Geocentric Disk Illuminated Fraction default in degree, k
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return k in degree or radian
    */
    fun moonGeoDiskIlluminatedFraction(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
    
        val i = moonGeoPhaseAngle(jd, deltaT)
        
        val kDeg = ((1 + cos(Math.toRadians(i))) / 2)
        val kRad = Math.toRadians(kDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> kDeg
            UnitType.RADIANS -> kRad
        }
    }
	
	/**
    * Moon Geocentric Bright Limb Angle default in degree, x
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return x in degree or radian
    */
    fun moonGeoBrightLimbAngle(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val deltaSun = SunPosition.sunApparentGeoDeclination(jd, deltaT, UnitType.DEGREES)
        val alphaSun = SunPosition.sunApparentGeoRightAscension(jd, deltaT, UnitType.DEGREES)
        val deltaMoon = moonAppaGeocentricDeclination(jd, deltaT)
        val alphaMoon = moonAppaGeocentricRightAscension(jd, deltaT)
        
        val xDeg = (Math.toDegrees(atan2(cos(Math.toRadians(deltaSun)) * sin(Math.toRadians(alphaSun - alphaMoon)), sin(Math.toRadians(deltaSun)) * cos(Math.toRadians(deltaMoon)) - cos(Math.toRadians(deltaSun)) * sin(Math.toRadians(deltaMoon)) * cos(Math.toRadians(alphaSun - alphaMoon))))).mod(360.0)
        val xRad = Math.toRadians(xDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> xDeg
            UnitType.RADIANS -> xRad
        }
    }
    
    /**
    * Moon term n in radian
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    *
    * @return n in radian
    */
    fun moonTermN(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0): Double {
        
        val lambda = moonGeocentricLongitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val beta = moonGeocentricLatitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val theta = SunPosition.localApparentSiderealTime(jd, lon, deltaT)
        val x = Correction.termX(lat, elev)
        val phi = moonEquatorialHorizontalParallax(jd, deltaT, UnitType.DEGREES)
        val n = cos(Math.toRadians(lambda)) * cos(Math.toRadians(beta)) - x * sin(Math.toRadians(phi)) * cos(Math.toRadians(theta))
        
        return n
    }
    
    /**
    * Parallax in the Moon Right Ascension default in degree, deltaAlpha
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arc second
    * @param unitType degree or radian
    * 
    * @return deltaAlpha in degree or radian
    */
    fun parallaxInTheMoonRightAscension(jd: Double, lon: Double, lat: Double, elev: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev) 
        val lha = moonGeoLocalHourAngle(jd, lon, deltaT)
        val phi = moonEquatorialHorizontalParallax(jd, deltaT, UnitType.DEGREES)
        val dec = moonAppaGeocentricDeclination(jd, deltaT)
        
        val deltaAlphaDeg = Math.toDegrees(atan2(-x * sin(Math.toRadians(phi)) * sin(Math.toRadians(lha)), cos(Math.toRadians(dec)) - x * sin(Math.toRadians(phi)) * cos(Math.toRadians(lha))))
        val deltaAlphaRad = Math.toRadians(deltaAlphaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> deltaAlphaDeg
            UnitType.RADIANS -> deltaAlphaRad
        }
    }
    
    /**
    * Parallax in the Moon Altitude default in degree
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
    fun parallaxInTheMoonAltitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val h = moonGeoAltitude(jd, lon, lat, deltaT)
        val phi = moonEquatorialHorizontalParallax(jd, deltaT)
        
        val pDeg = Math.toDegrees(asin(sqrt(y.pow(2) + x.pow(2)) * sin(Math.toRadians(phi)) * cos(Math.toRadians(h))))
        val pRad = Math.toRadians(pDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> pDeg
            UnitType.RADIANS -> pRad
        }
    }
    
    // Topocentric coor
    
    /**
    * Moon Topocentric Longitude default in degree
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
    fun moonTopoLongitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val n = moonTermN(jd, lon, lat, elev, deltaT)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        val lambda = moonGeocentricLongitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val beta = moonGeocentricLatitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val theta = SunPosition.localApparentSiderealTime(jd, lon, deltaT)
        val phi = moonEquatorialHorizontalParallax(jd, deltaT)
        
        val lambdaPDeg = (Math.toDegrees(atan2((sin(Math.toRadians(lambda)) * cos(Math.toRadians(beta)) - sin(Math.toRadians(phi)) * (y * sin(Math.toRadians(epsilon)) + x * cos(Math.toRadians(epsilon)) * sin(Math.toRadians(theta)))), n))).mod(360.0)
        val lambdaPRad = Math.toRadians(lambdaPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> lambdaPDeg
            UnitType.RADIANS -> lambdaPRad
        }
    }
    
    /**
    * Moon Topocentric Latitude default in degree
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
    fun moonTopoLatitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val n = moonTermN(jd, lon, lat, elev, deltaT)
        val epsilon = Nutation.trueObliquityOfEcliptic(jd, deltaT)
        val lambdaP = moonTopoLongitude(jd, lon, lat, elev, deltaT, UnitType.DEGREES)
        val beta = moonGeocentricLatitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)
        val theta = SunPosition.localApparentSiderealTime(jd, lon, deltaT)
        val phi = moonEquatorialHorizontalParallax(jd, deltaT)
        
        val betaPDeg = Math.toDegrees(atan(cos(Math.toRadians(lambdaP)) * (sin(Math.toRadians(beta)) - sin(Math.toRadians(phi)) * (y * cos(Math.toRadians(epsilon)) - x * sin(Math.toRadians(epsilon)) * sin(Math.toRadians(theta)))) / n))
        val betaPRad = Math.toRadians(betaPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> betaPDeg
            UnitType.RADIANS -> betaPRad
        }
    }
    
    /**
    * Moon Topocentric Right Ascension default in degree
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
    fun moonTopoRightAscension(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val alpha = moonAppaGeocentricRightAscension(jd, deltaT)
        val deltaAlpha = parallaxInTheMoonRightAscension(jd, lon, lat, elev, deltaT)
        
        val alphaPDeg = alpha + deltaAlpha
        val alphaPRad = Math.toRadians(alphaPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> alphaPDeg
            UnitType.RADIANS -> alphaPRad
        }
    }
    
    /**
    * Moon Topocentric Declination default in degree
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
    fun moonTopoDeclination(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val x = Correction.termX(lat, elev)
        val y = Correction.termY(lat, elev)
        val deltaAlpha = parallaxInTheMoonRightAscension(jd, lon, lat, elev, deltaT)
        val lha = moonGeoLocalHourAngle(jd, lon, deltaT)
        val phi = moonEquatorialHorizontalParallax(jd, deltaT, UnitType.DEGREES)
        val dec = moonAppaGeocentricDeclination(jd, deltaT)
        
        val deltaPDeg = Math.toDegrees(atan2((sin(Math.toRadians(dec)) - y * sin(Math.toRadians(phi))) * cos(Math.toRadians(deltaAlpha)), cos(Math.toRadians(dec)) - x * sin(Math.toRadians(phi)) * cos(Math.toRadians(lha))))
        val deltaPRad = Math.toRadians(deltaPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> deltaPDeg
            UnitType.RADIANS -> deltaPRad
        }
    }
    
    /**
    * Moon Topocentric Semidiameter default in degree
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
    fun moonTopoSemidiameter(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val lambdaP = moonTopoLongitude(jd, lon, lat, elev, deltaT)
        val betaP = moonTopoLatitude(jd, lon, lat, elev, deltaT)
        val s = moonGeoSemidiameter(jd, deltaT)
        val n = moonTermN(jd, lon, lat, elev, deltaT)
        
        val sPDeg = Math.toDegrees(asin(cos(Math.toRadians(lambdaP)) * cos(Math.toRadians(betaP)) * sin(Math.toRadians(s)) / n))
        val sPRad = Math.toRadians(sPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> sPDeg
            UnitType.RADIANS -> sPRad
        }
    }
    
    /**
    * Moon Topocentric Greenwich Hour Angle default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return gha in degree or radian
    */
    fun moonTopoGreenwichHourAngle(jd: Double, lon: Double, lat: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val gast = SunPosition.greenwichApparentSiderealTime(jd, deltaT, UnitType.DEGREES)
        val alpha = moonTopoRightAscension(jd, lon, lat, 0.0, deltaT)
        
        val ghaDeg = (gast - alpha).mod(360.0)
        val ghaRad = Math.toRadians(ghaDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> ghaDeg
            UnitType.RADIANS -> ghaRad
        }
    }
    
    /**
    * Moon Topocentric Local Hour Angle default in degree
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
    fun moonTopoLocalHourAngle(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val deltaAlpha = parallaxInTheMoonRightAscension(jd, lon, lat, elev, deltaT)
        val lha = moonGeoLocalHourAngle(jd, lon, deltaT)
        
        val lhaPDeg = lha - deltaAlpha
        val lhaPRad = Math.toRadians(lhaPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> lhaPDeg
            UnitType.RADIANS -> lhaPRad
        }
    }
    
    /**
    * Moon Topocentric Azimuth default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return azmP in degree or radian
    */
    fun moonTopoAzimuth(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val deltaP = moonTopoDeclination(jd, lon, lat, elev, deltaT)
        val lhaP = moonTopoLocalHourAngle(jd, lon, lat, elev, deltaT)
        
        val azmPDeg = (Math.toDegrees(atan2(sin(Math.toRadians(lhaP)), cos(Math.toRadians(lhaP)) * sin(Math.toRadians(lat)) - tan(Math.toRadians(deltaP)) * cos(Math.toRadians(lat)))) + 180).mod(360.0)
        val azmPRad = Math.toRadians(azmPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> azmPDeg
            UnitType.RADIANS -> azmPRad
        }
    }
    
    /**
    * Moon Topocentric Altitude in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param moonAltType see MoonAltType enum class
    * @param temperature in celcius
    * @param pressure in millibars
    *
    * @return moonTopoAltitude in degree
    */
    fun moonTopoAltitude(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, moonAltType: MoonAltType = MoonAltType.AIRLESS_CENTER, temperature: Double = 10.0, pressure: Double = 1010.0): Double {
        
        val deltaP = moonTopoDeclination(jd, lon, lat, elev, deltaT)
        val lhaP = moonTopoLocalHourAngle(jd, lon, lat, elev, deltaT)
        val dip = Correction.dip(elev)
        val s = moonTopoSemidiameter(jd, lon, lat, elev, deltaT)
        
        val hc = Math.toDegrees(asin(sin(Math.toRadians(lat)) * sin(Math.toRadians(deltaP)) + cos(Math.toRadians(lat)) * cos(Math.toRadians(deltaP)) * cos(Math.toRadians(lhaP))))
        val rhc = Correction.atmosphericRefractionFromAirlessAltitude(hc, temperature, pressure)
        val hac = hc + rhc
        val hoc = hac + dip
        
        val hu = hc + s
        val rhu = Correction.atmosphericRefractionFromAirlessAltitude(hu, temperature, pressure)
        val hau = hu + rhu
        val hou = hau + dip
        
        val hl = hc - s
        val rhl = Correction.atmosphericRefractionFromAirlessAltitude(hl, temperature, pressure)
        val hal = hl + rhl
        val hol = hal + dip
        
        return when (moonAltType) {
            MoonAltType.AIRLESS_CENTER -> hc
            MoonAltType.APPARENT_CENTER -> hac
            MoonAltType.OBSERVED_CENTER -> hoc
            MoonAltType.AIRLESS_UPPER -> hu
            MoonAltType.APPARENT_UPPER -> hau
            MoonAltType.OBSERVED_UPPER -> hou
            MoonAltType.AIRLESS_LOWER -> hl
            MoonAltType.APPARENT_LOWER -> hal
            MoonAltType.OBSERVED_LOWER -> hol
            MoonAltType.REFRACTION_CENTER -> rhc
            MoonAltType.REFRACTION_UPPER -> rhu
            MoonAltType.REFRACTION_LOWER -> rhl
        }
    }
    
    /**
    * Moon-Sun Topocentric Elongation default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return dP in degree or radian
    */
    fun moonSunTopoElongation(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val deltaPS = SunPosition.sunTopoDeclination(jd, lon, lat, elev, deltaT)
        val alphaPS = SunPosition.sunTopoRightAscension(jd, lon, lat, elev, deltaT)
        val deltaPM = moonTopoDeclination(jd, lon, lat, elev, deltaT)
        val alphaPM = moonTopoRightAscension(jd, lon, lat, elev, deltaT)
        
        val dPDeg = Math.toDegrees(acos(sin(Math.toRadians(deltaPS)) * sin(Math.toRadians(deltaPM)) + cos(Math.toRadians(deltaPS)) * cos(Math.toRadians(deltaPM)) * cos(Math.toRadians(alphaPS - alphaPM))))
        val dPRad = Math.toRadians(dPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> dPDeg
            UnitType.RADIANS -> dPRad
        }
    }
    
    /**
    * Moon Topocentric Phase Angle default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return iP in degree or radian
    */
    fun moonTopoPhaseAngle(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val rSun = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.KM)
        val rMoon = moonGeocentricDistance(jd, deltaT, PositionType.APPARENT, DistanceType.KM)
        val d = moonSunTopoElongation(jd, lon, lat, elev, deltaT)
        
        val iPDeg = Math.toDegrees(atan2(rSun * sin(Math.toRadians(d)), rMoon - rSun * cos(Math.toRadians(d))))
        val iPRad = Math.toRadians(iPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> iPDeg
            UnitType.RADIANS -> iPRad
        }
    }
	
	/**
    * Moon Topocentric Disk Illuminated Fraction default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return kP in degree or radian
    */
    fun moonTopoDiskIlluminatedFraction(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val i = moonTopoPhaseAngle(jd, lon, lat, elev, deltaT)
        
        val kPDeg = (1 + cos(Math.toRadians(i))) / 2
        val kPRad = Math.toRadians(kPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> kPDeg
            UnitType.RADIANS -> kPRad
        }
    }
    
    /**
    * Moon Topocentric Bright Limb Angle default in degree
    *
    * @param jd Julian Day
    * @param lon longitude of observer
    * @param lat latitude of observer
    * @param elev elevation of observer
    * @param deltaT in arcsecond
    * @param unitType degree or radian
    *
    * @return xP in degree or radian
    */
    fun moonTopoBrightLimbAngle(jd: Double, lon: Double, lat: Double, elev: Double = 0.0, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val deltaSun = SunPosition.sunTopoDeclination(jd, lon, lat, elev, deltaT)
        val alphaSun = SunPosition.sunTopoRightAscension(jd, lon, lat, elev, deltaT)
        val deltaMoon = moonTopoDeclination(jd, lon, lat, elev, deltaT)
        val alphaMoon = moonTopoRightAscension(jd, lon, lat, elev, deltaT)
        
        val xPDeg = (Math.toDegrees(atan2(cos(Math.toRadians(deltaSun)) * sin(Math.toRadians(alphaSun - alphaMoon)), sin(Math.toRadians(deltaSun)) * cos(Math.toRadians(deltaMoon)) - cos(Math.toRadians(deltaSun)) * sin(Math.toRadians(deltaMoon)) * cos(Math.toRadians(alphaSun - alphaMoon))))).mod(360.0)
        val xPRad = Math.toRadians(xPDeg)
        
        return when (unitType) {
            UnitType.DEGREES -> xPDeg
            UnitType.RADIANS -> xPRad
        }
    }

}
