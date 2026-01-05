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

import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.prayertimes.PrayerTimes
import kotlin.test.Test

class PrayerTimesTest {

    @Test
    fun prayerTimesTest() {

        val ws = PrayerTimes(
            date = 1,
            month = 1,
            year = 2026,
            latitude = -7.4333333334,
            longitude = 111.43333333334,
            elevation = 150.0,
            timeZone = 7.0,
            ihtiyatDzuhur = 3,
            otherIhtiyat = 2
        )

        val imsak = ws.imsakWD_HMS
        val shubuh = ws.shubuhWD_HMS
        val terbit = ws.terbitWD_HMS
        val dluha = ws.dluhaWD_HMS
        val dzuhur = ws.dzuhurWD_HMS
        val ashar = ws.asharWD_HMS
        val maghrib = ws.maghribWD_HMS
        val isya = ws.isyaWD_HMS
        val tMalam = ws.tengahMalamWD_HMS
        val duaPerTigaMalam = ws.duaPer3MalamWD_HMS
        val rashdul1 = ws.rashdu1HMS
        val rashdul2 = ws.rashdu2HMS
        val selisihJam = ws.selisihJamHMS
        val jarakKeduanya = ws.jarakKeduanya
        val selisihLK = ws.selisihDecLintangKabahDMS
        val selisihLT = ws.selisihDecLintangTempatDMS
        val dek12Noon = ConvertUtil.toDegreeFullRound2(ws.dek)
        val eq12Noon = ConvertUtil.toCounterMMSS2(ws.eq)
        val semi12Noon = ConvertUtil.toDegreeFullRound2(ws.semidiameter)

        println("1 Jan 2026")
        println("Imsak   : $imsak")
        println("Shubuh  : $shubuh")
        println("Terbit  : $terbit")
        println("Dluha   : $dluha")
        println("Dzuhur  : $dzuhur")
        println("Ashar   : $ashar")
        println("Maghrib : $maghrib")
        println("Isya    : $isya")
        println("T. Malam: $tMalam")
        println("2/3 Mlm : $duaPerTigaMalam")
        println("rashdu1 : $rashdul1")
        println("rashdu2 : $rashdul2")
        println("")
        println("Selisih Jam: $selisihJam")
        println("Jarak Keduanya: $jarakKeduanya")
        println("Selisih Dek - LK: $selisihLK")
        println("Selisih Dek - LT: $selisihLT")
        println("")
        println("Deklinasi : $dek12Noon")
        println("Equation  : $eq12Noon")
        println("Semidiameter: $semi12Noon")


    }
}