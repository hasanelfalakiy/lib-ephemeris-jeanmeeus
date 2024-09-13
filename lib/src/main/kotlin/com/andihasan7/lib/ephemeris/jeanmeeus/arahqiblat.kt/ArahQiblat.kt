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

package com.andihasan7.lib.ephemeris.jeanmeeus.arahqiblat

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

/**
* ArahQiblat
*/
class ArahQiblat {
    
    /**
    * arahQiblat(latitude: Double, longitude: Double): DoubleArray
    *
    * @return doubleArrayOf(azimuthUB, azimuthBU, azimuthUTSB)
    */
    fun arahQiblat(latitude: Double, longitude: Double): DoubleArray {
        
        val LATITUDEKABAH = 21.4225
        val LONGITUDEKABAH = 39.826111111111

        // Selisih azimuthBUjur

        val selisihazimuthBUjur = 360 - LONGITUDEKABAH + longitude - 360

        val h = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(LATITUDEKABAH)) + cos(Math.toRadians(latitude))  * cos(Math.toRadians(LATITUDEKABAH)) * cos(Math.toRadians(selisihazimuthBUjur))))
        
        // Azimuth U-B
        val azimuthUB = Math.toDegrees(acos((sin(Math.toRadians(LATITUDEKABAH)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(h))) / cos(Math.toRadians(latitude)) / cos(Math.toRadians(h))))

        // Azimuth B-U
        val azimuthBU = 90 - azimuthUB

        // Azimuth UTSB
        val azimuthUTSB = 360 - azimuthUB
            
        return doubleArrayOf(azimuthUB, azimuthBU, azimuthUTSB)
    }
    
    
    
}