package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus
import com.andihasan7.lib.ephemeris.jeanmeeus.arahqiblat.ArahQiblat
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toTimeFullRound2

class ArahQiblatTest {

    @Test
    fun arahQiblatTest() {
        
        val kiblat = ArahQiblat()
        
        val utsb = kiblat.arahQiblat(-7.433333333334, 111.43333333334)[2]
        
        val rashdu = kiblat.rashduQiblat(
            date = 11,
            month = 9,
            year = 2024,
            latitude = -7.433333333334,
            longitude = 111.4333333333334,
            elevation = 150.0,
            timeZone = 7.0,
            azimuthUTSB = utsb
        )
        
        println("Azimuth UB : ${toDegreeFullRound2(kiblat.arahQiblat(-7.43333333334, 111.4333333333334)[0])}")
        println("Azimuth BU : ${toDegreeFullRound2(kiblat.arahQiblat(-7.43333333334, 111.4333333333334)[1])}")
        println("Azimuth UTSB : ${toDegreeFullRound2(kiblat.arahQiblat(-7.43333333334, 111.4333333333334)[2])}")
        println("")
        println("rashdu 1 : ${toTimeFullRound2(rashdu[0])}")
        println("rashdu 2 : ${toTimeFullRound2(rashdu[1])}")
    }
    
    @Test
    fun waktuSholatTest() {
        
        val ws = WaktuSholat(
            
        )
    }
}