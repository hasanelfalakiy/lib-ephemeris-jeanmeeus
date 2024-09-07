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
            52.685,
            7.0,
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
        val localApparentSideralTime = jm.localApparentSideralTime
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
        
        
        
        
        
        // val test = jm.test
    
        println("")
        // println("test : $test")
        println("d : ${jm.krd}")
        println("m : ${jm.krm}")
        println("m1 : ${jm.krm1}")
        println("f : ${jm.krf}")
        println("jar bm po: ${jm.jari2BumiPolar}")
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
        println("sunAppaGeoRightAsce   = $sunApparentGeoRightAscension, ${jm.sunApparentGeoRightAscensionDMS}")
        println("sunApparentGeoDecli   = $sunApparentGeoDeclination, ${jm.sunApparentGeoDeclinationDMS}")
        println("gmst                  = $greenwichMeanSideralTime, ${toDegreeFullRound2(jm.greenwichMeanSideralTime)}")
        println("gast                  = $greenwichApparentSideralTime, ${toDegreeFullRound2(jm.greenwichApparentSideralTime)}")
        println("last                  = $localApparentSideralTime, ${toDegreeFullRound2(jm.localApparentSideralTime)}")
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
        println("sunTopoRightAsce      = $sunTopocentricRightAscension, ${jm.sunTopocentricRightAscensionDMS}")
        println("sunTopoDeclination    = $sunTopocentricDeclination, ${jm.sunTopocentricDeclinationDMS}")
        println("sunTopoLocalHourAngle = $sunTopocentricLocalHourAngle, ${jm.sunTopocentricLocalHourAngleDMS}")
        println("sunTopocentricAzimuth = $sunTopocentricAzimuth, ${jm.sunTopocentricAzimuthDMS}")
        println("sunAirlessTopoAltitud = $sunAirlessTopocentricAltitude, ${jm.sunAirlessTopocentricAltitudeDMS}")
        println("sunAppaTopoAltitude   = $sunApparentTopocentricAltitude, ${jm.sunApparentTopocentricAltitudeDMS}")
        println("sunObservedAltitude   = $sunObservedAltitude, ${jm.sunObservedAltitudeDMS}")
        println("sunTopocentricSemidi  = $sunTopocentricSemidiameter, ${jm.sunTopocentricSemidiameterDMS}")
        println("equationOfTimeHour    = $equationOfTimeHour, ${jm.equationOfTimeHourHMS}")
        
    }
}