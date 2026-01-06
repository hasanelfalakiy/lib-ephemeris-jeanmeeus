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
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DateFormat
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.PhaseType
import com.andihasan7.lib.ephemeris.jeanmeeus.moonphase.MoonPhaseJM
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import kotlin.test.Test

class MoonPhasesTest {

    @Test
    fun moonPhasesTest() {

        val mp = MoonPhaseJM
        val month = 9
        val year = 1446

        val newMoon = mp.moonPhase(month, year, PhaseType.NEWMOON)
        val dateNewMoon = TimeUtil.jdToGregorian<String>(newMoon, 7.0, DateFormat.DPDDMMYYM)
        val hourNewMoon = TimeUtil.jdToGregorian<Double>(newMoon, 7.0, DateFormat.HOUR_DOUBLE)
        println("New Moon: $dateNewMoon, ${ConvertUtil.toTimeFullRound2(hourNewMoon ?: 0.0)}")

        val firstMoon = mp.moonPhase(month, year, PhaseType.FIRSTQUARTER)
        val dateFirstMoon = TimeUtil.jdToGregorian<String>(firstMoon, 7.0, DateFormat.DPDDMMYYM)
        val hourFirstMoon = TimeUtil.jdToGregorian<Double>(firstMoon, 7.0, DateFormat.HOUR_DOUBLE)
        println("First Moon: $dateFirstMoon, ${ConvertUtil.toTimeFullRound2(hourFirstMoon ?: 0.0)}")

        val fullMoon = mp.moonPhase(month, year, PhaseType.FULLMOON)
        val dateFullMoon = TimeUtil.jdToGregorian<String>(fullMoon, 7.0, DateFormat.DPDDMMYYM)
        val hourFullMoon = TimeUtil.jdToGregorian<Double>(fullMoon, 7.0, DateFormat.HOUR_DOUBLE)
        println("Full Moon: $dateFullMoon, ${ConvertUtil.toTimeFullRound2(hourFullMoon ?: 0.0)}")

        val lastMoon = mp.moonPhase(month, year, PhaseType.LASTQUARTER)
        val dateLastMoon = TimeUtil.jdToGregorian<String>(lastMoon, 7.0, DateFormat.DPDDMMYYM)
        val hourLastMoon = TimeUtil.jdToGregorian<Double>(lastMoon, 7.0, DateFormat.HOUR_DOUBLE)
        println("Last Moon: $dateLastMoon, ${ConvertUtil.toTimeFullRound2(hourLastMoon ?: 0.0)}")
    }
}