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

package com.andihasan7.lib.ephemeris.jeanmeeus.util

import kotlin.math.abs
import kotlin.math.round

// fungsi custom round dibelakang koma
/**
 * extension function untuk membulatkan angka dibelakang koma yang bisa dicustom sendiri
 * input parameter bertipe Int
 */
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

/**
 * fungsi untuk merubah DMS/HMS ke Double
 * jika true maka positif, jika false maka negatif
 * @param[degree, minute, second]
 * @return decimal
 */
fun toDecimalCheck(degree: Int, minute: Int, second: Number, check: Boolean): Double {
    var decimal = degree + ((minute).toDouble() / 60) + ((second).toDouble() / 3600)

    if (check == false) {
        decimal = 0 - decimal
    }

    return decimal
}

// DD.d°
/**
 * fungsi merubah data Double ke bentuk format Derajat Desimal DD,d°
 * @param decimal
 * @return hasil
 */
fun toDoubleDegree(decimal: Double): String {
        val hasil = decimal.round(1)

    return "$hasil°"
}

// HH:MM:SS angka dibulatkan ke detik
/**
 * fungsi merubah data Double ke jam menit detik bertipe Int Array
 * index 0 : jam, index 1 : menit, index 2 : detik
 * @param decimal bertipe Double
 * @return intArrayOf(time, minute, second)
 */
fun toTimeFullRound(decimal: Double): IntArray {
    var time = abs(decimal).toInt()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt()
    var second = (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)).toInt()

    // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit ==
    // 60
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

// HH:MM:SS angka dibulatkan ke detik
/**
 * fungsi merubah data Doubel ke bentuk format HH:MM:SS detik dibulatkan ke menit, menit dibulatkan ke jam
 * @param decimal
 */
fun toTimeFullRoundSec(decimal: Double): String {
    var time = abs(decimal).toInt().toString()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
    var second =
        (round((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60))
            .toInt()
            .toString()

    // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit ==
    // 60
    if (second.toInt() == 60) {
        second = (second.toInt() - 60).toString()
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

// HH:MM:SS,ss dibulatkan ke 2 angka di belakang koma
/**
 * fungsi merubah data decimal ke bentuk format HH:MM:SS,ss pembulatan 2 angka dibelakang koma, detik dibulatkan ke menit, menit dibulatkan ke jam
 * @param decimal
 */
fun toTimeFullRound2(decimal: Double): String {
    var time = abs(decimal).toInt().toString()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
    var second =
        ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
            .round(2)
            .toString()

    // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit ==
    // 60
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

// DD° MM` SS,ss`` dibulatkan ke 2 angka di belakang koma
/**
 * fungsi merubah data desimal ke bentuk format DD° MM' SS,ss" detik dibulatkan ke 2 angka dibelakang koma, detik dibulatkan ke menit, menit dibulatkan ke jam
 * @param decimal
 */
fun toDegreeFullRound2(decimal: Double): String {
    var time = abs(decimal).toInt().toString()
    var minute = ((abs(decimal) - time.toDouble()) * 60).toInt().toString()
    var second =
        ((((abs(decimal) - time.toDouble()) * 60) - minute.toDouble()) * 60)
            .round(2)
            .toString()

    // Tambahkan perhitungan untuk membulatkan detik ke menit & menit ke jam jika detik & menit ==
    // 60
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

    return "$time\u00B0 $minute\u2032 $second\u2033"
}

/**
 * fungsi merubah data Int ke nama-nama hari yang dimulai dari Ahad
 * @param number
 * @return hari
 */
fun numberAhad(number: Int): String {

    val hari =
        when (number) {
            1 -> "Ahad"
            2 -> "Senin"
            3 -> "Selasa"
            4 -> "Rabu"
            5 -> "Kamis"
            6 -> "Jum`at"
            7 -> "Sabtu"
            else -> "Sabtu"
        }
    return hari
}
/**
 * fungsi merubah data Int ke nama-nama pasaran yang dimulai dari pahing
 * @param number
 * @return pasaran
 */
fun numberLegi(number: Int): String {
    val pasaran =
        when (number) {
            1 -> "Legi"
            2 -> "Pahing"
            3 -> "Pon"
            4 -> "Wage"
            5 -> "Kliwon"
            else -> "Kliwon"
        }
    return pasaran
}
/**
 * fungsi merubah data Int ke nama-nama bulan masehi
 * @param number
 * @return bulan
 */
fun numberJanuari(number: Int): String {
    val bulan =
        when (number) {
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
    return bulan
}