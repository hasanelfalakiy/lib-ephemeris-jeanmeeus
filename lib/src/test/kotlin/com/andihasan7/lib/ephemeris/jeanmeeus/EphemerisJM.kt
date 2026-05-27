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

package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.SunJM
import com.andihasan7.lib.ephemeris.jeanmeeus.MoonJM

class EphemerisJM {
    
    @Test
    fun testEphemerisJMNew() {
        
        /*
		val lat = -(7 + 1 / 60 + 44.6 / 3600)
        val lon = (106.0 + 33.0 / 60 + 27.8 / 3600)
        val hourD = (17 + 51.0 / 60 + 27.0 / 3600)
        */
       
        val ephe = SunJM(
            date = 28,
            month = 1,
            year = 2026,
            latitude = -7.476016667, // -7.029055555556,
            longitude = 111.3133556, // 106.557722222222,
            elevation = 0.0, // 52.685,
            timeZone = 7.0,
            hourDouble = 10.48861111, // 17.8575,// 17.94671437500343, // 17.8575,
            deltaTMode = 2,
            customDeltaT = 69.184
        )
        
        val elp = MoonJM(
            date = 28,
            month = 1,
            year = 2026,
            latitude = -7.476016667, // -7.029055555556,
            longitude = 111.3133556, // 106.557722222222,
            elevation = 0.0, // 52.685,
            timeZone = 7.0,
            hourDouble = 10.48861111, // 17.8575,// 17.94671437500343, // 17.8575,
            deltaTMode = 2,
            customDeltaT = 69.184
        )
        
        val cv = ConvertUtil
        
        val jd = ephe.jd
        val deltaT = ephe.deltaT
        val jde = ephe.jde
        val jc = ephe.jc
        val jce = ephe.jce
        val jm = ephe.jm
        val jme = ephe.jme
        val hourDouble = (ephe.hourDouble) // use ?. for casting to other data type
        val nutationInLongitude = ephe.nutationInLongitude
        val nutationInObliquity = ephe.nutationInObliquity
        val meanObliquityOfEcliptic = ephe.meanObliquityOfEcliptic
        val trueObliquityOfEcliptic = ephe.trueObliquityOfEcliptic
        val earthHelioLon = ephe.earthHeliocentricLongitudeDegree
        val earthHelioLat = ephe.earthHeliocentricLatitudeDegree
		
        val sunTrueGeoLon = ephe.sunTrueGeocentricLongitude
        val sunTrueGeoLat = ephe.sunTrueGeocentricLatitude
        val abr = ephe.abr
        val sunAppaGeoLon = ephe.sunApparentGeocentricLongitude
        val sunGeoDistanceAU = ephe.sunGeocentricDistanceAU
        val sunGeoDistanceKM = ephe.sunGeocentricDistanceKM
        val sunGeoDistanceER = ephe.sunGeocentricDistanceER
        val sunAppaGeoSemidiameter = ephe.sunApparentGeoSemidiameter
        val sunAppaGeoRA = ephe.sunApparentGeoRightAscension
        val sunAppaGeoDec = ephe.sunApparentGeoDeclination
        val gmst = ephe.greenwichMeanSiderealTimeDMS
        val gast = ephe.greenwichApparentSiderealTimeDMS
        val last = ephe.localApparentSiderealTimeDMS
        val gha = ephe.sunGeoGreenwichHourAngle
        val lha = ephe.sunGeoLocalHourAngle
        val sunGeoAzimuth = ephe.sunGeoAzimuth
        val sunGeoAltitude = ephe.sunGeoAltitude
        
        val sunEqHorizontalPlx = ephe.sunEquatorialHorizontalParallax
        val termU = ephe.termU
        val termX = ephe.termX
        val termY = ephe.termY
        val sunTermN = ephe.sunTermN
        val parallaxInTheSunRA = ephe.parallaxInTheSunRightAscension
        val parallaxInTheSunAltitude = ephe.parallaxInTheSunAltitude
        val atmosphericRefFromAirlessAlt = ephe.atmosphericRefractionFromAirlessAltitude
        val sunTopoLongitude = ephe.sunTopoLongitude
        val sunTopoLatitude = ephe.sunTopoLatitude
        val sunTopoRA = ephe.sunTopoRightAscension
        val sunTopoDec = ephe.sunTopoDeclination
        val sunTopoLHA = ephe.sunTopoLocalHourAngle
        val sunTopoAz = ephe.sunTopoAzimuth
        val sunTopoAirlessAlt = ephe.sunAirlessTopoAltitude
        val sunTopoApparentAlt = ephe.sunApparentTopoAltitude
        val sunTopoObserverAlt = ephe.sunObserverTopoAltitude
        val sunTopoSemidiameter = ephe.sunTopoSemidiameter
        val equationOfTime = ephe.equationOfTime
        
        val moonTrueGeoLon = elp.moonTrueGeocentricLongitude
        val moonAppaGeoLon = elp.moonApparentGeocentricLongitude
        val moonTrueGeoLat = elp.moonTrueGeocentricLatitude
        val moonAppaGeoLat = elp.moonApparentGeocentricLatitude
        val moonTrueGeoDistanceKM = elp.moonTrueGeocentricDistanceKM
        val moonAppaGeoDistanceKM = elp.moonAppaGeocentricDistanceKM
        val moonAppaGeoDistanceAU = elp.moonAppaGeocentricDistanceAU
        val moonAppaGeoDistanceER = elp.moonAppaGeocentricDistanceER
        val moonAppaGeoRA = elp.moonAppaGeocentricRightAscension
        val moonAppaGeoDec = elp.moonAppaGeocentricDeclination
        val moonGeoGHA = elp.moonGeoGreenwichHourAngle
        val moonGeoLHA = elp.moonGeoLocalHourAngle
        val moonGeoAzimuth = elp.moonGeoAzimuth
        val moonGeoAltitude = elp.moonGeoAltitude
        val moonEqHorizontalParallax = elp.moonEquatorialHorizontalParallax
        val moonGeoSemidiameter = elp.moonGeoSemidiameter
        val moonSunGeoElongation = elp.moonSunGeoElongation
        val moonGeoPhaseAngle = elp.moonGeoPhaseAngle
        val moonGeoDiskIlluminatedFraction = elp.moonGeoDiskIlluminatedFraction
        val moonGeoDiskIlluminatedFractionPercent = elp.moonGeoDiskIlluminatedFractionPercent
        val moonGeoBrightLimbAngle = elp.moonGeoBrightLimbAngle
        
        val moonTermN = elp.moonTermN
        val parallaxInTheMoonRightAscension = elp.parallaxInTheMoonRightAscension
        val parallaxInTheMoonAltitude = elp.parallaxInTheMoonAltitude
        val moonTopoLongitude = elp.moonTopoLongitude
        val moonTopoLatitude = elp.moonTopoLatitude
        val moonTopoRightAscension = elp.moonTopoRightAscension
        val moonTopoDeclination = elp.moonTopoDeclination
        val moonTopoSemidiameter = elp.moonTopoSemidiameter
        val moonTopoGHA = elp.moonTopoGreenwichHourAngle
        val moonTopoLHA = elp.moonTopoLocalHourAngle
        val moonTopoAzimuth = elp.moonTopoAzimuth
        
        val moonAirlessTopoAltC = elp.moonAirlessTopoAltitudeCenterLimb
        val moonAppaTopoAltC = elp.moonApparentTopoAltitudeCenterLimb
        val moonObTopoAltC = elp.moonObservedTopoAltitudeCenterLimb
        
        val moonAirlessTopoAltU = elp.moonAirlessTopoAltitudeUpperLimb
        val moonAppaTopoAltU = elp.moonApparentTopoAltitudeUpperLimb
        val moonObTopoAltU = elp.moonObservedTopoAltitudeUpperLimb
        
        val moonAirlessTopoAltL = elp.moonAirlessTopoAltitudeLowerLimb
        val moonAppaTopoAltL = elp.moonApparentTopoAltitudeLowerLimb
        val moonObTopoAltL = elp.moonObservedTopoAltitudeLowerLimb
        
        val moonAtmosphericRefTopoAltitudeCenter = elp.moonAtmosphericRefTopoAltitudeCenter
        val moonAtmosphericRefTopoAltitudeUpper = elp.moonAtmosphericRefTopoAltitudeUpper
        val moonAtmosphericRefTopoAltitudeLower = elp.moonAtmosphericRefTopoAltitudeLower
        val moonSunTopoElongation = elp.moonSunTopoElongation
        val moonTopoPhaseAngle = elp.moonTopoPhaseAngle
        val moonTopoDiskIlluminatedFraction = elp.moonTopoDiskIlluminatedFraction
        val moonTopoBrightLimbAngle = elp.moonTopoBrightLimbAngle
        
        // for realtime
        val gst = ephe.greenwichApparentSiderealTimeHourHMS
        val lst = ephe.localApparentSiderealTimeHourHMS
        val sunGHA = ephe.sunTopoGreenwichHourAngleHMS
        val sunLHA = ephe.sunTopoLocalHourAngleHMS
        val sunLon = ephe.sunTopoLongitudeDMS
        val sunLat = ephe.sunTopoLatitudeDMS
        val sunRa = ephe.sunTopoRightAscensionHMS
        val sunDec = ephe.sunTopoDeclinationDMS
        val sunAz = ephe.sunTopoAzimuthDMS
        val sunAlt = ephe.sunApparentTopoAltitudeDMS
        
        val moonGHA = elp.moonTopoGreenwichHourAngleHMS
        val moonLHA = elp.moonTopoLocalHourAngleHMS
        val moonLon = elp.moonTopoLongitudeDMS
        val moonLat = elp.moonTopoLatitudeDMS
        val moonRa = elp.moonTopoRightAscensionHMS
        val moonDec = elp.moonTopoDeclinationDMS
        val moonAz = elp.moonTopoAzimuthDMS
        val moonAlt = elp.moonApparentTopoAltitudeCenterLimbDMS
		
        
        println("Ephemeris Jean Meeus New Refactor code Periodic Terms (-+ 360)")
        println("")
        println("this is unit test")
        println("")
        println("Number of Date: ${ephe.numbDate}")
        println("Number of Month: ${ephe.numbMonth}")
        println("Name of Month: ${ephe.nameMonth}")
        println("Number of Year: ${ephe.numbYear}")
        println("Number of Day: ${ephe.numbDay}")
        println("Name of Day: ${ephe.nameDay}")
        println("Number of Pasaran: ${ephe.numbPasaran}")
        println("Name of Pasaran: ${ephe.namePasaran}")
        println("Hour Decimal: $hourDouble")
        println("Frac of Day: ${ephe.fracDay}")
        println("")
        println("Julian Day: $jd")
        println("Delta T: $deltaT")
        println("JDE: $jde")
        println("JC: $jc")
        println("JCE: $jce")
        println("JM: $jm")
        println("JME: $jme")
        println("Nutation in Lon: $nutationInLongitude, ${cv.toDegreeFullRound2(nutationInLongitude)}")
        println("Nutation in Obli: $nutationInObliquity, ${cv.toDegreeFullRound2(nutationInObliquity)}")
        println("Mean Obliquity: $meanObliquityOfEcliptic, ${cv.toDegreeFullRound2(meanObliquityOfEcliptic)}")
        println("True Obliquity: $trueObliquityOfEcliptic, ${cv.toDegreeFullRound2(trueObliquityOfEcliptic)}")
        println("")
        println("Earth Helio Lon: $earthHelioLon, ${cv.toDegreeFullRound2(earthHelioLon)}")
        println("Earth Helio Lat: $earthHelioLat, ${cv.toDegreeFullRound2(earthHelioLat)}")
        println("")
        println("Sun Geocentric Coor:")
        println("")
        println("Sun True Geo Lon: $sunTrueGeoLon, ${cv.toDegreeFullRound2(sunTrueGeoLon)}")
        println("Sun True Geo Lat: $sunTrueGeoLat, ${cv.toDegreeFullRound2(sunTrueGeoLat)}, ${ephe.sunTrueGeocentricLatitudeSS2}")
        println("Abration: $abr, ${cv.toDegreeFullRound2(abr)}")
        println("Sun Appa Geo Lon: $sunAppaGeoLon, ${cv.toDegreeFullRound2(sunAppaGeoLon)}")
        println("Sun Geo Distance AU: $sunGeoDistanceAU")
        println("Sun Geo Distance KM: $sunGeoDistanceKM")
        println("Sun Geo Distance ER: $sunGeoDistanceER")
        println("Sun Appa Geo Semidiameter: $sunAppaGeoSemidiameter, ${cv.toDegreeFullRound2(sunAppaGeoSemidiameter)}, ${ephe.sunApparentGeoSemidiameterMMSS2}")
        println("Sun Appa Geo Right Ascen: $sunAppaGeoRA, ${cv.toDegreeFullRound2(sunAppaGeoRA)}")
        println("Sun Appa Geo Declination: $sunAppaGeoDec, ${cv.toDegreeFullRound2(sunAppaGeoDec)}")
        println("Greenwich Mean Sidereal Time: $gmst")
        println("Greenwich Apparent Sidereal Time: $gast")
        println("Local Apparent Sidereal Time: $last")
        println("Sun Geo Greenwich HA: $gha, ${cv.toDegreeFullRound2(gha)}")
        println("Sun Geo Local HA: $lha, ${cv.toDegreeFullRound2(lha)}")
        println("Sun Geo Azimuth: $sunGeoAzimuth, ${cv.toDegreeFullRound2(sunGeoAzimuth)}")
        println("Sun Geo Altitude: $sunGeoAltitude, ${cv.toDegreeFullRound2(sunGeoAltitude)}")
        println("")
        println("Sun Topocentric Coor:")
        println("")
        println("Sun Eq Horizontal Parallax: $sunEqHorizontalPlx, ${cv.toDegreeFullRound2(sunEqHorizontalPlx)}")
        println("Term U: $termU")
        println("Term X: $termX")
        println("Term Y: $termY")
        println("Sun Term N: $sunTermN")
        println("Parallax in the Sun RA: $parallaxInTheSunRA, ${cv.toDegreeFullRound2(parallaxInTheSunRA)}")
        println("Parallax in the Sun Alt: $parallaxInTheSunAltitude, ${cv.toDegreeFullRound2(parallaxInTheSunAltitude)}")
        println("Atmospheric Ref from Airless Alt: $atmosphericRefFromAirlessAlt, ${cv.toDegreeFullRound2(atmosphericRefFromAirlessAlt)}")
        println("Sun Topo Lon: $sunTopoLongitude, ${cv.toDegreeFullRound2(sunTopoLongitude)}")
        println("Sun Topo Lat: $sunTopoLatitude, ${cv.toDegreeFullRound2(sunTopoLatitude)}")
        println("Sun Topo RA: $sunTopoRA, ${cv.toDegreeFullRound2(sunTopoRA)}")
        println("Sun Topo Dec: $sunTopoDec, ${cv.toDegreeFullRound2(sunTopoDec)}")
        println("Sun Topo LHA: $sunTopoLHA, ${cv.toDegreeFullRound2(sunTopoLHA)}")
        println("Sun Topo Az: $sunTopoAz, ${cv.toDegreeFullRound2(sunTopoAz)}")
        println("Sun Airless Topo Alt: $sunTopoAirlessAlt, ${cv.toDegreeFullRound2(sunTopoAirlessAlt)}")
        println("Sun Apparent Topo Alt: $sunTopoApparentAlt, ${cv.toDegreeFullRound2(sunTopoApparentAlt)}")
        println("Sun Observer Topo Alt: $sunTopoObserverAlt, ${cv.toDegreeFullRound2(sunTopoObserverAlt)}")
        println("Sun Topo Semidiameter: $sunTopoSemidiameter, ${cv.toDegreeFullRound2(sunTopoSemidiameter)}, ${ephe.sunTopoSemidiameterMMSS2}")
        println("Sun Equation of Time: $equationOfTime, ${cv.toTimeFullRound2(equationOfTime)}")
        println("")
        println("")
        println("GST/LST: $gst/$lst")
        println("sun GHA/LHA: $sunGHA/$sunLHA")
        println("sun Lon/Lat: $sunLon/$sunLat")
        println("sun Ra/Dec: $sunRa/$sunDec")
        println("sun Az/Alt: $sunAz/$sunAlt")
        println(" ")
        println("moon GHA/LHA: $moonGHA/$moonLHA")
        println("moon Lon/Lat: $moonLon/$moonLat")
        println("moon Ra/Dec: $moonRa/$moonDec")
        println("moon Az/Alt: $moonAz/$moonAlt")
        println(" ")
        
        println("Moon Geocentric Coor:")
        println("")
        println("Moon True Geo Lon: $moonTrueGeoLon, ${cv.toDegreeFullRound2(moonTrueGeoLon)}")
        println("Moon Appa Geo Lon: $moonAppaGeoLon, ${cv.toDegreeFullRound2(moonAppaGeoLon)}")
        println("Moon True Geo Lat: $moonTrueGeoLat, ${cv.toDegreeFullRound2(moonTrueGeoLat)}")
        println("Moon Appa Geo Lat: $moonAppaGeoLat, ${cv.toDegreeFullRound2(moonAppaGeoLat)}")
        println("Moon True Geo Distance KM: $moonTrueGeoDistanceKM")
        println("Moon Appa Geo Distance KM: $moonAppaGeoDistanceKM")
        println("Moon Appa Geo Distance AU: $moonAppaGeoDistanceAU")
        println("Moon Appa Geo Distance ER: $moonAppaGeoDistanceER")
        println("Moon Appa Geo RA: $moonAppaGeoRA, ${cv.toDegreeFullRound2(moonAppaGeoRA)}")
        println("Moon Appa Geo Dec: $moonAppaGeoDec, ${cv.toDegreeFullRound2(moonAppaGeoDec)}")
        println("Moon Geo GHA: $moonGeoGHA, ${cv.toDegreeFullRound2(moonGeoGHA)}")
        println("Moon Geo LHA: $moonGeoLHA, ${cv.toDegreeFullRound2(moonGeoLHA)}")
        println("Moon Geo Az: $moonGeoAzimuth, ${cv.toDegreeFullRound2(moonGeoAzimuth)}")
        println("Moon Geo Alt: $moonGeoAltitude, ${cv.toDegreeFullRound2(moonGeoAltitude)}")
        println("Moon Eq Horizontal Plx: $moonEqHorizontalParallax, ${cv.toDegreeFullRound2(moonEqHorizontalParallax)}")
        println("Moon Geo Semidiameter: $moonGeoSemidiameter, ${cv.toDegreeFullRound2(moonGeoSemidiameter)}")
        println("Moon-Sun Geo Elongation: $moonSunGeoElongation, ${cv.toDegreeFullRound2(moonSunGeoElongation)}")
        println("Moon Geo Phase Angle: $moonGeoPhaseAngle, ${cv.toDegreeFullRound2(moonGeoPhaseAngle)}")
        println("Moon Geo Disk Illuminated Fraction: $moonGeoDiskIlluminatedFraction ")
        println("Moon Geo Disk Illuminated Fraction Percent: $moonGeoDiskIlluminatedFractionPercent %")
        println("Moon Geo Bright Limb Angle: $moonGeoBrightLimbAngle, ${cv.toDegreeFullRound2(moonGeoBrightLimbAngle)}")
        println("Moon Term N: $moonTermN")
        println("Parallax in the Moon RA: $parallaxInTheMoonRightAscension, ${cv.toDegreeFullRound2(parallaxInTheMoonRightAscension)}")
        println("Parallax in the Moon Alt: $parallaxInTheMoonAltitude, ${cv.toDegreeFullRound2(parallaxInTheMoonAltitude)}")
        println("Moon Topo Longitude: $moonTopoLongitude, ${cv.toDegreeFullRound2(moonTopoLongitude)}")
        println("Moon Topo Latitude: $moonTopoLatitude, ${cv.toDegreeFullRound2(moonTopoLatitude)}")
        println("Moon Topo RA: $moonTopoRightAscension, ${cv.toDegreeFullRound2(moonTopoRightAscension)}")
        println("Moon Topo Dec: $moonTopoDeclination, ${cv.toDegreeFullRound2(moonTopoDeclination)}")
        println("Moon Topo Semidimeter: $moonTopoSemidiameter, ${cv.toDegreeFullRound2(moonTopoSemidiameter)}")
        println("Moon Topo GHA perlu dicek: $moonTopoGHA, ${cv.toDegreeFullRound2(moonTopoGHA)}")
        println("Moon Topo LHA: $moonTopoLHA, ${cv.toDegreeFullRound2(moonTopoLHA)}")
        println("Moon Topo Az: $moonTopoAzimuth, ${cv.toDegreeFullRound2(moonTopoAzimuth)}")
        println("")
        println("Moon Airless Topo Alt Center: $moonAirlessTopoAltC, ${cv.toDegreeFullRound2(moonAirlessTopoAltC)}")
        println("Moon Apparent Topo Alt Center: $moonAppaTopoAltC, ${cv.toDegreeFullRound2(moonAppaTopoAltC)}")
        println("Moon Observed Topo Alt Center: $moonObTopoAltC, ${cv.toDegreeFullRound2(moonObTopoAltC)}")
        println("")
        println("Moon Airless Topo Alt Upper: $moonAirlessTopoAltU, ${cv.toDegreeFullRound2(moonAirlessTopoAltU)}")
        println("Moon Apparent Topo Alt Upper: $moonAppaTopoAltU, ${cv.toDegreeFullRound2(moonAppaTopoAltU)}")
        println("Moon Observed Topo Alt Upper: $moonObTopoAltU, ${cv.toDegreeFullRound2(moonObTopoAltU)}")
        println("")
        println("Moon Airless Topo Alt Lower: $moonAirlessTopoAltL, ${cv.toDegreeFullRound2(moonAirlessTopoAltL)}")
        println("Moon Apparent Topo Alt Lower: $moonAppaTopoAltL, ${cv.toDegreeFullRound2(moonAppaTopoAltL)}")
        println("Moon Observed Topo Alt Lower: $moonObTopoAltL, ${cv.toDegreeFullRound2(moonObTopoAltL)}")
        println("")
        println("Moon Atmospheric Ref Topo Alt Center: $moonAtmosphericRefTopoAltitudeCenter, ${cv.toDegreeFullRound2(moonAtmosphericRefTopoAltitudeCenter)}")
        println("Moon Atmospheric Ref Topo Alt Upper: $moonAtmosphericRefTopoAltitudeUpper, ${cv.toDegreeFullRound2(moonAtmosphericRefTopoAltitudeUpper)}")
        println("Moon Atmospheric Ref Topo Alt Lower: $moonAtmosphericRefTopoAltitudeLower, ${cv.toDegreeFullRound2(moonAtmosphericRefTopoAltitudeLower)}")
        println("")
        println("Moon Sun Topo Elongation: $moonSunTopoElongation, ${cv.toDegreeFullRound2(moonSunTopoElongation)}")
        println("Moon Topo Phase Angle: $moonTopoPhaseAngle, ${cv.toDegreeFullRound2(moonTopoPhaseAngle)}")
        println("Moon Topo Disk Illuminated Fraction: $moonTopoDiskIlluminatedFraction ${elp.moonTopoDiskIlluminatedFractionPercent} %")
        println("Moon Topo Bright Limb Angle: $moonTopoBrightLimbAngle, ${cv.toDegreeFullRound2(moonTopoBrightLimbAngle)}")

	}
}
