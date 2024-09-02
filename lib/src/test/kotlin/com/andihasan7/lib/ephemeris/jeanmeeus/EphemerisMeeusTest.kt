package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus

class EphemerisMeeusTest {
    
    @Test
    fun testEphemerisMeeus() {
        
        val jm = EphemerisMeeus(
            10, // 20
            4, 
            1987, // 2023
            -7.029055555556,
            106.557722222222,
            0.0,
            0.0, // 17.8575
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
    }
}