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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.earthterms

/**
* Terms for earth heliocentric radius, R
*/
object EARTH_R1 {
	// format: A, B, C
	val earth_R1 = arrayOf(
		doubleArrayOf(103019.0, 1.10749, 6283.07585),
        doubleArrayOf(1721.0, 1.0644, 12566.1517),
        doubleArrayOf(702.0, 3.142, 0.0),
        doubleArrayOf(32.0, 1.02, 18849.23),
        doubleArrayOf(31.0, 2.84, 5507.55),
        doubleArrayOf(25.0, 1.32, 5223.69),
        doubleArrayOf(18.0, 1.42, 1577.34),
        doubleArrayOf(10.0, 5.91, 10977.08),
        doubleArrayOf(9.0, 1.42, 6275.96),
        doubleArrayOf(9.0, 0.27, 5486.78)
	)
}
