package com.andihasan7.lib.ephemeris.jeanmeeus

import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonActivityType
import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.moonactivity.MoonActivity

class MoonActivityTest {

    @Test
    fun moonActivityTest() {

        val ma = MoonActivity
        val moonRise = ma.moonActivity(
            5,
            5,
            2026,
            111.43333334,
            -7.43333334,
            150.0,
            7.0,
            5,
            MoonActivityType.RISE
        )
        val moonSet = ma.moonActivity(
            5,
            5,
            2026,
            111.43333334,
            -7.43333334,
            150.0,
            7.0,
            5,
            MoonActivityType.SET
        )
        val moonTransit = ma.moonActivity(
            5,
            5,
            2026,
            111.43333334,
            -7.43333334,
            150.0,
            7.0,
            5,
            MoonActivityType.TRANSIT
        )

        println("Moon Activity Test")
        println("Moon Rise: ${ConvertUtil.toTimeFullRound2(moonRise ?: 0.0)}")
        println("Moon Set : ${ConvertUtil.toTimeFullRound2(moonSet ?: 0.0)}")
        println("Moon Transit: ${ConvertUtil.toTimeFullRound2(moonTransit ?: 0.0)}")
    }
}