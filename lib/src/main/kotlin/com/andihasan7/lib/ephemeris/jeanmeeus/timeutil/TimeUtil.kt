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

package com.andihasan7.lib.ephemeris.jeanmeeus.timeutil

import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DateFormat
import kotlin.math.floor

object TimeUtil {
    
    /**
    * convert Gregorian to Julian Day
    *
    * @param date
    * @param month
    * @param year
    * @param hourDouble, hour decimal
    * @param timeZone
    *
    * @return jd
    */
    fun gregorianToJD(
        date: Int,
        month: Int,
        year: Int,
        hourDouble: Double = 0.0,
        timeZone: Double = 0.0
    ): Double {
        var m: Double
        var y: Double
        var a: Double
        var b: Double
    
        val d = date + ((hourDouble - timeZone) / 24.0)
    
        if (month > 2) {
            m = month.toDouble()
            y = year.toDouble()
        } else {
            m = (month + 12).toDouble()
            y = (year - 1).toDouble()
        }
    
        val _j = (year.toDouble() + month.toDouble() / 100 + date.toDouble() / 10000)
        if (_j >= 1582.1015) {
            a = floor(y / 100)
            b = 2 - a + floor(a / 4)
        } else {
            b = 0.0
        }
    
        val jd = floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + d + b - 1524.5
    
        return jd
    }
    
    /**
    * Convert Julian Day to Gregorian
    *
    * @param jd Julian Day
    * @param timeZone
    *
    * @return DateFormat
    */
    inline fun <reified T> jdToGregorian(jd: Double, timeZone: Double, dateFormat: DateFormat): T? {
        
        val cjd = jd + 0.5 + timeZone / 24.0
        val cjdn = floor(cjd)
        val fracD = cjd - cjdn
        val alpha = floor((cjdn - 1867216.25) / 36524.25)
        val beta = 1 + alpha - floor(alpha / 4)
        val gCor = if (cjdn >= 2299161) {
            beta
        } else {
            0.0
        }
        val vA = cjdn + gCor
        val vB = vA + 1524
        val vC = floor((vB - 122.1) / 365.25)
        val vD = floor(365.25 * vC)
        val vE = floor((vB - vD) / 30.6001)
        
        val tglGreg = vB - vD - floor(30.6001 * vE)
        val blnGreg = if (vE < 14) {
            vE - 1
        } else {
            vE - 13
        }
        
        val thnGreg = if (blnGreg > 2) {
            vC - 4716
        } else {
            vC - 4715
        }
        
        val hourDouble = (fracD * 24).toDouble()
        // number of day
        val numbDay = (cjdn).toInt().mod(7)
        // number of pasaran
        val numbPasaran = (cjdn).toInt().mod(5)
        // name of day
        val nameDay = ConvertUtil.toNameDay(numbDay)
        // name of pasaran
        val namePasaran = ConvertUtil.toNamePasaran(numbPasaran)
        // name of month
        val nameMonth = ConvertUtil.toNameMonth(blnGreg.toInt())
        // DPDDMMYY format eq. Rabu Pon, 1 Januari 2025
        val dpddmmyy = "$nameDay $namePasaran, ${(tglGreg).toInt()} $nameMonth ${(thnGreg).toInt()}"
        // DPDDMMYY format eq. Rabu Pon, 1 Januari 2025 M
        val dpddmmyym = "$nameDay $namePasaran, ${(tglGreg).toInt()} $nameMonth ${(thnGreg).toInt()} M"

        return when (dateFormat) {
            DateFormat.DATE -> (tglGreg).toInt() as? T
            DateFormat.MONTH_INT -> (blnGreg).toInt() as? T
            DateFormat.MONTH_NAME -> nameMonth as? T
            DateFormat.YEAR -> (thnGreg).toInt() as? T
            DateFormat.DAY_INT -> numbDay as? T
            DateFormat.DAY_NAME -> nameDay as? T
            DateFormat.PASARAN_INT -> numbPasaran as? T
            DateFormat.PASARAN_NAME -> namePasaran as? T
            DateFormat.DPDDMMYY -> dpddmmyy as? T
            DateFormat.DPDDMMYYM -> dpddmmyym as? T
            DateFormat.HOUR_DOUBLE -> hourDouble as? T
            DateFormat.FRAC_DAY -> fracD as? T
        }
    }

    /**
    * Julian Day Ephemeris (JDE)
    *
    * @param jd
    * @param deltaT
    * @return jde
    */
    fun jde(jd: Double, deltaT: Double) = jd + (deltaT / 86400.0)

    /**
    * Julian Century (JC) for standart epoch 2000
    *
    * @param jd
    * @return jde
    */
    fun jc(jd: Double) = (jd - 2451545.0) / 36525.0

    /**
    * Julian Century Ephemeris (JCE) for standart epoch 2000
    *
    * @param jde or jd
    * @return jce
    */
    fun jce(jde: Double) = (jde - 2451545.0) / 36525.0

    /**
    * Julian Millennium (JM) for standart epoch 2000
    *
    * @param jc
    * @return jm
    */
    fun jm(_jc: Double) = _jc / 10.0

    /**
    * Julian Millennium Ephemeris (JME) for standart epoch 2000
    *
    * @param jce
    * @return jme
    */
    fun jme(_jce: Double) = _jce / 10.0
    
    /**
    * Julian Date with option type
    * 
    * @param jd
    * @param deltaT
    * @param julianType, JulianType enum class JDE, JC, JCE, JM, JME
    * 
    * @return Double JulianType enum class JDE, JC, JCE, JM, JME
    */
    fun julianType(jd: Double, deltaT: Double, julianType: JulianType): Double {
    
        val jde = jd + (deltaT / 86400.0)
        val jc = (jd - 2451545.0) / 36525.0
        val jce = (jde - 2451545.0) / 36525.0
        val jm = jc / 10.0
        val jme = jce / 10.0
        
        return when (julianType) {
            
            JulianType.JDE -> jde
            JulianType.JC -> jc
            JulianType.JCE -> jce
            JulianType.JM -> jm
            JulianType.JME -> jme
        }
    }
    
    


}