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

import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.nutationterms.*
import com.andihasan7.lib.ephemeris.jeanmeeus.readerutil.NutationReader
import kotlin.math.pow

/**
* Nutation
*
*/
object Nutation {
    
    /**
    * Nutation in Longitude, deltaPsi
    * Nutation of Obliquity, deltaEpsilon
    * model of IAU 1980
    *
    * @param jd Julian Day
    * @param deltaT in arc second
    *
    * @return doubleArrayOf(deltaPsi, deltaEp) degree unit
    */
    fun nutationInLonAndObliquity(jd: Double, deltaT: Double = 0.0): DoubleArray {
        
        val deltaPsiTerms = DeltaPsi.deltaPsi
        val deltaEpsilonTerms = DeltaEpsilon.deltaEpsilon
        
        // t is the same as jce
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JCE)
    
        // delaunay argument count (D, M, M1, F, omega)
        /*
        * Mean Elongation of the Moon from the Sun, D in radian
        */
        val dRad = Math.toRadians((297.85036 + 445267.111480 * t - 0.0019142 * t.pow(2) + t.pow(3) / 189474).mod(360.0))

        /*
        * Mean Anomaly of the Sun (Earth), M in radian
        */
        val mRad = Math.toRadians((357.52772 + 35999.05034 * t - 0.0001603 * t.pow(2) - t.pow(3) / 300000).mod(360.0))
        
        /*
        * Mean Anomaly of the Moon, M' in radian
        */
        val m1Rad = Math.toRadians((134.96298 + 477198.867398 * t + 0.0086972 * t.pow(2) + t.pow(3) / 56250).mod(360.0))
    
        /*
        * Mean argument of the latitude of the Moon, F in radian
        */
        val fRad = Math.toRadians((93.27191 + 483202.017538 * t - 0.0036825 * t.pow(2) + t.pow(3) / 327270).mod(360.0))
        
        /*
        * Mean Longitude of the Ascending Node of the Moon, omega in radian
        */
        val omegaRad = Math.toRadians((125.04452 - 1934.136261 * t + 0.0020708 * t.pow(2) + t.pow(3) / 450000).mod(360.0))
        
        val deltaPsi = NutationReader.nutationInLongitudeReader(
            t,
            dRad,
            mRad,
            m1Rad,
            fRad,
            omegaRad,
            deltaPsiTerms
        ) / 3600.0
        
        val deltaEpsilon = NutationReader.nutationInObliquityReader(
            t,
            dRad,
            mRad,
            m1Rad,
            fRad,
            omegaRad,
            deltaEpsilonTerms
        ) / 3600.0
        
        
        return doubleArrayOf(deltaPsi, deltaEpsilon)
    }
    
    /**
    * Mean Obliquity of Ecliptic, epsilon zero
    *
    * @param jme Julian Millenium Ephemeris
    *
    * @return mean obliquity of ecliptic
    */
    fun meanObliquityOfEcliptic(jd: Double, deltaT: Double = 0.0): Double {
        
        val u = TimeUtil.julianType(jd, deltaT, JulianType.JME) / 10.0
        val epsilonZero = 23 + 26.0 / 60 + 21.448 / 3600 + (-4680.93 * u - 1.55 * u.pow(2) + 1999.25 * u.pow(3) - 51.38 * u.pow(4) - 249.67 * u.pow(5) - 39.05 * u.pow(6) + 7.12 * u.pow(7) + 27.87 * u.pow(8) + 5.79 * u.pow(9) + 2.45 * u.pow(10)) / 3600
        
        return epsilonZero
    }
    
    /**
    * True Obliquity of Ecliptic, epsilon
    *
    * @param nutationInObliquity
    * @param meanObliquityOfEcliptic
    * 
    * @return trueObliquityOfEcliptic
    */
    fun trueObliquityOfEcliptic(jd: Double, deltaT: Double): Double {
    
        val meanObliquityOfEcliptic = meanObliquityOfEcliptic(jd, deltaT)
        val nutationInObliquity = nutationInLonAndObliquity(jd, deltaT)[1]
        
        return meanObliquityOfEcliptic + nutationInObliquity
    }
    
}
