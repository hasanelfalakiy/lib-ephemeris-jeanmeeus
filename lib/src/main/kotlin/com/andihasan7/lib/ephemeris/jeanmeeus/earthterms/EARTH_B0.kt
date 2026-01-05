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
* Terms for earth heliocentric latitude, B
*/
object EARTH_B0 {
	// format: A, B, C
	val earth_B0 = arrayOf(
		doubleArrayOf(280.0, 3.199, 84334.662),
		doubleArrayOf(102.0, 5.422, 5507.553),
		doubleArrayOf(80.0, 3.88, 5223.69),
		doubleArrayOf(44.0, 3.7, 2352.87),
		doubleArrayOf(32.0, 4.0, 1577.34)
	)
}
