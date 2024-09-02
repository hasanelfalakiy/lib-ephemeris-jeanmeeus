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

package com.andihasan7.lib.ephemeris.jeanmeeus.util

import kotlin.math.floor

fun masehiToJD(
    date: Int,
    month: Int,
    year: Int,
    hourDouble: Double = 0.0,
    timeZone: Double = 0.0
): Double {
    var m: Double
    var y: Double
    var a: Double
    var b: Double
    
    val d = date + ((hourDouble - timeZone) / 24.0)
    
    if (month < 2) {
        m = (month + 12).toDouble()
        y = (year - 1).toDouble()
    } else {
        m = month.toDouble()
        y = year.toDouble()
    }
    
    val _j = (year.toDouble() + month.toDouble() / 100 + date.toDouble() / 10000)
    if (_j >= 1582.1015) {
        a = floor(y / 100)
        b = 2 - a + floor(a / 4)
    } else {
        b = 0.0
    }
    
    val jd = floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + d + b - 1524.5
    
    return jd
}