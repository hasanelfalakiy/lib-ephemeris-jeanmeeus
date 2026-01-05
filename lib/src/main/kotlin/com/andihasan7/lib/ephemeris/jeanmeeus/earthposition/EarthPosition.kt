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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.earthposition

import com.andihasan7.lib.ephemeris.jeanmeeus.earthterms.*
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DistanceType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.readerutil.EarthLBRReader
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import kotlin.math.pow

object EarthPosition {
	/**
    * Earth Heliocentric Longitude L default in degrees
    * 
    * @param jd Julian Day
    * @param deltaT in arcsecond
    *
    * @return eartHeliocentricLongitude
    */
    fun earthHeliocentricLongitude(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
    
        val earthL0 = EARTH_L0.earth_L0
        val earthL1 = EARTH_L1.earth_L1
        val earthL2 = EARTH_L2.earth_L2
        val earthL3 = EARTH_L3.earth_L3
        val earthL4 = EARTH_L4.earth_L4
        val earthL5 = EARTH_L5.earth_L5
        
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JME)
        
        val l0 = EarthLBRReader.earthLBRReader(t, earthL0)
        val l1 = EarthLBRReader.earthLBRReader(t, earthL1)
        val l2 = EarthLBRReader.earthLBRReader(t, earthL2)
        val l3 = EarthLBRReader.earthLBRReader(t, earthL3)
        val l4 = EarthLBRReader.earthLBRReader(t, earthL4)
        val l5 = EarthLBRReader.earthLBRReader(t, earthL5)

        val eartHeliocentricLongitude = (l0 + l1 * t + l2 * t.pow(2) + l3 * t.pow(3) + l4 * t.pow(4) + l5 * t.pow(5)) / 100000000L // dibagi 100000000L format sedikit berbeda dengan vsop

        return when (unitType) {

            UnitType.DEGREES -> (Math.toDegrees(eartHeliocentricLongitude)).mod(360.0)
            UnitType.RADIANS -> eartHeliocentricLongitude
        }
        
    }
    
    /**
    * Earth Heliocentric Latitude B default in degrees
    * 
    * @param jd Julian Day
    * @param deltaT in arcsecond
    *
    * @return eartHeliocentricLatitude
    */
    fun earthHeliocentricLatitude(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
    
        val earthB0 = EARTH_B0.earth_B0
        val earthB1 = EARTH_B1.earth_B1
        
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JME)
        
        val b0 = EarthLBRReader.earthLBRReader(t, earthB0)
        val b1 = EarthLBRReader.earthLBRReader(t, earthB1)
        
        val eartHeliocentricLatitude = (b0 + b1 * t) / 100000000L
        
        return when (unitType) {
            UnitType.DEGREES -> Math.toDegrees(eartHeliocentricLatitude)
            UnitType.RADIANS -> eartHeliocentricLatitude
        }
    }
    
    /**
    * Earth Radius Vector R is the same as Sun Geocentric Distance in AU unit
    * 
    * @param jd, Julian Day
    * @param deltaT, in arcsecond
    *
    * @return eartRadiusVector
    */
    fun earthRadiusVector(jd: Double, deltaT: Double = 0.0, distanceType: DistanceType): Double {
    
        val earthR0 = EARTH_R0.earth_R0
        val earthR1 = EARTH_R1.earth_R1
        val earthR2 = EARTH_R2.earth_R2
        val earthR3 = EARTH_R3.earth_R3
        val earthR4 = EARTH_R4.earth_R4
        
        val t = TimeUtil.julianType(jd, deltaT, JulianType.JME)
        
        val r0 = EarthLBRReader.earthLBRReader(t, earthR0)
        val r1 = EarthLBRReader.earthLBRReader(t, earthR1)
        val r2 = EarthLBRReader.earthLBRReader(t, earthR2)
        val r3 = EarthLBRReader.earthLBRReader(t, earthR3)
        val r4 = EarthLBRReader.earthLBRReader(t, earthR4)
        
        val _earthRadiusVector = (r0 + r1 * t + r2 * t.pow(2) + r3 * t.pow(3) + r4 * t.pow(4)) / 100000000
        
        val earthRadiusVector = when (distanceType) {
            DistanceType.AU -> _earthRadiusVector
            DistanceType.KM -> {
                _earthRadiusVector * 149597870.7
            }
            DistanceType.ER -> {
                _earthRadiusVector * 149597870.7 / 6371.0
            }
        }
        
        return earthRadiusVector
    }
}
