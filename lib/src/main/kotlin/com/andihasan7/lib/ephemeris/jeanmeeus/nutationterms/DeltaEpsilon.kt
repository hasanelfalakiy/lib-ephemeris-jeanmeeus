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

object DeltaEpsilon {
    /**
    * deltaEpsilon: arrayOf<DoubleArray>
    */
	val deltaEpsilon = arrayOf(
		
		// (coeff1 + coeff2 * t) * sin(D * d + M * m + M' * m1 + F * f + OMEGA * omega)
		// format: coeff1, coeff2, D, M, M', F, Omega
		doubleArrayOf(92025.0, 8.9, 0.0, 0.0, 0.0, 0.0, 1.0),
        doubleArrayOf(5736.0, -3.1, -2.0, 0.0, 0.0, 2.0, 2.0),
        doubleArrayOf(977.0, -0.5, 0.0, 0.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-895.0, 0.5, 0.0, 0.0, 0.0, 0.0, 2.0),
        doubleArrayOf(54.0, -0.1, 0.0, 1.0, 0.0, 0.0, 0.0),
        doubleArrayOf(224.0, -0.6, -2.0, 1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(129.0, -0.1, 0.0, 0.0, 1.0, 2.0, 2.0),
        doubleArrayOf(-95.0, 0.3, -2.0, -1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(-7.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0),
        doubleArrayOf(200.0, 0.0, 0.0, 0.0, 0.0, 2.0, 1.0),
        doubleArrayOf(-70.0, 0.0, -2.0, 0.0, 0.0, 2.0, 1.0),
        doubleArrayOf(-53.0, 0.0, 0.0, 0.0, -1.0, 2.0, 2.0),
        doubleArrayOf(-33.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0),
        doubleArrayOf(26.0, 0.0, 2.0, 0.0, -1.0, 2.0, 2.0),
        doubleArrayOf(32.0, 0.0, 0.0, 0.0, -1.0, 0.0, 1.0),
        doubleArrayOf(27.0, 0.0, 0.0, 0.0, 1.0, 2.0, 1.0),
        doubleArrayOf(-24.0, 0.0, 0.0, 0.0, -2.0, 2.0, 1.0),
        doubleArrayOf(16.0, 0.0, 2.0, 0.0, 0.0, 2.0, 2.0),
        doubleArrayOf(13.0, 0.0, 0.0, 0.0, 2.0, 2.0, 2.0),
        doubleArrayOf(-12.0, 0.0, -2.0, 0.0, 1.0, 2.0, 2.0),
        doubleArrayOf(-10.0, 0.0, 0.0, 0.0, -1.0, 2.0, 1.0),
        doubleArrayOf(-8.0, 0.0, 2.0, 0.0, -1.0, 0.0, 1.0),
        doubleArrayOf(7.0, 0.0, -2.0, 2.0, 0.0, 2.0, 2.0),
        doubleArrayOf(9.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0),
        doubleArrayOf(7.0, 0.0, -2.0, 0.0, 1.0, 0.0, 1.0),
        doubleArrayOf(6.0, 0.0, 0.0, -1.0, 0.0, 0.0, 1.0),
        doubleArrayOf(5.0, 0.0, 2.0, 0.0, -1.0, 2.0, 1.0),
        doubleArrayOf(3.0, 0.0, 2.0, 0.0, 1.0, 2.0, 2.0),
        doubleArrayOf(-3.0, 0.0, 0.0, 1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(3.0, 0.0, 0.0, -1.0, 0.0, 2.0, 2.0),
        doubleArrayOf(3.0, 0.0, 2.0, 0.0, 0.0, 2.0, 1.0),
        doubleArrayOf(-3.0, 0.0, -2.0, 0.0, 2.0, 2.0, 2.0),
        doubleArrayOf(-3.0, 0.0, -2.0, 0.0, 1.0, 2.0, 1.0),
        doubleArrayOf(3.0, 0.0, 2.0, 0.0, -2.0, 0.0, 1.0),
        doubleArrayOf(3.0, 0.0, 2.0, 0.0, 0.0, 0.0, 1.0),
        doubleArrayOf(3.0, 0.0, -2.0, -1.0, 0.0, 2.0, 1.0),
        doubleArrayOf(3.0, 0.0, -2.0, 0.0, 0.0, 0.0, 1.0),
        doubleArrayOf(3.0, 0.0, 0.0, 0.0, 2.0, 2.0, 1.0)
    )
}
