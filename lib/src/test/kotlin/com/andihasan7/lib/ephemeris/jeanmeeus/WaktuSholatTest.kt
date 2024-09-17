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
import com.andihasan7.lib.ephemeris.jeanmeeus.waktusholat.WaktuSholat

class WaktuSholatTest {
    
    
    @Test
    fun waktuSholatTest() {
        
        val ws = WaktuSholat(
            date = 10,
            month = 5,
            year = 2018,
            latitude = -7.813166666667, // -6.166667, //-7.433333333334,
            longitude = 112.02425, //106.85, //111.4333333333334,
            elevation = 95.0,
            timeZone = 7.0,
            ihtiyatDzuhur = 2,
            ihtiyatLain = 2
        )
        
        println("dzuhur wd    : ${ws.dzuhurWD_HMS}")
        println("dzuhur wis   : ${ws.dzuhurWIS_HMS}")
        println("")
        println("ashar wd     : ${ws.asharWD_HMS}")
        println("ashar wis    : ${ws.asharWIS_HMS}")
        println("")
        println("maghrib wd   : ${ws.maghribWD_HMS}")
        println("maghrib wis  : ${ws.maghribWIS_HMS}")
        println("")
        println("isya wd      : ${ws.isyaWD_HMS}")
        println("isya wis     : ${ws.isyaWIS_HMS}")
        println("")
        println("imsak wd     : ${ws.imsakWD_HMS}")
        println("imsak wis    : ${ws.imsakWIS_HMS}")
        println("")
        println("shubuh wd    : ${ws.shubuhWD_HMS}")
        println("shubuh wis   : ${ws.shubuhWIS_HMS}")
        println("")
        println("terbit wd    : ${ws.terbitWD_HMS}")
        println("terbit wis   : ${ws.terbitWIS_HMS}")
        println("")
        println("dluha wd     : ${ws.dluhaWD_HMS}")
        println("dluha wis    : ${ws.dluhaWIS_HMS}")
        println("")
        println("tengah m wd  : ${ws.tengahMalamWD_HMS}")
        println("tengah m wis : ${ws.tengahMalamWIS_HMS}")
        println("")
        println("2/3 malam wd : ${ws.duaPer3MalamWD_HMS}")
        println("2/3 malam wis: ${ws.duaPer3MalamWIS_HMS}")
        println("")
        println("rashdu 1     : ${ws.rashdu1HMS}")
        println("rashdu 2     : ${ws.rashdu2HMS}")
        println("")
        println("selisih jam  : ${ws.selisihJamHMS}")
        println("jarak keduany: ${ws.jarakKeduanya}")
        println("selisih deklk: ${ws.selisihDecLintangKabahDMS}")
        println("selisih deklt: ${ws.selisihDecLintangTempatDMS}")
    }
    
}