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

import kotlin.math.sin
import kotlin.math.pow

/**
* Abrasi
*
*/
object Abrasi {
    
    /**
    * abrasi 
    * @param {tau: Double, r: earth radius vector distance AU: Double}
    * @return abr // dalam satuan arcseconds (detik busur)
    *
    */
    fun abrasi(tau: Double, r: Double): Double {
        
        val deltaLambda = 3548.330
            + 118.568 * sin(87.5287 + 359993.7286 * tau)
            + 2.476 * sin(85.0561 + 719987.4571 * tau)
            + 1.376 * sin(27.8502 + 4452671.1152 * tau)
            + 0.119 * sin(73.1375 + 450368.8564 * tau)
            + 0.114 * sin(337.2264 + 329644.6718 * tau)
            + 0.086 * sin(222.5400 + 659289.3436 * tau)
            + 0.078 * sin(162.8136 + 9224659.7915 * tau) 
            + 0.054 * sin(82.5823 + 1079981.1857 * tau)
            + 0.052 * sin(171.5189 + 225184.4282 * tau) 
            + 0.034 * sin(30.3214 + 4092677.3866 * tau)
            + 0.033 * sin(119.8105 + 337181.4711 * tau)
            + 0.023 * sin(247.5418 + 299295.6151 * tau) 
            + 0.023 * sin(325.1526 + 315559.5560 * tau)
            + 0.021 * sin(155.1241 + 675553.2846 * tau)
            + 7.311 * tau * sin(333.4515 + 359993.7286 * tau)
            + 0.305 * tau * sin(330.9814 + 719987.4571 * tau)
            + 0.010 * tau * sin(328.5170 + 1079981.1857 * tau)
            + 0.309 * tau.pow(2) * sin(241.4518 + 359993.7286 * tau)
            + 0.021 * tau.pow(2) * sin(205.0482 + 719987.4571 * tau)
            + 0.004 * tau.pow(2) * sin(297.8610 + 4452671.1152 * tau)
            + 0.010 * tau.pow(3) * sin(154.7066 + 359993.7286 * tau)
        
        val abr = -0.005775518 * r * deltaLambda
        
        return abr
    }
}