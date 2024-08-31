package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus

class EphemerisMeeusTest {
    
    @Test
    fun testEphemerisMeeus() {
        
        val jm = EphemerisMeeus(
    	    17,
            11,
            2009,
            -6.166666667,
            106.85,
            7.0,
            17.82861111,
            true
        )
    
        val jd = jm.jd
    
        println("")
        println("")
        println("JD = $jd")
    }
}