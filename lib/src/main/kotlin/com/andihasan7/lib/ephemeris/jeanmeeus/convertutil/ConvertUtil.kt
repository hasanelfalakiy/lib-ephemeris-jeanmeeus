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

package com.andihasan7.lib.ephemeris.jeanmeeus.convertutil


import kotlin.math.abs
import kotlin.math.round
import kotlin.mod

object ConvertUtil {
    
    /**
    * Extension function to round numbers after the decimal point which can be customized
    * 
    * @param decimals
    * 
    * @return value
    */
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
    
    // SS.ss
    /**
    * function to change Double data to decimal unit form SS,ss
    * @param decimal
    * @return hasil
    */
    fun toDegreeSS2(decimal: Double): String {
        val result = decimal.round(2)

        return "$result\u2033"
    }

    /**
    * HH:MM:SS numbers rounded to seconds
    * function to change Double data to hours, minutes, seconds of type Int Array
    * index 0 : hours, index 1 : minutes, index 2 : seconds
    *
    * @param decimal
    *
    * @return intArrayOf(time, minute, second)
    */
    fun toTimeFullRound(decimal: Double): IntArray {
        var time = abs(decimal).toInt()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt()
        var second = (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)).toInt()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toInt() == 60) {
            second = second.toInt() - 60
            minute = minute.toInt() + 1
        }

        if (minute.toInt() == 60) {
            minute = minute.toInt() - 60
            time = time.toInt() + 1
        }

        if (decimal < 0) {
            time = time - time * 2
            minute = minute - minute * 2
            second = second - second * 2
        }

        return intArrayOf(time, minute, second)
    }
    
    
    /**
    * HH:MM:SS numbers rounded to seconds
    * function to change Double data to HH:MM:SS format, seconds rounded to minutes, minutes rounded to hours
    * @param decimal
    * 
    * @return String
    */
    fun toTimeFullRoundSec(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60))
                .toInt()
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toInt() == 60) {
            second = (second.toInt() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
            time = (time.toInt() + 1).toString()
        }

        // Add zero before numbers less than 10
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            time = "-$time"
        }

        return "$time:$minute:$second"
    }
    
    /**
    * HH:MM:SS,ss rounded to 2 digits after the decimal point
    * function to change decimal data to HH:MM:SS,ss format, rounded to 2 digits behind the comma, seconds rounded to minutes, minutes rounded to hours
    * @param decimal
    * @return String
    */
    fun toTimeFullRound2(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
                .round(2)
                .toString()

        // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit == 60
        if (second.toDouble() == 60.0) {
            second = (second.toDouble() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
            time = (time.toInt() + 1).toString()
        }

        // Tambahkan nol sebelum angka yang kurang dari 10
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            time = "-$time"
        }

        return "$time:$minute:$second"
    }
    
    /**
    * MM m SS s numbers rounded to seconds
    * function to change double data to MM m SS s format seconds rounded to minutes
    * @param decimal
    * @return String
    */
    fun toCounterMMSS(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60))
                .toInt()
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toInt() == 60) {
            second = (second.toInt() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
        }

        // Add zero before numbers less than 10
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            minute = "-$minute"
        }

        return "$minute\u006d $second\u0073"
    }
    
    /**
    * MM m SS,ss s rounded to 2 digits after the decimal point
    * function to change decimal data to MM m SS,ss s format, rounded to 2 digits behind the comma, seconds rounded to minutes
    * @param decimal
    * @return String
    */
    fun toCounterMMSS2(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
                .round(2)
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toDouble() == 60.0) {
            second = (second.toDouble() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
        }

        // Add zero before numbers less than 10
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            minute = "-$minute"
        }

        return "$minute\u006d $second\u0073"
    }
    
    /**
    * HH h MM m SS,ss s rounded to 2 digits after the decimal point
    * function to change decimal data to HH h MM m SS,ss s format, rounding to 2 digits behind the comma, seconds rounded to minutes, minutes to hours
    * @param decimal
    * @return String
    */
    fun toCounterHHMMSS2(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
                .round(2)
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toDouble() == 60.0) {
            second = (second.toDouble() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
            time = (time.toInt() + 1).toString()
        }

        // Add zero before numbers less than 10
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            time = "-$time"
        }

        return "$time\u0068 $minute\u006d $second\u0073"
    }
    
    /**
    * format 24 h
    * HH h MM m SS,ss s rounded to 2 (by default) and can custom digits after the decimal point
    * function to change decimal data to HH h MM m SS,ss s format, rounding to 2 digits behind the comma, seconds rounded to minutes, minutes to hours
    * @param decimal
    * @return String
    */
    fun toCounterHHMMSS24(decimal: Double, round: Int = 2): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
                .round(round)
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toDouble() == 60.0) {
            second = (second.toDouble() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
            time = (time.toInt() + 1).toString()
        }
        
        // time = time.toDouble().mod(24.0).toInt().toString()

        // Add zero before numbers less than 10
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(3, '0')
        
        

        return "$time\u0068 $minute\u006d $second\u0073"
    }
    
    /**
    * MM` SS,ss`` rounded to 2 digits after the decimal point
    * function to change decimal data to MM' SS,ss" format, seconds are rounded to 2 digits after the decimal point, seconds are rounded to minutes
    * @param decimal
    * @return String
    */
    fun toDegreeMMSS2(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
                .round(2)
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toDouble() == 60.0) {
            second = (second.toDouble() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
        }

        // Add zero before numbers less than 10
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            minute = "-$minute"
        }

        return "$minute\u2032 $second\u2033"
    }

    /**
    * DD° MM` SS,ss`` rounded to 2 decimal places
    * function to change decimal data to DD° MM' SS,ss" format, seconds rounded to 2 digits after the decimal point, seconds rounded to minutes, minutes rounded to hours
    * @param decimal
    * @return String
    */
    fun toDegreeFullRound2(decimal: Double): String {
        var time = abs(decimal).toInt().toString()
        var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
        var second =
            ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
                .round(2)
                .toString()

        // Add calculation to round seconds to minutes & minutes to hours if seconds & minutes == 60
        if (second.toDouble() == 60.0) {
            second = (second.toDouble() - 60).toString()
            minute = (minute.toInt() + 1).toString()
        }

        if (minute.toInt() == 60) {
            minute = (minute.toInt() - 60).toString()
            time = (time.toInt() + 1).toString()
        }

        // Add zero before numbers less than 10
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

        if (decimal < 0) {
            time = "-$time"
        }

        return "$time\u00B0 $minute\u2032 $second\u2033"
    }
    
    /**
    * convert number of day to day name
    * 1 = Monday, 2 = Tuesday etc.
    * 
    * @param value as a number of day
    * @return day name
    */
    fun toNameDay(value: Int): String {
        return when (value) {
            0 -> "Senin"
            1 -> "Selasa"
            2 -> "Rabu"
            3 -> "Kamis"
            4 -> "Jum`at"
            5 -> "Sabtu"
            6 -> "Ahad"
            7 -> "Senin"
            else -> "Senin"
        }
    }
    
    /**
    * convert number of pasaran to pasaran name
    * 1 = Legi, 2 = Pahing etc.
    * 
    * @param value as a number of pasaran
    * @return pasaran name
    */
    fun toNamePasaran(value: Int): String {
        return when (value) {
            0 -> "Legi"
            1 -> "Pahing"
            2 -> "Pon"
            3 -> "Wage"
            4 -> "Kliwon"
            5 -> "Legi"
            else -> "Legi"
        }
    }
    
    /**
    * convert number of month to month name
    * 1 = January, 2 = February etc.
    * 
    * @param number
    * @return name of month
    */
    fun toNameMonth(number: Int): String {
        
        return when (number) {
                1 -> "Januari"
                2 -> "Februari"
                3 -> "Maret"
                4 -> "April"
                5 -> "Mei"
                6 -> "Juni"
                7 -> "Juli"
                8 -> "Agustus"
                9 -> "September"
                10 -> "Oktober"
                11 -> "November"
                12 -> "Desember"
                else -> "Desember"
            }
    }
}
