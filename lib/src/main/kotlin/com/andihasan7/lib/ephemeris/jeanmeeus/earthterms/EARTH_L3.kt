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
* Terms for earth heliocentric longitude, L
*/

object EARTH_L3 {

 // format: A, B, C	
	val earth_L3 = arrayOf(
		doubleArrayOf(289.0, 5.844, 6283.076),
        doubleArrayOf(35.0, 0.0, 0.0),
        doubleArrayOf(17.0, 5.49, 12566.15),
        doubleArrayOf(3.0, 5.2, 155.42),
        doubleArrayOf(1.0, 4.72, 3.52),
        doubleArrayOf(1.0, 5.3, 18849.23),
        doubleArrayOf(1.0, 5.97, 242.73)
	)
}