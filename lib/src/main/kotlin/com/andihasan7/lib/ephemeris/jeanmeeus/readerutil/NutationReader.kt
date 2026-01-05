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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.readerutil

import kotlin.math.cos
import kotlin.math.sin

object NutationReader {
    
    /**
    * function to read nutation in longitude terms for model IAU 1980
    *
    * @param t is the same as jce
    * @param d Mean Elongation of the Moon from the Sun, D in radian
    * @param m Mean Anomaly of the Sun (Earth), M in radian
    * @param m1 Mean Anomaly of the Moon, M' in radian
    * @param f Mean argument of the latitude of the Moon, F in radian
    * @param omega Mean Longitude of the Ascending Node of the Moon, omega in radian
    * @param arrayDoubleArray object of array
    *
    * @return nutationInLongitude (deltaPsi) the unit is 0.0000001s
    */
    fun nutationInLongitudeReader(t: Double, d: Double, m: Double, m1: Double, f: Double, omega: Double, arrayDoubleArray: Array<DoubleArray>): Double {

        var totalCoefficients = 0.0

        for (row in arrayDoubleArray) {
            totalCoefficients += (row[0] + row[1] * t) * sin(row[2] * d + row[3] * m + row[4] * m1 + row[5] * f + row[6] * omega)
        }

        return totalCoefficients / 10000.0
    }

    /**
    * function to read nutation in obliquity terms for model IAU 1980
    *
    * @param t is the same as jce
    * @param d Mean Elongation of the Moon from the Sun, D in radian
    * @param m Mean Anomaly of the Sun (Earth), M in radian
    * @param m1 Mean Anomaly of the Moon, M' in radian
    * @param f Mean argument of the latitude of the Moon, F in radian
    * @param omega Mean Longitude of the Ascending Node of the Moon, omega in radian
    * @param arrayDoubleArray object of array
    *
    * @return nutationInObliquity (deltaEpsilon) the unit is 0.0000001s
    */
    fun nutationInObliquityReader(t: Double, d: Double, m: Double, m1: Double, f: Double, omega: Double, arrayDoubleArray: Array<DoubleArray>): Double {

        var totalCoefficients = 0.0

        for (row in arrayDoubleArray) {
            totalCoefficients += (row[0] + row[1] * t) * cos(row[2] * d + row[3] * m + row[4] * m1 + row[5] * f + row[6] * omega)
        }

        return totalCoefficients / 10000.0
    }

}
