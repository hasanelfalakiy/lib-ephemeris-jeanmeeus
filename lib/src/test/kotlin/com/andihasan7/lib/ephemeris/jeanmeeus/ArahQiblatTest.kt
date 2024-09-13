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
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus
import com.andihasan7.lib.ephemeris.jeanmeeus.arahqiblat.ArahQiblat
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toTimeFullRound2

class ArahQiblatTest {

    @Test
    fun arahQiblatTest() {
        
        val kiblat = ArahQiblat()
        
        println("Azimuth UB : ${toDegreeFullRound2(kiblat.arahQiblat(-7.43333333334, 111.4333333333334)[0])}")
        println("Azimuth BU : ${toDegreeFullRound2(kiblat.arahQiblat(-7.43333333334, 111.4333333333334)[1])}")
        println("Azimuth UTSB : ${toDegreeFullRound2(kiblat.arahQiblat(-7.43333333334, 111.4333333333334)[2])}")
        println("")
        
    }
    
    
    
}