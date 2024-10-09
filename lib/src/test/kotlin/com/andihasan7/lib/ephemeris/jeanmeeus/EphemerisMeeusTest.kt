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
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2

class EphemerisMeeusTest {
    
    @Test
    fun testEphemerisMeeus() {
        
        val jm = EphemerisMeeus(
            20, // 20
            4, 
            2023, // 2023
            -7.029055555556, // -6.166666666667, //-7.029055555556,
            106.557722222222, // 106.85, //106.557722222222,
            52.685, //elev
            7.0, //tz
            17.8575,//17.828611111111, // 17.8575
            10.0, // suhu lokal rata-rata tahunan
            1010.0, // tekanan udara lokal rata-rata tahunan
            true
        )
        
        val jd = jm.jd
        val deltaT = jm.deltaT
        val jde = jm.jde
        val jc = jm.jc
        val nilaiT = jm.nilaiT
        val tau = jm.tau
        
        // data matahari
        val deltaPsiArcsec = jm.deltaPsiArcsec
        val deltaPsiDegrees = jm.deltaPsiDegrees
        val u = jm.u
        val meanObliquityOfEcliptic = jm.meanObliquityOfEcliptic
        val deltaEpsArcsec = jm.deltaEpsArcsec
        val deltaEps = jm.deltaEps
        val trueObliquityOfEcliptic = jm.trueObliquityOfEcliptic
		
		val earthHeliocentricLongitudeDegrees = jm.earthHeliocentricLongitudeDegrees
		val sunGeometricLongitudeDegrees = jm.sunGeometricLongitudeDegrees
		val sunGeometricLonLamdaMRadians = jm.sunGeometricLonLamdaMRadians
        val sunTrueGeocentricLonJ2000Degrees = jm.sunTrueGeocentricLonJ2000Degrees
		val sunTrueGeocentricLonFK5Degrees = jm.sunTrueGeocentricLonFK5Degrees
		val deltaBeta = jm.deltaBeta
        val earthHeliocentricLatitudeRadians = jm.earthHeliocentricLatitudeRadians
		val sunTrueGeocentricLatitudeRadians = jm.sunTrueGeocentricLatitudeRadians
		val sunTrueGeocentricLatitudeDegrees = jm.sunTrueGeocentricLatitudeDegrees
		val betaZero = jm.betaZero
        val sunTrueGeocentricDistanceAU = jm.sunTrueGeocentricDistanceAU
        val sunTrueGeocentricDistanceKM = jm.sunTrueGeocentricDistanceKM
        val sunTrueGeocentricDistanceER = jm.sunTrueGeocentricDistanceER
        val abrasiDegrees = jm.abrasiDegrees
        val sunApparentGeoLongitude = jm.sunApparentGeoLongitude
        val sunApparentGeoLatitude = jm.sunApparentGeoLatitude
        val sunApparentGeocentricSemidiameter = jm.sunApparentGeocentricSemidiameter
        val sunApparentGeoRightAscension = jm.sunApparentGeoRightAscension
        val sunApparentGeoDeclination = jm.sunApparentGeoDeclination
        val greenwichMeanSideralTime = jm.greenwichMeanSideralTime
        val greenwichApparentSideralTime = jm.greenwichApparentSideralTime
        val localApparentSideralTimeDegrees = jm.localApparentSideralTimeDegrees
        
        val greenwichSideralTimeHour = jm.greenwichSideralTimeHour
        val greenwichApparentSideralTimeHour = jm.greenwichApparentSideralTimeHour
        val localApparentSideralTimeHour = jm.localApparentSideralTimeHour
        
        val sunGeocentricGreenwichHourAngle = jm.sunGeocentricGreenwichHourAngle
        val sunGeocentricLocalHourAngle = jm.sunGeocentricLocalHourAngle
        val sunGeocentricAzimuth = jm.sunGeocentricAzimuth
        val sunGeocentricAltitude = jm.sunGeocentricAltitude
        val eqHorizontalParallaxSun = jm.eqHorizontalParallaxSun
        val suku_u = jm.suku_u
        val suku_x = jm.suku_x
        val suku_y = jm.suku_y
        val suku_rho = jm.suku_rho
        val suku_n = jm.suku_n
        val parallaxSunRightAscension = jm.parallaxSunRightAscension
        val sunAtmosphericRefraction = jm.sunAtmosphericRefraction
        val parallaxSunAltitude = jm.parallaxSunAltitude
        val sunTopocentricEclipLongitude = jm.sunTopocentricEclipLongitude
        val sunTopocentricEclipLatitude = jm.sunTopocentricEclipLatitude
        val sunTopocentricRightAscension = jm.sunTopocentricRightAscension
        val sunTopocentricDeclination = jm.sunTopocentricDeclination
        val sunTopocentricLocalHourAngle = jm.sunTopocentricLocalHourAngle
        val sunTopocentricAzimuth = jm.sunTopocentricAzimuth
        val sunAirlessTopocentricAltitude = jm.sunAirlessTopocentricAltitude
        val sunApparentTopocentricAltitude = jm.sunApparentTopocentricAltitude
        val sunObservedAltitude = jm.sunObservedAltitude
        val sunTopocentricSemidiameter = jm.sunTopocentricSemidiameter
        val equationOfTimeHour = jm.equationOfTimeHour
        
        // data bulan
        val bujurRata2Bulan = jm.bujurRata2Bulan
        val koreksiBujurBulan = jm.koreksiBujurBulan
        val moonTrueGeocentricLongitude = jm.moonTrueGeocentricLongitude
        val moonApparentGeocentricLongitude = jm.moonApparentGeocentricLongitude
        
        val moonApparentGeocentricLatitude = jm.moonApparentGeocentricLatitude
        val moonTrueGeocentricDistanceKM = jm.moonTrueGeocentricDistanceKM
        val moonApparentGeocentricDistanceKM = jm.moonApparentGeocentricDistanceKM
        val moonApparentGeocentricDistanceAU = jm.moonApparentGeocentricDistanceAU
        val moonApparentGeocentricDistanceER = jm.moonApparentGeocentricDistanceER
        val moonEquatorialHorizontalParallax = jm.moonEquatorialHorizontalParallax
        val moonApparentGeocentricSemidiameter = jm.moonApparentGeocentricSemidiameter
        val moonApparentGeocentricRightAscension = jm.moonApparentGeocentricRightAscension
        val moonApparentGeoDeclination = jm.moonApparentGeoDeclination
        val moonGeoGreenwichHourAngle = jm.moonGeoGreenwichHourAngle
        val moonGeoLocalHourAngle = jm.moonGeoLocalHourAngle
        val moonGeoAzimuthFromSelatan = jm.moonGeoAzimuthFromSelatan
        val moonGeoAzimuthFromUtara = jm.moonGeoAzimuthFromUtara
        val moonGeoAltitude = jm.moonGeoAltitude
        val moonSunApparentGeoElongation = jm.moonSunApparentGeoElongation
        val moonApparentGeoPhaseAngle = jm.moonApparentGeoPhaseAngle
        val moonApparentGeoPhaseAngleDMS = jm.moonApparentGeoPhaseAngleDMS
        val moonApparentGeoDiskIlluminatedFraction = jm.moonApparentGeoDiskIlluminatedFraction
        val moonApparentGeoDiskIlluminatedFractionPercent = jm.moonApparentGeoDiskIlluminatedFractionPercent
        val moonApparentGeoBrightLimbAngle = jm.moonApparentGeoBrightLimbAngle
        
        // moon topo
        val suku_n_moon = jm.suku_n_moon
        val moonApparentTopoLongitude = jm.moonApparentTopoLongitude
        val moonApparentTopoLatitude = jm.moonApparentTopoLatitude
        val moonApparentTopoSemidiameter = jm.moonApparentTopoSemidiameter
        val parallaxMoonRightAscension = jm.parallaxMoonRightAscension
        val moonApparentTopoRightAscension = jm.moonApparentTopoRightAscension
        val moonApparentTopoDeclination = jm.moonApparentTopoDeclination
        val moonApparentTopoLocalHourAngle = jm.moonApparentTopoLocalHourAngle
        val moonTopocentricAzimuth = jm.moonTopocentricAzimuth
        val parallaxMoonAltitude = jm.parallaxMoonAltitude
        // center limb
        val moonAirlessTopoAltitudeCenterLimb = jm.moonAirlessTopoAltitudeCenterLimb
        val moonAtmosphericRefractionForCenterLimb = jm.moonAtmosphericRefractionForCenterLimb
        val moonApparentTopoAltitudeCenterLimb = jm.moonApparentTopoAltitudeCenterLimb
        val moonObservedAltitudeCenterLimb = jm.moonObservedAltitudeCenterLimb
        // upper limb
        val moonAirlessTopoAltitudeUpperLimb = jm.moonAirlessTopoAltitudeUpperLimb
        val moonAtmosphericRefractionForUpperLimb = jm.moonAtmosphericRefractionForUpperLimb
        val moonApparentTopoAltitudeUpperLimb = jm.moonApparentTopoAltitudeUpperLimb
        val moonObservedAltitudeUpperLimb = jm.moonObservedAltitudeUpperLimb
        // lower limb
        val moonAirlessTopoAltitudeLowerLimb = jm.moonAirlessTopoAltitudeLowerLimb
        val moonAtmosphericRefractionForLowerLimb = jm.moonAtmosphericRefractionForLowerLimb
        val moonApparentTopoAltitudeLowerLimb = jm.moonApparentTopoAltitudeLowerLimb
        val moonObservedAltitudeLowerLimb = jm.moonObservedAltitudeLowerLimb
        val moonSunApparentTopoElongation = jm.moonSunApparentTopoElongation
        val moonApparentTopoPhaseAngle = jm.moonApparentTopoPhaseAngle
        val moonApparentTopoDiskIlluminatedFraction = jm.moonApparentTopoDiskIlluminatedFraction
        // val moonApparentTopoBrightLimbAngle = jm.moonApparentTopoBrightLimbAngle
        
        
        // val test = jm.test
    
        println("")
        println("JD                    = $jd")
        println("DeltaT                = $deltaT")
        println("JDE                   = $jde")
        println("JC                    = $jc")
        println("T                     = $nilaiT")
        println("tau                   = $tau")
        println("deltaPsi arcseconds   = $deltaPsiArcsec, ${jm.deltaPsiArcsecDMS}")
        println("deltaPsi_d            = $deltaPsiDegrees, ${jm.deltaPsiDegreesDMS}")
        println("u                     = $u")
        println("mean obliquity        = $meanObliquityOfEcliptic, ${jm.meanObliquityOfEclipticDMS}")
        println("deltaEpsArcsec        = $deltaEpsArcsec, ${jm.deltaEpsArcsecDMS}")
        println("deltaEps              = $deltaEps, ${jm.deltaEpsDMS}")
        println("true obliquity        = $trueObliquityOfEcliptic, ${jm.trueObliquityOfEclipticDMS}")
		println("")
		println("earthHelioLonDeg      = $earthHeliocentricLongitudeDegrees, ${jm.earthHeliocentricLongitudeDegreesDMS}")
        println("sunGeoLonDeg          = $sunGeometricLongitudeDegrees, ${jm.sunGeometricLongitudeDegreesDMS}")
        println("sunGeoLonLamdaNR      = $sunGeometricLonLamdaMRadians, ${jm.sunGeometricLonLamdaMRadiansDMS}")
        println("sunTrueGeoLonJ2000    = $sunTrueGeocentricLonJ2000Degrees, ${jm.sunTrueGeocentricLonJ2000DegreesDMS}")
		println("sunTrueGeoLonFK5Deg   = $sunTrueGeocentricLonFK5Degrees, ${jm.sunTrueGeocentricLonFK5DegreesDMS}")
        println("")
        println("earthHelioLatRad      = $earthHeliocentricLatitudeRadians, ${jm.earthHeliocentricLatitudeRadiansDMS}")
        println("betaZero              = $betaZero, ${jm.betaZero}")
        println("deltaBeta             = $deltaBeta, ${jm.deltaBeta}")
        println("sunTrueGeoLatRad      = $sunTrueGeocentricLatitudeRadians, ${jm.sunTrueGeocentricLatitudeRadiansDMS}")
		println("sunTrueGeoLatDeg      = $sunTrueGeocentricLatitudeDegrees, ${jm.sunTrueGeocentricLatitudeDegreesDMS}")
        println("")
        println("sun true geo dist AU  = $sunTrueGeocentricDistanceAU")
        println("sun true geo dist KM  = $sunTrueGeocentricDistanceKM")
        println("sun true geo dist ER  = $sunTrueGeocentricDistanceER")
        println("")
        println("abrasi degrees        = $abrasiDegrees")
        println("sunAppaGeoLon         = $sunApparentGeoLongitude, ${jm.sunApparentGeoLongitudeDMS}")
        println("sunAppaGeoLat         = $sunApparentGeoLatitude, ${jm.sunApparentGeoLatitudeDMS}")
        println("sunAppaGeoSemidia     = $sunApparentGeocentricSemidiameter, ${jm.sunApparentGeocentricSemidiameterDMS}")
        println("sunAppaGeoRightAsce   = $sunApparentGeoRightAscension | ${jm.sunApparentGeoRightAscensionDMS} | ${jm.sunApparentGeoRightAscensionHMS}")
        println("sunApparentGeoDecli   = $sunApparentGeoDeclination, ${jm.sunApparentGeoDeclinationDMS}")
        println("gmst                  = $greenwichMeanSideralTime, ${toDegreeFullRound2(jm.greenwichMeanSideralTime)}")
        println("gast                  = $greenwichApparentSideralTime, ${toDegreeFullRound2(jm.greenwichApparentSideralTime)}")
        println("last                  = $localApparentSideralTimeDegrees, ${toDegreeFullRound2(jm.localApparentSideralTimeDegrees)}")
        println("gst pukul             = $greenwichSideralTimeHour, ${toDegreeFullRound2(jm.greenwichSideralTimeHour)}")
        println("apparent gst pukul    = $greenwichApparentSideralTimeHour, ${toDegreeFullRound2(jm.greenwichApparentSideralTimeHour)}")
        println("apparent lst pukul    = $localApparentSideralTimeHour, ${toDegreeFullRound2(jm.localApparentSideralTimeHour)}")
        println("")
        println("sunGeoGreenwiHourAng  = $sunGeocentricGreenwichHourAngle, ${jm.sunGeocentricGreenwichHourAngleDMS}")
        println("sunGeoLocalHourAng    = $sunGeocentricLocalHourAngle, ${jm.sunGeocentricLocalHourAngleDMS}")
		println("sunGeoAzimuth         = $sunGeocentricAzimuth, ${jm.sunGeocentricAzimuthDMS}")
        println("sunGeoAltitude        = $sunGeocentricAltitude, ${jm.sunGeocentricAltitudeDMS}")
        println("eqHorizontalPrlxSun   = $eqHorizontalParallaxSun")
        println("suku u                = $suku_u")
        println("suku x                = $suku_x")
        println("suku y                = $suku_y")
        println("suku rho              = $suku_rho")
        println("suku n                = $suku_n")
        println("parallaxSunRightAsce  = $parallaxSunRightAscension")
        println("sunAtmosphericRefrac  = $sunAtmosphericRefraction, ${jm.sunAtmosphericRefractionDMS}")
        println("parallaxSunAltitude   = $parallaxSunAltitude, ${jm.parallaxSunAltitudeDMS}")
        println("sunTopoEclipLong      = $sunTopocentricEclipLongitude, ${jm.sunTopocentricEclipLongitudeDMS}")
        println("sunTopoEclipLat       = $sunTopocentricEclipLatitude, ${jm.sunTopocentricEclipLatitudeDMS}")
        println("sunTopoRightAsce      = $sunTopocentricRightAscension | ${jm.sunTopocentricRightAscensionDMS} | ${jm.sunTopocentricRightAscensionHMS}")
        println("sunTopoDeclination    = $sunTopocentricDeclination, ${jm.sunTopocentricDeclinationDMS}")
        println("sunTopoLocalHourAngle = $sunTopocentricLocalHourAngle, ${jm.sunTopocentricLocalHourAngleDMS}")
        println("sunTopocentricAzimuth = $sunTopocentricAzimuth, ${jm.sunTopocentricAzimuthDMS}")
        println("sunAirlessTopoAltitud = $sunAirlessTopocentricAltitude, ${jm.sunAirlessTopocentricAltitudeDMS}")
        println("sunAppaTopoAltitude   = $sunApparentTopocentricAltitude, ${jm.sunApparentTopocentricAltitudeDMS}")
        println("sunObservedAltitude   = $sunObservedAltitude, ${jm.sunObservedAltitudeDMS}")
        println("sunTopocentricSemidi  = $sunTopocentricSemidiameter, ${jm.sunTopocentricSemidiameterDMS}")
        println("equationOfTimeHour    = $equationOfTimeHour, ${jm.equationOfTimeHourHMS}")
        println(" ")
        println("data bulan : ")
        println(" ")
        println("bujurRata2Bulan       = $bujurRata2Bulan, ${jm.bujurRata2BulanDMS}")
        println("koreksiBujurBulan     = $koreksiBujurBulan, ${jm.koreksiBujurBulanDMS}")
        println("moonTrueGeoLongitide  = $moonTrueGeocentricLongitude, ${jm.moonTrueGeocentricLongitudeDMS}")
        println("moonAppaGeoLongitude  = $moonApparentGeocentricLongitude, ${jm.moonApparentGeocentricLongitudeDMS}")
        println("moonAppaGeoLatitude   = $moonApparentGeocentricLatitude, ${jm.moonApparentGeocentricLatitudeDMS}")
        println("moonTrueGeoDistanceKM = $moonTrueGeocentricDistanceKM")
        println("moonAppaGeoDistanceKM = $moonApparentGeocentricDistanceKM")
        println("moonAppaGeoDistanceAU = $moonApparentGeocentricDistanceAU")
        println("moonAppaGeoDistanceER = $moonApparentGeocentricDistanceER")
        println("moonEquHorizoParallax = $moonEquatorialHorizontalParallax, ${jm.moonEquatorialHorizontalParallaxDMS}")
        println("moonAppaGeoSemidiame  = $moonApparentGeocentricSemidiameter, ${jm.moonApparentGeocentricSemidiameterDMS}")
        println("moonAppaGeoRightAscen = $moonApparentGeocentricRightAscension | ${jm.moonApparentGeocentricRightAscensionDMS} | ${jm.moonApparentGeocentricRightAscensionHMS}")
        println("moonAppaGeoDeclinatio = $moonApparentGeoDeclination, ${jm.moonApparentGeoDeclinationDMS}")
        println("moonGeoGreenwiHourAng = $moonGeoGreenwichHourAngle, ${jm.moonGeoGreenwichHourAngleDMS}")
        println("moonGeoLocalHourAngle = $moonGeoLocalHourAngle, ${jm.moonGeoLocalHourAngleDMS}")
        println("moonGeoAzimuthSelatan = $moonGeoAzimuthFromSelatan, ${jm.moonGeoAzimuthFromSelatanDMS}")
        println("moonGeoAzimuthFrUtara = $moonGeoAzimuthFromUtara, ${jm.moonGeoAzimuthFromUtaraDMS}")
        println("moonGeoAltitude       = $moonGeoAltitude, ${jm.moonGeoAltitudeDMS}")
        println("moonSunAppaGeoElonga  = $moonSunApparentGeoElongation, ${jm.moonSunApparentGeoElongationDMS}")
        println("moonAppaGeoPhaseAngle = $moonApparentGeoPhaseAngle | $moonApparentGeoPhaseAngleDMS")
        println("moonAppaGeoIllumFrac  = $moonApparentGeoDiskIlluminatedFraction | $moonApparentGeoDiskIlluminatedFractionPercent %")
        println("moonAppGeoBrightLimbA = $moonApparentGeoBrightLimbAngle | ${jm.moonApparentGeoBrightLimbAngleDMS}")
        println("")
        println("bulan topocentric :")
        println("")
        println("suku_n_moon           = $suku_n_moon")
        println("moonAppaTopoLongitude = $moonApparentTopoLongitude | ${jm.moonApparentTopoLongitudeDMS}")
        println("moonAppaTopoLatitude  = $moonApparentTopoLatitude | ${jm.moonApparentTopoLatitudeDMS}")
        println("moonAppaTopoSemidiame = $moonApparentTopoSemidiameter | ${jm.moonApparentTopoSemidiameterDMS}")
        println("parallaxMoonRightAsce = $parallaxMoonRightAscension | ${jm.parallaxMoonRightAscensionDMS}")
        println("moonAppaTopoRightAsce = $moonApparentTopoRightAscension | ${jm.moonApparentTopoRightAscensionDMS}")
        println("moonAppaTopoDeclinati = $moonApparentTopoDeclination | ${jm.moonApparentTopoDeclinationDMS}")
        println("moonAppaTopoLocHourAn = $moonApparentTopoLocalHourAngle | ${jm.moonApparentTopoLocalHourAngleDMS}")
        println("moonTopocentriAzimuth = $moonTopocentricAzimuth | ${jm.moonTopocentricAzimuthDMS}")
        println("parallaxMoonAltitude  = $parallaxMoonAltitude | ${jm.parallaxMoonAltitudeDMS}")
        println("center limb:")
        println("moonAirTopoAltituCent = $moonAirlessTopoAltitudeCenterLimb | ${jm.moonAirlessTopoAltitudeCenterLimbDMS}")
        println("moonAtmosRefractiCent = $moonAtmosphericRefractionForCenterLimb | ${jm.moonAtmosphericRefractionForCenterLimbDMS}")
        println("moonAppaTopoAltitCent = $moonApparentTopoAltitudeCenterLimb | ${jm.moonApparentTopoAltitudeCenterLimbDMS}")
        println("moonObserAltitudCente = $moonObservedAltitudeCenterLimb | ${jm.moonObservedAltitudeCenterLimbDMS}")
        println("upper limb:")
        println("moonAirTopoAltituUppe = $moonAirlessTopoAltitudeUpperLimb | ${jm.moonAirlessTopoAltitudeUpperLimbDMS}")
        println("moonAtmosRefractiUppe = $moonAtmosphericRefractionForUpperLimb | ${jm.moonAtmosphericRefractionForUpperLimbDMS}")
        println("moonAppaTopoAltitUppe = $moonApparentTopoAltitudeUpperLimb | ${jm.moonApparentTopoAltitudeUpperLimbDMS}")
        println("moonObserAltitudUpper = $moonObservedAltitudeUpperLimb | ${jm.moonObservedAltitudeUpperLimbDMS}")
        println("lower limb:")
        println("moonAirTopoAltituLowe = $moonAirlessTopoAltitudeLowerLimb | ${jm.moonAirlessTopoAltitudeLowerLimbDMS}")
        println("moonAtmosRefractiLowe = $moonAtmosphericRefractionForLowerLimb | ${jm.moonAtmosphericRefractionForLowerLimbDMS}")
        println("moonAppaTopoAltitLowe = $moonApparentTopoAltitudeLowerLimb | ${jm.moonApparentTopoAltitudeLowerLimbDMS}")
        println("moonObserAltitudLower = $moonObservedAltitudeLowerLimb | ${jm.moonObservedAltitudeLowerLimbDMS}")
        println("")
        println("moonSunAppaTopoElonga = $moonSunApparentTopoElongation | ${jm.moonSunApparentTopoElongationDMS}")
        println("moonAppareTopoPhaseAn = $moonApparentTopoPhaseAngle | ${jm.moonApparentTopoPhaseAngleDMS}")
        println("moonAppaTopoIllumFrac = $moonApparentTopoDiskIlluminatedFraction | ${jm.moonApparentTopoDiskIlluminatedFractionPercent} %")
        
        // println("moonAppTopoBrigLimbAn = $moonApparentTopoBrightLimbAngle | ${jm.moonApparentTopoBrightLimbAngleDMS}")
        // println("test : $test")
    }
}