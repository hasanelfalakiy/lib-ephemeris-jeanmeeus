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
* Terms for moon latitude, B
*/
object MOON_B {
	// format: D, M, MA(M'), F, koef
	val moon_B = arrayOf(
		doubleArrayOf(0.0, 0.0, 0.0, 1.0, 5128122.0),
        doubleArrayOf(0.0, 0.0, 1.0, 1.0, 280602.0),
        doubleArrayOf(0.0, 0.0, 1.0, -1.0, 277693.0),
        doubleArrayOf(2.0, 0.0, 0.0, -1.0, 173237.0),
        doubleArrayOf(2.0, 0.0, -1.0, 1.0, 55413.0),
        doubleArrayOf(2.0, 0.0, -1.0, -1.0, 46271.0),
        doubleArrayOf(2.0, 0.0, 0.0, 1.0, 32573.0),
        doubleArrayOf(0.0, 0.0, 2.0, 1.0, 17198.0),
        doubleArrayOf(2.0, 0.0, 1.0, -1.0, 9266.0),
        doubleArrayOf(0.0, 0.0, 2.0, -1.0, 8822.0),
        doubleArrayOf(2.0, -1.0, 0.0, -1.0, 8216.0),
        doubleArrayOf(2.0, 0.0, -2.0, -1.0, 4324.0),
        doubleArrayOf(2.0, 0.0, 1.0, 1.0, 4200.0),
        doubleArrayOf(2.0, 1.0, 0.0, -1.0, -3359.0),
        doubleArrayOf(2.0, -1.0, -1.0, 1.0, 2463.0),
        doubleArrayOf(2.0, -1.0, 0.0, 1.0, 2211.0),
        doubleArrayOf(2.0, -1.0, -1.0, -1.0, 2065.0),
        doubleArrayOf(0.0, 1.0, -1.0, -1.0, -1870.0),
        doubleArrayOf(4.0, 0.0, -1.0, -1.0, 1828.0),
        doubleArrayOf(0.0, 1.0, 0.0, 1.0, -1794.0),
        doubleArrayOf(0.0, 0.0, 0.0, 3.0, -1749.0),
        doubleArrayOf(0.0, 1.0, -1.0, 1.0, -1565.0),
        doubleArrayOf(1.0, 0.0, 0.0, 1.0, -1491.0),
        doubleArrayOf(0.0, 1.0, 1.0, 1.0, -1475.0),
        doubleArrayOf(0.0, 1.0, 1.0, -1.0, -1410.0),
        doubleArrayOf(0.0, 1.0, 0.0, -1.0, -1344.0),
        doubleArrayOf(1.0, 0.0, 0.0, -1.0, -1335.0),
        doubleArrayOf(0.0, 0.0, 3.0, 1.0, 1107.0),
        doubleArrayOf(4.0, 0.0, 0.0, -1.0, 1021.0),
        doubleArrayOf(4.0, 0.0, -1.0, 1.0, 833.0),
        doubleArrayOf(0.0, 0.0, 1.0, -3.0, 777.0),
        doubleArrayOf(4.0, 0.0, -2.0, 1.0, 671.0),
        doubleArrayOf(2.0, 0.0, 0.0, -3.0, 607.0),
        doubleArrayOf(2.0, 0.0, 2.0, -1.0, 596.0),
        doubleArrayOf(2.0, -1.0, 1.0, -1.0, 491.0),
        doubleArrayOf(2.0, 0.0, -2.0, 1.0, -451.0),
        doubleArrayOf(0.0, 0.0, 3.0, -1.0, 439.0),
        doubleArrayOf(2.0, 0.0, 2.0, 1.0, 422.0),
        doubleArrayOf(2.0, 0.0, -3.0, -1.0, 421.0),
        doubleArrayOf(2.0, 1.0, -1.0, 1.0, -366.0),
        doubleArrayOf(2.0, 1.0, 0.0, 1.0, -351.0),
        doubleArrayOf(4.0, 0.0, 0.0, 1.0, 331.0),
        doubleArrayOf(2.0, -1.0, 1.0, 1.0, 315.0),
        doubleArrayOf(2.0, -2.0, 0.0, -1.0, 302.0),
        doubleArrayOf(0.0, 0.0, 1.0, 3.0, -283.0),
        doubleArrayOf(2.0, 1.0, 1.0, -1.0, -229.0),
        doubleArrayOf(1.0, 1.0, 0.0, -1.0, 223.0),
        doubleArrayOf(1.0, 1.0, 0.0, 1.0, 223.0),
        doubleArrayOf(0.0, 1.0, -2.0, -1.0, -220.0),
        doubleArrayOf(2.0, 1.0, -1.0, -1.0, -220.0),
        doubleArrayOf(1.0, 0.0, 1.0, 1.0, -185.0),
        doubleArrayOf(2.0, -1.0, -2.0, -1.0, 181.0),
        doubleArrayOf(0.0, 1.0, 2.0, 1.0, -177.0),
        doubleArrayOf(4.0, 0.0, -2.0, -1.0, 176.0),
        doubleArrayOf(4.0, -1.0, -1.0, -1.0, 166.0),
        doubleArrayOf(1.0, 0.0, 1.0, -1.0, -164.0),
        doubleArrayOf(4.0, 0.0, 1.0, -1.0, 132.0),
        doubleArrayOf(1.0, 0.0, -1.0, -1.0, -119.0),
        doubleArrayOf(4.0, -1.0, 0.0, -1.0, 115.0),
        doubleArrayOf(2.0, -2.0, 0.0, 1.0, 107.0)
        
	)
}
