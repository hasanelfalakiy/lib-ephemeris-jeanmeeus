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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.readerutil

import kotlin.math.cos
import kotlin.math.pow

object MoonLBRReader {
    
    /**
     * function to read moon longitude and calculate the coefficients
     *
     * @param t is the same as jme Julian Millenium Ephemeris/tau
     * @param arrayDoubleArray object of array
     *
     * @return totalCoefficients
     */
    fun moonLongitudeReader(t: Double, arrayDoubleArray: Array<DoubleArray>): Double {

        var totalCoefficients = 0.0
		
		/**
        * bujur rata-rata bulan, L`, l1
        */
        val l1_r = Math.toRadians((218.3164591 + 481267.88134236 * t - 0.0013268 * t.pow(2) + t.pow(3) / 538841 - t.pow(4) / 65194000).mod(360.0))
		
        // elongsi rata2 bulan
        val d_r = Math.toRadians((297.8502042 + 445267.1115168 * t - 0.00163 * t.pow(2) + t.pow(3) / 545868 - t.pow(4) / 113065000).mod(360.0))

        // anomali rata2 matahari
        val m_r = Math.toRadians((357.5291092 + 35999.0502909 * t - 0.0001536 * t.pow(2) + t.pow(3) / 24490000).mod(360.0))

        // anomali rata2 bulan
        val ma_r = Math.toRadians((134.9634114 + 477198.8676313 * t + 0.008997 * t.pow(2) + t.pow(3) / 69699 - t.pow(4) / 14712000).mod(360.0))

        // Argumen bujur bulan
        val f_r = Math.toRadians((93.2720993 + 483202.0175273 * t - 0.0034029 * t.pow(2) - t.pow(3) / 3526000 + t.pow(4) / 863310000).mod(360.0))

        // eksentrisitas orbit
        val e = 1 - 0.002516 * t - 0.0000074 * t.pow(2)


        for (row in arrayDoubleArray) {
            totalCoefficients += row[4] * Math.pow(e, Math.abs(row[1])) * Math.sin(row[0] * d_r + row[1] * m_r + row[2] * ma_r + row[3] * f_r)
        }
		
		val aA1_r = Math.toRadians((119.75 + 131.849 * t).mod(360.0))
        val aA2_r = Math.toRadians((53.09 + 479264.29 * t).mod(360.0))
        val aA3_r = Math.toRadians((313.45 + 481266.484 * t).mod(360.0))
        
        totalCoefficients =
            (totalCoefficients +
                3958 * Math.sin(aA1_r) +
                1962 * Math.sin(l1_r - f_r) +
                318 * Math.sin(aA2_r)) / 1000000.0

        return totalCoefficients
    }
	
	/**
     * function to read moon latitude and calculate the coefficients
     *
     * @param t is the same as jme Julian Millenium Ephemeris/tau
     * @param arrayDoubleArray object of array
     *
     * @return totalCoefficients
     */
    fun moonLatitudeReader(t: Double, arrayDoubleArray: Array<DoubleArray>): Double {
		
		var totalCoefficients = 0.0
		
		/**
        * bujur rata-rata bulan, L`, l1
        */
        val l1_r = Math.toRadians((218.3164591 + 481267.88134236 * t - 0.0013268 * t.pow(2) + t.pow(3) / 538841 - t.pow(4) / 65194000).mod(360.0))
		
        // elongsi rata2 bulan
        val d_r = Math.toRadians((297.8502042 + 445267.1115168 * t - 0.00163 * t.pow(2) + t.pow(3) / 545868 - t.pow(4) / 113065000).mod(360.0))

        // anomali rata2 matahari
        val m_r = Math.toRadians((357.5291092 + 35999.0502909 * t - 0.0001536 * t.pow(2) + t.pow(3) / 24490000).mod(360.0))

        // anomali rata2 bulan
        val ma_r = Math.toRadians((134.9634114 + 477198.8676313 * t + 0.008997 * t.pow(2) + t.pow(3) / 69699 - t.pow(4) / 14712000).mod(360.0))

        // Argumen bujur bulan
        val f_r = Math.toRadians((93.2720993 + 483202.0175273 * t - 0.0034029 * t.pow(2) - t.pow(3) / 3526000 + t.pow(4) / 863310000).mod(360.0))

        // eksentrisitas orbit
        val e = 1 - 0.002516 * t - 0.0000074 * t.pow(2)
		
		val aA1_r = Math.toRadians((119.75 + 131.849 * t).mod(360.0))
        val aA3_r = Math.toRadians((313.45 + 481266.484 * t).mod(360.0))
		
		for (row in arrayDoubleArray) {
            totalCoefficients += row[4] * Math.pow(e, Math.abs(row[1])) * Math.sin(row[0] * d_r + row[1] * m_r + row[2] * ma_r + row[3] * f_r)
        }
		
		totalCoefficients =
            (totalCoefficients - 2235 * Math.sin(l1_r) +
                382 * Math.sin(aA3_r) +
                175 * Math.sin(aA1_r - f_r) +
                175 * Math.sin(aA1_r + f_r) +
                127 * Math.sin(l1_r - ma_r) - 115 * Math.sin(l1_r + ma_r)) / 1000000.0
        
		
		return totalCoefficients
	}
	
	/**
     * function to read moon radius vector/jarak and calculate the coefficients
     *
     * @param t is the same as jme Julian Millenium Ephemeris/tau
     * @param arrayDoubleArray object of array
     *
     * @return totalCoefficients
     */
    fun moonRadiusReader(t: Double, arrayDoubleArray: Array<DoubleArray>): Double {
		
		var totalCoefficients = 0.0
		
		// elongsi rata2 bulan
        val d_r = Math.toRadians((297.8502042 + 445267.1115168 * t - 0.00163 * t.pow(2) + t.pow(3) / 545868 - t.pow(4) / 113065000).mod(360.0))

        // anomali rata2 matahari
        val m_r = Math.toRadians((357.5291092 + 35999.0502909 * t - 0.0001536 * t.pow(2) + t.pow(3) / 24490000).mod(360.0))

        // anomali rata2 bulan
        val ma_r = Math.toRadians((134.9634114 + 477198.8676313 * t + 0.008997 * t.pow(2) + t.pow(3) / 69699 - t.pow(4) / 14712000).mod(360.0))

        // Argumen bujur bulan
        val f_r = Math.toRadians((93.2720993 + 483202.0175273 * t - 0.0034029 * t.pow(2) - t.pow(3) / 3526000 + t.pow(4) / 863310000).mod(360.0))

        // eksentrisitas orbit
        val e = 1 - 0.002516 * t - 0.0000074 * t.pow(2)

		
		for (row in arrayDoubleArray) {
            totalCoefficients += row[4] * Math.pow(e, Math.abs(row[1])) * Math.cos(row[0] * d_r + row[1] * m_r + row[2] * ma_r + row[3] * f_r)
        }
		
		totalCoefficients = totalCoefficients / 1000.0
		
		return totalCoefficients
	}



}
