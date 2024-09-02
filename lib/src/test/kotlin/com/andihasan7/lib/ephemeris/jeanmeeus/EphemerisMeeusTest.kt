package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus

class EphemerisMeeusTest {
    
    @Test
    fun testEphemerisMeeus() {
        
        val jm = EphemerisMeeus(
            20, // 20
            4, 
            2023, // 2023
            -6.166666666667, //-7.029055555556,
            106.85, //106.557722222222,
            7.0,
            17.8575,//17.828611111111, // 17.8575
            true
        )
        
        val jd = jm.jd
        val deltaT = jm.deltaT
        val jde = jm.jde
        val nilaiT = jm.nilaiT
        val tau = jm.tau
        
        val deltaPsi = jm.deltaPsi
        val deltaPsi_d = jm.deltaPsi_d
        val u = jm.u
        val meanObliquityOfEcliptic = jm.meanObliquityOfEcliptic
        val deltaEps = jm.deltaEps
        val trueObliquityOfEcliptic = jm.trueObliquityOfEcliptic
		
		val earthHeliocentricLongitudeDegrees = jm.earthHeliocentricLongitudeDegrees
		val sunGeometricLongitudeDegrees = jm.sunGeometricLongitudeDegrees
		val sunGeometricLonLamdaMRadians = jm.sunGeometricLonLamdaMRadians
		val sunTrueGeocentricLonFK5Degrees = jm.sunTrueGeocentricLonFK5Degrees
		val deltaBeta = jm.deltaBeta
        val earthHeliocentricLatitudeRadians = jm.earthHeliocentricLatitudeRadians
		val sunTrueGeocentricLatitudeRadians = jm.sunTrueGeocentricLatitudeRadians
		val sunTrueGeocentricLatitudeDegrees = jm.sunTrueGeocentricLatitudeDegrees
		val b_detikBusur = jm.b_detikBusur
        val r = jm.r
        
        val test = jm.test
    
        println("")
        println("test : $test")
        println("")
        println("JD                    = $jd")
        println("DeltaT                = $deltaT")
        println("JDE                   = $jde")
        println("T                     = $nilaiT")
        println("tau                   = $tau")
        println("deltaPsi              = $deltaPsi, ${jm.deltaPsiDMS}")
        println("deltaPsi_d            = $deltaPsi_d")
        println("u                     = $u")
        println("mean obliquity        = $meanObliquityOfEcliptic, ${jm.meanObliquityOfEclipticDMS}")
        println("deltaEps              = $deltaEps, ${jm.deltaEpsDMS}")
        println("true obliquity        = $trueObliquityOfEcliptic, ${jm.trueObliquityOfEclipticDMS}")
		println("")
		println("earthHelioLonDeg      = $earthHeliocentricLongitudeDegrees, ${jm.earthHeliocentricLongitudeDegreesDMS}")
        println("earthHelioLatRad      = $earthHeliocentricLatitudeRadians, ${jm.earthHeliocentricLatitudeRadiansDMS}")
        println("sunGeoLonDeg          = $sunGeometricLongitudeDegrees, ${jm.sunGeometricLongitudeDegreesDMS}")
        println("sunGeoLonLamdaNR      = $sunGeometricLonLamdaMRadians, ${jm.sunGeometricLonLamdaMRadiansDMS}")
		println("sunTrueGeoFK5Deg      = $sunTrueGeocentricLonFK5Degrees, ${jm.sunTrueGeocentricLonFK5DegreesDMS}")
        println("deltaBeta             = $deltaBeta, ${jm.deltaBeta}")
        println("sunTrueGeoLatRad      = $sunTrueGeocentricLatitudeRadians, ${jm.sunTrueGeocentricLatitudeRadiansDMS}")
		println("sunTrueGeoLatDeg      = $sunTrueGeocentricLatitudeDegrees, ${jm.sunTrueGeocentricLatitudeDegreesDMS}")
        println("b_detikBusur          = $b_detikBusur, ${jm.b_detikBusur}")
        println("vector radius         = $r, ${jm.r}")
		
    }
}