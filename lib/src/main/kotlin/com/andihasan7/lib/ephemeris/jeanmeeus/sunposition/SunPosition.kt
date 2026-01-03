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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.sunposition

import com.andihasan7.lib.ephemeris.jeanmeeus.earthposition.EarthPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import kotlin.mod
import kotlin.math.*

object SunPosition {
	
	// Sun Geocentric Coordinate
    
    /**
    * Sun True Geocentric Longitude FK5 System default in degree, theta
    *
    * @param jd Julian Day
    * @param deltaT in second
    * @param unitType degree or radian
    *
    * @return sunTrueGeocentricLongitude FK5 System degree or radian
    */
    fun sunTrueGeocentricLongitude(jd: Double, deltaT: Double = 0.0, unitType: UnitType = UnitType.DEGREES): Double {
        
        val earthHeliocentricLongitude = EarthPosition.earthHeliocentricLongitude(jd, deltaT, UnitType.DEGREES)
        val earthHeliocentricLatitude = EarthPosition.earthHeliocentricLatitude(jd, deltaT, UnitType.DEGREES)
        val jce = TimeUtil.julianType(jd, deltaT, JulianType.JCE)
        val sunTrueGeocentricLatitude = -earthHeliocentricLatitude
        val thetaZero = (earthHeliocentricLongitude + 180).mod(360.0)
        val lambdaP = thetaZero - 1.397 * jce - 0.00031 * jce.pow(2)
        val deltaTheta = (-0.09033 + 0.03916 * (cos(Math.toRadians(lambdaP)) + sin(Math.toRadians(lambdaP))) * tan(sunTrueGeocentricLatitude)) / 3600.0
        
        val sunTrueGeocentricLongitude = (thetaZero + deltaTheta).mod(360.0)
        
        return when (unitType) {
            UnitType.DEGREES -> sunTrueGeocentricLongitude
            UnitType.RADIANS -> (Math.toRadians(sunTrueGeocentricLongitude))
        }
    }
    
	
	
	
	
	
	
	
	
}