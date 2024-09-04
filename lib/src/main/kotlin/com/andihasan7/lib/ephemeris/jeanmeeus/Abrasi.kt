package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.sin

/**
* Abrasi
*
*/
object Abrasi {
    
    /**
    * abrasi 
    * @param {tau: Double, radiusVectorDistanceAU: Double}
    * @return abr // dalam satuan arcseconds (detik busur)
    *
    */
    fun abrasi(tau: Double, r: Double): Double {
        
        val deltaLambda = 3548.330
            + 118.568 * sin(87.5287 + 359993.7286 * tau)
            + 2.476 * sin(85.0561 + 719987.4571 * tau)
            + 1.376 * sin(27.8502 + 4452671.1152 * tau)
            + 0.119 * sin(73.1375 + 450368.8564 * tau)
        
        val abr = -0.005775518 * r * deltaLambda
        
        return abr
    }
}