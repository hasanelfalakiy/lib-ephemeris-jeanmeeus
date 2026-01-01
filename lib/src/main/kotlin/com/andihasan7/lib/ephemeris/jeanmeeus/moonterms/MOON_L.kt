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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.moonterms

/**
* Terms for moon longitude, L
*/
object MOON_L {
	// format: D, M, MA(M'), F, koef 
	val moon_L = arrayOf(
		doubleArrayOf(0.0, 0.0, 1.0, 0.0, 6288774.0),
        doubleArrayOf(2.0, 0.0, -1.0, 0.0, 1274027.0),
        doubleArrayOf(2.0, 0.0, 0.0, 0.0, 658314.0),
        doubleArrayOf(0.0, 0.0, 2.0, 0.0, 213618.0),
        doubleArrayOf(0.0, 1.0, 0.0, 0.0, -185116.0),
        doubleArrayOf(0.0, 0.0, 0.0, 2.0, -114332.0),
        doubleArrayOf(2.0, 0.0, -2.0, 0.0, 58793.0),
        doubleArrayOf(2.0, -1.0, -1.0, 0.0, 57066.0),
        doubleArrayOf(2.0, 0.0, 1.0, 0.0, 53322.0),
        doubleArrayOf(2.0, -1.0, 0.0, 0.0, 45758.0),
        doubleArrayOf(0.0, 1.0, -1.0, 0.0, -40923.0),
        doubleArrayOf(1.0, 0.0, 0.0, 0.0, -34720.0),
        doubleArrayOf(0.0, 1.0, 1.0, 0.0, -30383.0),
        doubleArrayOf(2.0, 0.0, 0.0, -2.0, 15327.0),
        doubleArrayOf(0.0, 0.0, 1.0, 2.0, -12528.0),
        doubleArrayOf(0.0, 0.0, 1.0, -2.0, 10980.0),
        doubleArrayOf(4.0, 0.0, -1.0, 0.0, 10675.0),
        doubleArrayOf(0.0, 0.0, 3.0, 0.0, 10034.0),
        doubleArrayOf(4.0, 0.0, -2.0, 0.0, 8548.0),
        doubleArrayOf(2.0, 1.0, -1.0, 0.0, -7888.0),
        doubleArrayOf(2.0, 1.0, 0.0, 0.0, -6766.0),
        doubleArrayOf(1.0, 0.0, -1.0, 0.0, -5163.0),
        doubleArrayOf(1.0, 1.0, 0.0, 0.0, 4987.0),
        doubleArrayOf(2.0, -1.0, 1.0, 0.0, 4036.0),
        doubleArrayOf(2.0, 0.0, 2.0, 0.0, 3994.0),
        doubleArrayOf(4.0, 0.0, 0.0, 0.0, 3861.0),
        doubleArrayOf(2.0, 0.0, -3.0, 0.0, 3665.0),
        doubleArrayOf(0.0, 1.0, -2.0, 0.0, -2689.0),
        doubleArrayOf(2.0, 0.0, -1.0, 2.0, -2602.0),
        doubleArrayOf(2.0, -1.0, -2.0, 0.0, 2390.0),
        doubleArrayOf(1.0, 0.0, 1.0, 0.0, -2348.0),
        doubleArrayOf(2.0, -2.0, 0.0, 0.0, 2236.0),
        doubleArrayOf(0.0, 1.0, 2.0, 0.0, -2120.0),
        doubleArrayOf(0.0, 2.0, 0.0, 0.0, -2069.0),
        doubleArrayOf(2.0, -2.0, -1.0, 0.0, 2048.0),
        doubleArrayOf(2.0, 0.0, 1.0, -2.0, -1773.0),
        doubleArrayOf(2.0, 0.0, 0.0, 2.0, -1595.0),
        doubleArrayOf(4.0, -1.0, -1.0, 0.0, 1215.0),
        doubleArrayOf(0.0, 0.0, 2.0, 2.0, -1110.0),
        doubleArrayOf(3.0, 0.0, -1.0, 0.0, -892.0),
        doubleArrayOf(2.0, 1.0, 1.0, 0.0, -810.0),
        doubleArrayOf(4.0, -1.0, -2.0, 0.0, 759.0),
        doubleArrayOf(0.0, 2.0, -1.0, 0.0, -713.0),
        doubleArrayOf(2.0, 2.0, -1.0, 0.0, -700.0),
        doubleArrayOf(2.0, 1.0, -2.0, 0.0, 691.0),
        doubleArrayOf(2.0, -1.0, 0.0, -2.0, 596.0),
        doubleArrayOf(4.0, 0.0, 1.0, 0.0, 549.0),
        doubleArrayOf(0.0, 0.0, 4.0, 0.0, 537.0),
        doubleArrayOf(4.0, -1.0, 0.0, 0.0, 520.0),
        doubleArrayOf(1.0, 0.0, -2.0, 0.0, -487.0),
        doubleArrayOf(2.0, 1.0, 0.0, -2.0, -399.0),
        doubleArrayOf(0.0, 0.0, 2.0, -2.0, -381.0),
        doubleArrayOf(1.0, 1.0, 1.0, 0.0, 351.0),
        doubleArrayOf(3.0, 0.0, -2.0, 0.0, -340.0),
        doubleArrayOf(4.0, 0.0, -3.0, 0.0, 330.0),
        doubleArrayOf(2.0, -1.0, 2.0, 0.0, 327.0),
        doubleArrayOf(0.0, 2.0, 1.0, 0.0, -323.0),
        doubleArrayOf(1.0, 1.0, -1.0, 0.0, 299.0),
        doubleArrayOf(2.0, 0.0, 3.0, 0.0, 294.0)
	)
}
