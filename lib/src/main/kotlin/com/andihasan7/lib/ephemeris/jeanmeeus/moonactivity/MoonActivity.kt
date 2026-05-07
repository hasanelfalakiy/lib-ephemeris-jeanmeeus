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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.moonactivity

import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DateFormat
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonActivityType
import com.andihasan7.lib.ephemeris.jeanmeeus.moonposition.MoonPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.Nutation
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.DeltaT
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.abs
import kotlin.mod
 
 
object MoonActivity {
    
   /**
   * Moon Rise, Set, and Transit
   * this formula is taken from hisab astronomis that developed by Ust. Abu Sabda that from Astronomical Algorithm and Explanatory Supplement to The Astronomical Almanac
   *
   * @param date gregorian date
   * @param month gregorian month
   * @param year gregorian year
   * @param longitude of observer
   * @param latitude of observer
   * @param elevation of observer
   * @param timeZone of observer
   * @param maxLoop maximal loop/iterations
   * @param moonActivityType RISE, SET, or TRANSIT reference to MoonActivityType
   *
   * @return ttrs reference to MoonActivityType
   */
   fun moonActivity(
       date: Int,
       month: Int,
       year: Int,
       longitude: Double,
       latitude: Double,
       elevation: Double,
       timeZone: Double,
       maxLoop: Int,
       moonActivityType: MoonActivityType
   ): Double? {
        
       var jd0LT: Double
       var jd0UT: Double
       var jde0UT: Double
       var alphaMm1d: Double
       var alphaM00d: Double
       var alphaMp1d: Double
       var deltaMm1d: Double
       var deltaM00d: Double
       var deltaMp1d: Double
       var pi: Double
       var h0: Double
       var cosHA0: Double
       var ha0: Double
       var t: Double
       var theta0: Double
       var m: Double
       var sTheta0: Double = 0.0
       var nT: Double
       var alphaM: Double = 0.0
       var deltaM: Double = 0.0
       var ha: Double
       var h: Double
       var dltm: Double
       var jdTRS: Double
       var ttrs: Double? = 0.0
       
       jd0UT = TimeUtil.gregorianToJD(date, month, year, timeZone, timeZone) + -1
        
       for (di in 1..3) {
           jde0UT = jd0UT + DeltaT.deltaT(jd0UT) / 86400.0
           alphaM00d = MoonPosition.moonAppaGeocentricRightAscension(jde0UT)
           alphaMm1d = MoonPosition.moonAppaGeocentricRightAscension((jde0UT - 1))
           alphaMp1d = MoonPosition.moonAppaGeocentricRightAscension((jde0UT + 1))
            
           if (moonActivityType == MoonActivityType.TRANSIT) {
               deltaM00d = 0.0
               deltaMm1d = 0.0
               deltaMp1d = 0.0
           } else {
               deltaM00d = MoonPosition.moonAppaGeocentricDeclination(jde0UT)
               deltaMm1d = MoonPosition.moonAppaGeocentricDeclination((jde0UT - 1))
               deltaMp1d = MoonPosition.moonAppaGeocentricDeclination((jde0UT + 1))
           }
            
           pi = MoonPosition.moonEquatorialHorizontalParallax(jde0UT)
            
           // h0 = -(34.0 / 60) + 0.7275 * pi - 0.0353 * sqrt(elevation)
           h0 = 0.7275 * pi - (34.0 / 60)
           cosHA0 = (sin(Math.toRadians(h0)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(deltaM00d))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(deltaM00d)))
            
           if (abs(cosHA0) <= 1.0) {
               ha0 = Math.toDegrees(acos(cosHA0))
           } else {
               ha0 = Double.NaN
           }
            
           t = (jde0UT - 2451545) / 36525.0
            
           theta0 = ((100.46061837) + (36000.770053608 * t) + (0.000387933 * t.pow(2)) - (t.pow(3) / 38710000) + (Nutation.nutationInLonAndObliquity(jde0UT)[0] * cos(Math.toRadians(Nutation.trueObliquityOfEcliptic(jde0UT, 0.0))))).mod(360.0)
           m = (alphaM00d - longitude - theta0) / 360.0
            
           when (moonActivityType) {
               MoonActivityType.TRANSIT -> m = m.mod(1.0)
               MoonActivityType.RISE -> m = (m - ha0 / 360.0).mod(1.0)
               MoonActivityType.SET -> m = (m + ha0 / 360.0).mod(1.0)
           }
            
           for (i in 1..maxLoop) {
               sTheta0 = (theta0 + 360.985647 * m).mod(360.0)
               nT = m
               alphaM = (alphaM00d + nT / 2.0 * ((alphaM00d - alphaMm1d).mod(360.0) + (alphaMp1d - alphaM00d).mod(360.0) + nT * ((alphaMp1d - alphaM00d).mod(360.0) - (alphaM00d - alphaMm1d).mod(360.0)))).mod(360.0)
                
               if (moonActivityType == MoonActivityType.TRANSIT) {
                   deltaM = 0.0
               } else {
                   deltaM = deltaM00d + nT / 2.0 * ((deltaM00d - deltaMm1d) + (deltaMp1d - deltaM00d) + nT * ((deltaMp1d - deltaM00d) - (deltaM00d - deltaMm1d)))
               }
                
               ha = (sTheta0 + longitude - alphaM).mod(360.0)
               if (ha > 180.0) {
                   ha = ha - 360.0
               } else {
                   ha = ha
               }
                
               h = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(deltaM)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(deltaM)) * cos(Math.toRadians(ha))))
                
               when (moonActivityType) {
                   MoonActivityType.TRANSIT -> dltm = -ha / 360.0
                   MoonActivityType.RISE, MoonActivityType.SET -> dltm = (h - h0) / (360.0 * cos(Math.toRadians(deltaM)) * cos(Math.toRadians(latitude)) * sin(Math.toRadians(ha)))
               }
                
               m = (m + dltm).mod(1.0)
           }
            
           jdTRS = jd0UT + m
           jd0LT = TimeUtil.gregorianToJD(date, month, year, 0.0, timeZone)
           ttrs = TimeUtil.jdToGregorian<Double>(jdTRS, timeZone, DateFormat.HOUR_DOUBLE)
            
           if ((jdTRS >= (jd0LT + 0)) && (jdTRS <= (jd0LT + 1))) {
               ttrs = TimeUtil.jdToGregorian<Double>(jdTRS, timeZone, DateFormat.HOUR_DOUBLE) ?: 0.0
           } else {
               jd0UT = jd0UT + 1
               ttrs = Double.NaN ?: 0.0
           }
            
       }
        
       return ttrs
        
   }
}
 