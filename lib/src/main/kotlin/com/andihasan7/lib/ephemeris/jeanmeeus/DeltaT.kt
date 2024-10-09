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

import kotlin.math.pow

/**
* deltaT
* input data masehi
* 
* @param {date, month, year}
* @return deltaT
*/
fun deltaT(date: Int, month: Int, year: Int): Double {
    
    var u: Double
    var korC: Double
    var deltaT: Double
    
    val y = year + (month - 1).toDouble() / 12 + (date).toDouble() / 365
    
    when {
        
        y <= -500 -> {
            u = (y - 1820) / 100
            deltaT = -20 + 32 * u.pow(2)
        }
        
        y > -500 && y <= 500 -> {
            u = y / 100
            deltaT = 10583.6 - 1014.41 * u + 33.78311 * u.pow(2) - 5.952053 * u.pow(3) - 0.1798452 * u.pow(4) + 0.022174192 * u.pow(5) + 0.0090316521 * u.pow(6)
        }
        
        y > 500 && y <= 1600 -> {
            u = (y - 1000) / 100
            deltaT = 1574.2 - 556.01 * u + 71.23472 * u.pow(2) + 0.319781 * u.pow(3) - 0.8503463 * u.pow(4) - 0.005050998 * u.pow(5) + 0.0083572073 * u.pow(6)
        }
        
        y > 1600 && y <= 1700 -> {
            u = (y - 1600)
            deltaT = 120 - 0.9808 * u - 0.01532 * u.pow(2) + u.pow(3) / 7129
        }
        
        y > 1700 && y <= 1800 -> {
            u = (y - 1700)
            deltaT = 8.83 + 0.1603 * u - 0.0059285 * u.pow(2) + 0.00013336 * u.pow(3) - u.pow(4) / 1174000
        }
        
        y > 1800 && y <= 1860 -> {
            u = (y - 1800)
            deltaT = 13.72 - 0.332447 * u + 0.0068612 * u.pow(2) + 0.0041116 * u.pow(3) - 0.00037436 * u.pow(4) + 0.0000121272 * u.pow(5) - 0.0000001699 * u.pow(6) + 0.000000000875 * u.pow(7)
        }
        
        y > 1860 && y <= 1900 -> {
            u = (y - 1860)
            deltaT = 7.62 + 0.5737 * u - 0.251754 * u.pow(2) + 0.01680668 * u.pow(3) - 0.0004473624 * u.pow(4) + u.pow(5) / 233174
        }
        
        y > 1900 && y <= 1920 -> {
            u = (y - 1900)
            deltaT = -2.79 + 1.494119 * u - 0.0598939 * u.pow(2) + 0.0061966 * u.pow(3) - 0.000197 * u.pow(4)
        }
        
        y > 1920 && y <= 1941 -> {
            u = (y - 1920)
            deltaT = 21.20 + 0.84493 * u - 0.076100 * u.pow(2) + 0.0020936 * u.pow(3)
        }
        
        y > 1941 && y <= 1961 -> {
            u = (y - 1950)
            deltaT = 29.07 + 0.407 * u - u.pow(2) / 233 + u.pow(3) / 2547
        }
        
        y > 1961 && y <= 1986 -> {
            u = (y - 1975)
            deltaT = 45.45 + 1.067 * u - u.pow(2) / 260 - u.pow(3) / 718
        }
        
        y > 1986 && y <= 2005 -> {
            u = (y - 2000)
            deltaT = 63.86 + 0.3345 * u - 0.060374 * u.pow(2) + 0.0017275 * u.pow(3) + 0.000651814 * u.pow(4) + 0.00002373599 * u.pow(5)
        }
        
        y > 2005 && y <= 2015 -> {
            u = (y - 2005)
            deltaT = 64.69 + 0.2930 * u
        }
        
        y > 2015 && y <= 3000 -> {
            u = (y - 2015)
            deltaT = 67.62 + 0.3645 * u + 0.0039755 * u.pow(2)
        }
        
        /* rumus lama
        y > 2005 && y <= 2050 -> {
            u = y - 2000
            deltaT = 62.92 + 0.32217 * u + 0.005589 * u.pow(2)
        }
        
        y > 2050 && y <= 2150 -> {
            deltaT = -20 + 32 * ((y-1820)/100).pow(2) - 0.5628 * (2150 - y)
        }
        
        y > 2150 -> {
            u = (y - 1820) / 100
            deltaT = -20 + 32 * u.pow(2)
        }
        */
        
        else -> {
            deltaT = 0.0
        }
        
        
    }
    
    if (y < 1955 || y > 2005) {
        korC = -0.000012932 * (y - 1955).pow(2)
        deltaT = deltaT + korC
    } else {
        deltaT = deltaT
    }
    
    
    return deltaT
}
