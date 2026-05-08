package com.andihasan7.lib.ephemeris.jeanmeeus

import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonActivityType
import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.moonactivity.MoonActivity

class MoonActivityTest {

    @Test
    fun moonActivityTest() {

        val ma = MoonActivity
        
        val date = 7
        val month = 5
        val year = 2026
        val lat = 59.91388889
        val lon = 10.75222222
        val elev = 50.0
        val tz = 1.0
        val useDip = true
        
        val moonRise = ma.moonActivity(
            date,
            month,
            year,
            lon,
            lat,
            elev,
            tz,
            5,
            MoonActivityType.RISE,
            useDip
        )
        val moonSet = ma.moonActivity(
            date,
            month,
            year,
            lon,
            lat,
            elev,
            tz,
            5,
            MoonActivityType.SET,
            useDip
        )
        val moonTransit = ma.moonActivity(
            date,
            month,
            year,
            lon,
            lat,
            elev,
            tz,
            5,
            MoonActivityType.TRANSIT,
            useDip
        )

        println("Moon Activity Test")
        println("Moon Rise: ${ConvertUtil.toTimeFullRound2(moonRise ?: 0.0)}")
        println("Moon Transit: ${ConvertUtil.toTimeFullRound2(moonTransit ?: 0.0)}")
        println("Moon Set : ${ConvertUtil.toTimeFullRound2(moonSet ?: 0.0)}")
        
    }
}