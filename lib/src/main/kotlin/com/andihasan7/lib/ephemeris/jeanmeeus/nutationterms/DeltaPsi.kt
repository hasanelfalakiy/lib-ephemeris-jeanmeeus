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

package com.andihasan7.lib.ephemeris.jeanmeeus.nutationterms

/**
* Nutasi, total koreksi 101
*/
object DeltaPsi {
    
    /**
    * deltaPsi: arrayOf<DoubleArray>
    */
    val deltaPsi = arrayOf(
		
        // (coeff1 + coeff2 * t) * sin(D * d + M * m + M' * m1 + F * f + OMEGA * omega)
		// format: coeff1, coeff2, D, M, M', F, Omega
        doubleArrayOf(-171996.0, -174.2, 0.0, 0.0, 0.0, 0.0, 1.0),
        doubleArrayOf(-13187.0, -1.6, -2.0, 0.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-2274.0, -0.2, 0.0, 0.0, 0.0, 2.0, 2.0),
        doubleArrayOf(2062.0, 0.2, 0.0, 0.0, 0.0, 0.0, 2.0),
        doubleArrayOf(1426.0, -3.4, 0.0, 1.0, 0.0, 0.0, 0.0),
        doubleArrayOf(712.0, 0.1, 0.0, 0.0, 1.0, 0.0, 0.0),
        doubleArrayOf(-517.0, 1.2, -2.0, 1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-386.0, -0.4, 0.0, 0.0, 0.0, 2.0, 1.0),
        doubleArrayOf(217.0, -0.5, -2.0, -1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(129.0, 0.1, -2.0, 0.0, 0.0, 2.0, 1.0),
        doubleArrayOf(63.0, 0.1, 0.0, 0.0, 1.0, 0.0, 1.0),
        doubleArrayOf(-58.0, -0.1, 0.0, 0.0, -1.0, 0.0, 1.0),
        doubleArrayOf(17.0, -0.1, 0.0, 2.0, 0.0, 0.0, 0.0),
        doubleArrayOf(-16.0, 0.1, -2.0, 2.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-301.0, 0.0, 0.0, 0.0, 1.0, 2.0, 2.0),
        doubleArrayOf(-158.0, 0.0, -2.0, 0.0, 1.0, 0.0, 0.0),
        doubleArrayOf(123.0, 0.0, 0.0, 0.0, -1.0, 2.0, 2.0),
        doubleArrayOf(63.0, 0.0, 2.0, 0.0, 0.0, 0.0, 0.0),
        doubleArrayOf(-59.0, 0.0, 2.0, 0.0, -1.0, 2.0, 2.0),
        doubleArrayOf(-51.0, 0.0, 0.0, 0.0, 1.0, 2.0, 1.0),
        doubleArrayOf(48.0, 0.0, -2.0, 0.0, 2.0, 0.0, 0.0),
        doubleArrayOf(46.0, 0.0, 0.0, 0.0, -2.0, 2.0, 1.0),
        doubleArrayOf(-38.0, 0.0, 2.0, 0.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-31.0, 0.0, 0.0, 0.0, 2.0, 2.0, 2.0),
        doubleArrayOf(29.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0),
        doubleArrayOf(29.0, 0.0, -2.0, 0.0, 1.0, 2.0, 2.0),
        doubleArrayOf(26.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0),
        doubleArrayOf(-22.0, 0.0, -2.0, 0.0, 0.0, 2.0, 0.0),
        doubleArrayOf(21.0, 0.0, 0.0, 0.0, -1.0, 2.0, 1.0),
        doubleArrayOf(16.0, 0.0, 2.0, 0.0, -1.0, 0.0, 1.0),
        doubleArrayOf(-15.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0),
        doubleArrayOf(-13.0, 0.0, -2.0, 0.0, 1.0, 0.0, 1.0),
        doubleArrayOf(-12.0, 0.0, 0.0, -1.0, 0.0, 0.0, 1.0),
        doubleArrayOf(11.0, 0.0, 0.0, 0.0, 2.0, -2.0, 0.0),
        doubleArrayOf(-10.0, 0.0, 2.0, 0.0, -1.0, 2.0, 1.0),
        doubleArrayOf(-8.0, 0.0, 2.0, 0.0, 1.0, 2.0, 2.0),
        doubleArrayOf(7.0, 0.0, 0.0, 1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-7.0, 0.0, -2.0, 1.0, 1.0, 0.0, 0.0),
        doubleArrayOf(-7.0, 0.0, 0.0, -1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-7.0, 0.0, 2.0, 0.0, 0.0, 2.0, 1.0),
        doubleArrayOf(6.0, 0.0, 2.0, 0.0, 1.0, 0.0, 0.0),
        doubleArrayOf(6.0, 0.0, -2.0, 0.0, 2.0, 2.0, 2.0),
        doubleArrayOf(6.0, 0.0, -2.0, 0.0, 1.0, 2.0, 1.0),
        doubleArrayOf(-6.0, 0.0, 2.0, 0.0, -2.0, 0.0, 1.0),
        doubleArrayOf(-6.0, 0.0, 2.0, 0.0, 0.0, 0.0, 1.0),
        doubleArrayOf(5.0, 0.0, 0.0, -1.0, 1.0, 0.0, 0.0),
        doubleArrayOf(-5.0, 0.0, -2.0, -1.0, 0.0, 2.0, 1.0),
        doubleArrayOf(-5.0, 0.0, -2.0, 0.0, 0.0, 0.0, 1.0),
        doubleArrayOf(-5.0, 0.0, 0.0, 0.0, 2.0, 2.0, 1.0),
        doubleArrayOf(4.0, 0.0, -2.0, 0.0, 2.0, 0.0, 1.0),
        doubleArrayOf(4.0, 0.0, -2.0, 1.0, 0.0, 2.0, 1.0),
        doubleArrayOf(4.0, 0.0, 0.0, 0.0, 1.0, -2.0, 0.0),
        doubleArrayOf(-4.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0.0),
        doubleArrayOf(-4.0, 0.0, -2.0, 1.0, 0.0, 0.0, 0.0),
        doubleArrayOf(-4.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0),
        doubleArrayOf(3.0, 0.0, 0.0, 0.0, 1.0, 2.0, 0.0),
        doubleArrayOf(-3.0, 0.0, 0.0, 0.0, -2.0, 2.0, 2.0),
        doubleArrayOf(-3.0, 0.0, -1.0, -1.0, 1.0, 0.0, 0.0),
        doubleArrayOf(-3.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0),
        doubleArrayOf(-3.0, 0.0, 0.0, -1.0, 1.0, 2.0, 2.0),
        doubleArrayOf(-3.0, 0.0, 2.0, -1.0, -1.0, 2.0, 2.0),
        doubleArrayOf(-3.0, 0.0, 0.0, 0.0, 3.0, 2.0, 2.0),
        doubleArrayOf(-3.0, 0.0, 2.0, -1.0, 0.0, 2.0, 2.0)
	)
	
	
}
