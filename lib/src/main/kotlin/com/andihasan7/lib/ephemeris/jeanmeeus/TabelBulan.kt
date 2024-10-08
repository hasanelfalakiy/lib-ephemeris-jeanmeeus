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

import kotlin.math.pow

/**
* TabelBulan
*/
object TabelBulan {
    
    /**
    * periodikBujur
    * t = nilaiT
    * l1 = bujur rata-rata bulan
    *
    * @param {nilaiT, l1}
    * @return doubleArrayOf(0.0, d, m, ma, f, e, bujur_bulan, lintang_bulan, jarakBulan)
    *
    * ```
    *     1 = d = elongasi rata2 bulan
    *     2 = m = anomali rata2 Matahari
    *     3 = ma = anomali rata2 bulan
    *     4 = f = argumen bujur bulan
    *     5 = e = eksentrisitas orbit
    *     6 = bujur_bulan = koreksi bujur bulan
    *     7 = lintang_bulan = true/apparent lintang bulan
    *     8 = jarakBulan = koreksi jarak bumi-bulan
    * ```
    */    
    fun periodikBujur(t: Double, l1: Double): DoubleArray {
        
        val l1_r = Math.toRadians(l1)
        // elongsi rata2 bulan
        val d =
            (297.8502042 + 445267.1115168 * t - 0.00163 * t.pow(2) + t.pow(3) / 545868 - t.pow(4) / 113065000).mod(360.0)
        val d_r = Math.toRadians(d)

        // anomali rata2 matahari
        val m = (357.5291092 + 35999.0502909 * t - 0.0001536 * t.pow(2) + t.pow(3) / 24490000).mod(360.0)
        val m_r = Math.toRadians(m)

        // anomali rata2 bulan
        val ma =
            (134.9634114 + 477198.8676313 * t + 0.008997 * t.pow(2) + t.pow(3) / 69699 - t.pow(4) / 14712000).mod(360.0)
        val ma_r = Math.toRadians(ma)

        // Argumen bujur bulan
        val f =
            (93.2720993 + 483202.0175273 * t - 0.0034029 * t.pow(2) - t.pow(3) / 3526000 + t.pow(4) / 863310000).mod(360.0)
        val f_r = Math.toRadians(f)

        // eksentrisitas orbit
        val e = 1 - 0.002516 * t - 0.0000074 * t.pow(2)

        // koreksi suku periodik bujur ekliptika bulan (sinus)
        var bujur_bulan = 0.0
        val D1 = 0.0
        val M1 = 0.0
        val MA1 = 1.0
        val F1 = 0.0
        val koefisien1 = 6288774.0
        val D2 = 2.0
        val M2 = 0.0
        val MA2 = -1.0
        val F2 = 0.0
        val koefisien2 = 1274027.0
        val D3 = 2.0
        val M3 = 0.0
        val MA3 = 0.0
        val F3 = 0.0
        val koefisien3 = 658314.0
        val D4 = 0.0
        val M4 = 0.0
        val MA4 = 2.0
        val F4 = 0.0
        val koefisien4 = 213618.0
        val D5 = 0.0
        val M5 = 1.0
        val MA5 = 0.0
        val F5 = 0.0
        val koefisien5 = -185116.0
        val D6 = 0.0
        val M6 = 0.0
        val MA6 = 0.0
        val F6 = 2.0
        val koefisien6 = -114332.0
        val D7 = 2.0
        val M7 = 0.0
        val MA7 = -2.0
        val F7 = 0.0
        val koefisien7 = 58793.0
        val D8 = 2.0
        val M8 = -1.0
        val MA8 = -1.0
        val F8 = 0.0
        val koefisien8 = 57066.0
        val D9 = 2.0
        val M9 = 0.0
        val MA9 = 1.0
        val F9 = 0.0
        val koefisien9 = 53322.0
        val D10 = 2.0
        val M10 = -1.0
        val MA10 = 0.0
        val F10 = 0.0
        val koefisien10 = 45758.0
        val D11 = 0.0
        val M11 = 1.0
        val MA11 = -1.0
        val F11 = 0.0
        val koefisien11 = -40923.0
        val D12 = 1.0
        val M12 = 0.0
        val MA12 = 0.0
        val F12 = 0.0
        val koefisien12 = -34720.0
        val D13 = 0.0
        val M13 = 1.0
        val MA13 = 1.0
        val F13 = 0.0
        val koefisien13 = -30383.0
        val D14 = 2.0
        val M14 = 0.0
        val MA14 = 0.0
        val F14 = -2.0
        val koefisien14 = 15327.0
        val D15 = 0.0
        val M15 = 0.0
        val MA15 = 1.0
        val F15 = 2.0
        val koefisien15 = -12528.0
        val D16 = 0.0
        val M16 = 0.0
        val MA16 = 1.0
        val F16 = -2.0
        val koefisien16 = 10980.0
        val D17 = 4.0
        val M17 = 0.0
        val MA17 = -1.0
        val F17 = 0.0
        val koefisien17 = 10675.0
        val D18 = 0.0
        val M18 = 0.0
        val MA18 = 3.0
        val F18 = 0.0
        val koefisien18 = 10034.0
        val D19 = 4.0
        val M19 = 0.0
        val MA19 = -2.0
        val F19 = 0.0
        val koefisien19 = 8548.0
        val D20 = 2.0
        val M20 = 1.0
        val MA20 = -1.0
        val F20 = 0.0
        val koefisien20 = -7888.0
        val D21 = 2.0
        val M21 = 1.0
        val MA21 = 0.0
        val F21 = 0.0
        val koefisien21 = -6766.0
        val D22 = 1.0
        val M22 = 0.0
        val MA22 = -1.0
        val F22 = 0.0
        val koefisien22 = -5163.0
        val D23 = 1.0
        val M23 = 1.0
        val MA23 = 0.0
        val F23 = 0.0
        val koefisien23 = 4987.0
        val D24 = 2.0
        val M24 = -1.0
        val MA24 = 1.0
        val F24 = 0.0
        val koefisien24 = 4036.0
        val D25 = 2.0
        val M25 = 0.0
        val MA25 = 2.0
        val F25 = 0.0
        val koefisien25 = 3994.0
        val D26 = 4.0
        val M26 = 0.0
        val MA26 = 0.0
        val F26 = 0.0
        val koefisien26 = 3861.0
        val D27 = 2.0
        val M27 = 0.0
        val MA27 = -3.0
        val F27 = 0.0
        val koefisien27 = 3665.0
        val D28 = 0.0
        val M28 = 1.0
        val MA28 = -2.0
        val F28 = 0.0
        val koefisien28 = -2689.0
        val D29 = 2.0
        val M29 = 0.0
        val MA29 = -1.0
        val F29 = 2.0
        val koefisien29 = -2602.0
        val D30 = 2.0
        val M30 = -1.0
        val MA30 = -2.0
        val F30 = 0.0
        val koefisien30 = 2390.0
        val D31 = 1.0
        val M31 = 0.0
        val MA31 = 1.0
        val F31 = 0.0
        val koefisien31 = -2348.0
        val D32 = 2.0
        val M32 = -2.0
        val MA32 = 0.0
        val F32 = 0.0
        val koefisien32 = 2236.0
        val D33 = 0.0
        val M33 = 1.0
        val MA33 = 2.0
        val F33 = 0.0
        val koefisien33 = -2120.0
        val D34 = 0.0
        val M34 = 2.0
        val MA34 = 0.0
        val F34 = 0.0
        val koefisien34 = -2069.0
        val D35 = 2.0
        val M35 = -2.0
        val MA35 = -1.0
        val F35 = 0.0
        val koefisien35 = 2048.0
        val D36 = 2.0
        val M36 = 0.0
        val MA36 = 1.0
        val F36 = -2.0
        val koefisien36 = -1773.0
        val D37 = 2.0
        val M37 = 0.0
        val MA37 = 0.0
        val F37 = 2.0
        val koefisien37 = -1595.0
        val D38 = 4.0
        val M38 = -1.0
        val MA38 = -1.0
        val F38 = 0.0
        val koefisien38 = 1215.0
        val D39 = 0.0
        val M39 = 0.0
        val MA39 = 2.0
        val F39 = 2.0
        val koefisien39 = -1110.0
        val D40 = 3.0
        val M40 = 0.0
        val MA40 = -1.0
        val F40 = 0.0
        val koefisien40 = -892.0
        val D41 = 2.0
        val M41 = 1.0
        val MA41 = 1.0
        val F41 = 0.0
        val koefisien41 = -810.0
        val D42 = 4.0
        val M42 = -1.0
        val MA42 = -2.0
        val F42 = 0.0
        val koefisien42 = 759.0
        val D43 = 0.0
        val M43 = 2.0
        val MA43 = -1.0
        val F43 = 0.0
        val koefisien43 = -713.0
        val D44 = 2.0
        val M44 = 2.0
        val MA44 = -1.0
        val F44 = 0.0
        val koefisien44 = -700.0
        val D45 = 2.0
        val M45 = 1.0
        val MA45 = -2.0
        val F45 = 0.0
        val koefisien45 = 691.0
        val D46 = 2.0
        val M46 = -1.0
        val MA46 = 0.0
        val F46 = -2.0
        val koefisien46 = 596.0
        val D47 = 4.0
        val M47 = 0.0
        val MA47 = 1.0
        val F47 = 0.0
        val koefisien47 = 549.0
        val D48 = 0.0
        val M48 = 0.0
        val MA48 = 4.0
        val F48 = 0.0
        val koefisien48 = 537.0
        val D49 = 4.0
        val M49 = -1.0
        val MA49 = 0.0
        val F49 = 0.0
        val koefisien49 = 520.0
        val D50 = 1.0
        val M50 = 0.0
        val MA50 = -2.0
        val F50 = 0.0
        val koefisien50 = -487.0
        val D51 = 2.0
        val M51 = 1.0
        val MA51 = 0.0
        val F51 = -2.0
        val koefisien51 = -399.0
        val D52 = 0.0
        val M52 = 0.0
        val MA52 = 2.0
        val F52 = -2.0
        val koefisien52 = -381.0
        val D53 = 1.0
        val M53 = 1.0
        val MA53 = 1.0
        val F53 = 0.0
        val koefisien53 = 351.0
        val D54 = 3.0
        val M54 = 0.0
        val MA54 = -2.0
        val F54 = 0.0
        val koefisien54 = -340.0
        val D55 = 4.0
        val M55 = 0.0
        val MA55 = -3.0
        val F55 = 0.0
        val koefisien55 = 330.0
        val D56 = 2.0
        val M56 = -1.0
        val MA56 = 2.0
        val F56 = 0.0
        val koefisien56 = 327.0
        val D57 = 0.0
        val M57 = 2.0
        val MA57 = 1.0
        val F57 = 0.0
        val koefisien57 = -323.0
        val D58 = 1.0
        val M58 = 1.0
        val MA58 = -1.0
        val F58 = 0.0
        val koefisien58 = 299.0
        val D59 = 2.0
        val M59 = 0.0
        val MA59 = 3.0
        val F59 = 0.0
        val koefisien59 = 294.0
        bujur_bulan += koefisien1 * Math.pow(e, Math.abs(M1)) * Math.sin(D1 * d_r + M1 * m_r + MA1 * ma_r + F1 * f_r)
        bujur_bulan += koefisien2 * Math.pow(e, Math.abs(M2)) * Math.sin(D2 * d_r + M2 * m_r + MA2 * ma_r + F2 * f_r)
        bujur_bulan += koefisien3 * Math.pow(e, Math.abs(M3)) * Math.sin(D3 * d_r + M3 * m_r + MA3 * ma_r + F3 * f_r)
        bujur_bulan += koefisien4 * Math.pow(e, Math.abs(M4)) * Math.sin(D4 * d_r + M4 * m_r + MA4 * ma_r + F4 * f_r)
        bujur_bulan += koefisien5 * Math.pow(e, Math.abs(M5)) * Math.sin(D5 * d_r + M5 * m_r + MA5 * ma_r + F5 * f_r)
        bujur_bulan += koefisien6 * Math.pow(e, Math.abs(M6)) * Math.sin(D6 * d_r + M6 * m_r + MA6 * ma_r + F6 * f_r)
        bujur_bulan += koefisien7 * Math.pow(e, Math.abs(M7)) * Math.sin(D7 * d_r + M7 * m_r + MA7 * ma_r + F7 * f_r)
        bujur_bulan += koefisien8 * Math.pow(e, Math.abs(M8)) * Math.sin(D8 * d_r + M8 * m_r + MA8 * ma_r + F8 * f_r)
        bujur_bulan += koefisien9 * Math.pow(e, Math.abs(M9)) * Math.sin(D9 * d_r + M9 * m_r + MA9 * ma_r + F9 * f_r)
        bujur_bulan += koefisien10 * Math.pow(e, Math.abs(M10)) * Math.sin(D10 * d_r + M10 * m_r + MA10 * ma_r + F10 * f_r)
        bujur_bulan += koefisien11 * Math.pow(e, Math.abs(M11)) * Math.sin(D11 * d_r + M11 * m_r + MA11 * ma_r + F11 * f_r)
        bujur_bulan += koefisien12 * Math.pow(e, Math.abs(M12)) * Math.sin(D12 * d_r + M12 * m_r + MA12 * ma_r + F12 * f_r)
        bujur_bulan += koefisien13 * Math.pow(e, Math.abs(M13)) * Math.sin(D13 * d_r + M13 * m_r + MA13 * ma_r + F13 * f_r)
        bujur_bulan += koefisien14 * Math.pow(e, Math.abs(M14)) * Math.sin(D14 * d_r + M14 * m_r + MA14 * ma_r + F14 * f_r)
        bujur_bulan += koefisien15 * Math.pow(e, Math.abs(M15)) * Math.sin(D15 * d_r + M15 * m_r + MA15 * ma_r + F15 * f_r)
        bujur_bulan += koefisien16 * Math.pow(e, Math.abs(M16)) * Math.sin(D16 * d_r + M16 * m_r + MA16 * ma_r + F16 * f_r)
        bujur_bulan += koefisien17 * Math.pow(e, Math.abs(M17)) * Math.sin(D17 * d_r + M17 * m_r + MA17 * ma_r + F17 * f_r)
        bujur_bulan += koefisien18 * Math.pow(e, Math.abs(M18)) * Math.sin(D18 * d_r + M18 * m_r + MA18 * ma_r + F18 * f_r)
        bujur_bulan += koefisien19 * Math.pow(e, Math.abs(M19)) * Math.sin(D19 * d_r + M19 * m_r + MA19 * ma_r + F19 * f_r)
        bujur_bulan += koefisien20 * Math.pow(e, Math.abs(M20)) * Math.sin(D20 * d_r + M20 * m_r + MA20 * ma_r + F20 * f_r)
        bujur_bulan += koefisien21 * Math.pow(e, Math.abs(M21)) * Math.sin(D21 * d_r + M21 * m_r + MA21 * ma_r + F21 * f_r)
        bujur_bulan += koefisien22 * Math.pow(e, Math.abs(M22)) * Math.sin(D22 * d_r + M22 * m_r + MA22 * ma_r + F22 * f_r)
        bujur_bulan += koefisien23 * Math.pow(e, Math.abs(M23)) * Math.sin(D23 * d_r + M23 * m_r + MA23 * ma_r + F23 * f_r)
        bujur_bulan += koefisien24 * Math.pow(e, Math.abs(M24)) * Math.sin(D24 * d_r + M24 * m_r + MA24 * ma_r + F24 * f_r)
        bujur_bulan += koefisien25 * Math.pow(e, Math.abs(M25)) * Math.sin(D25 * d_r + M25 * m_r + MA25 * ma_r + F25 * f_r)
        bujur_bulan += koefisien26 * Math.pow(e, Math.abs(M26)) * Math.sin(D26 * d_r + M26 * m_r + MA26 * ma_r + F26 * f_r)
        bujur_bulan += koefisien27 * Math.pow(e, Math.abs(M27)) * Math.sin(D27 * d_r + M27 * m_r + MA27 * ma_r + F27 * f_r)
        bujur_bulan += koefisien28 * Math.pow(e, Math.abs(M28)) * Math.sin(D28 * d_r + M28 * m_r + MA28 * ma_r + F28 * f_r)
        bujur_bulan += koefisien29 * Math.pow(e, Math.abs(M29)) * Math.sin(D29 * d_r + M29 * m_r + MA29 * ma_r + F29 * f_r)
        bujur_bulan += koefisien30 * Math.pow(e, Math.abs(M30)) * Math.sin(D30 * d_r + M30 * m_r + MA30 * ma_r + F30 * f_r)
        bujur_bulan += koefisien31 * Math.pow(e, Math.abs(M31)) * Math.sin(D31 * d_r + M31 * m_r + MA31 * ma_r + F31 * f_r)
        bujur_bulan += koefisien32 * Math.pow(e, Math.abs(M32)) * Math.sin(D32 * d_r + M32 * m_r + MA32 * ma_r + F32 * f_r)
        bujur_bulan += koefisien33 * Math.pow(e, Math.abs(M33)) * Math.sin(D33 * d_r + M33 * m_r + MA33 * ma_r + F33 * f_r)
        bujur_bulan += koefisien34 * Math.pow(e, Math.abs(M34)) * Math.sin(D34 * d_r + M34 * m_r + MA34 * ma_r + F34 * f_r)
        bujur_bulan += koefisien35 * Math.pow(e, Math.abs(M35)) * Math.sin(D35 * d_r + M35 * m_r + MA35 * ma_r + F35 * f_r)
        bujur_bulan += koefisien36 * Math.pow(e, Math.abs(M36)) * Math.sin(D36 * d_r + M36 * m_r + MA36 * ma_r + F36 * f_r)
        bujur_bulan += koefisien37 * Math.pow(e, Math.abs(M37)) * Math.sin(D37 * d_r + M37 * m_r + MA37 * ma_r + F37 * f_r)
        bujur_bulan += koefisien38 * Math.pow(e, Math.abs(M38)) * Math.sin(D38 * d_r + M38 * m_r + MA38 * ma_r + F38 * f_r)
        bujur_bulan += koefisien39 * Math.pow(e, Math.abs(M39)) * Math.sin(D39 * d_r + M39 * m_r + MA39 * ma_r + F39 * f_r)
        bujur_bulan += koefisien40 * Math.pow(e, Math.abs(M40)) * Math.sin(D40 * d_r + M40 * m_r + MA40 * ma_r + F40 * f_r)
        bujur_bulan += koefisien41 * Math.pow(e, Math.abs(M41)) * Math.sin(D41 * d_r + M41 * m_r + MA41 * ma_r + F41 * f_r)
        bujur_bulan += koefisien42 * Math.pow(e, Math.abs(M42)) * Math.sin(D42 * d_r + M42 * m_r + MA42 * ma_r + F42 * f_r)
        bujur_bulan += koefisien43 * Math.pow(e, Math.abs(M43)) * Math.sin(D43 * d_r + M43 * m_r + MA43 * ma_r + F43 * f_r)
        bujur_bulan += koefisien44 * Math.pow(e, Math.abs(M44)) * Math.sin(D44 * d_r + M44 * m_r + MA44 * ma_r + F44 * f_r)
        bujur_bulan += koefisien45 * Math.pow(e, Math.abs(M45)) * Math.sin(D45 * d_r + M45 * m_r + MA45 * ma_r + F45 * f_r)
        bujur_bulan += koefisien46 * Math.pow(e, Math.abs(M46)) * Math.sin(D46 * d_r + M46 * m_r + MA46 * ma_r + F46 * f_r)
        bujur_bulan += koefisien47 * Math.pow(e, Math.abs(M47)) * Math.sin(D47 * d_r + M47 * m_r + MA47 * ma_r + F47 * f_r)
        bujur_bulan += koefisien48 * Math.pow(e, Math.abs(M48)) * Math.sin(D48 * d_r + M48 * m_r + MA48 * ma_r + F48 * f_r)
        bujur_bulan += koefisien49 * Math.pow(e, Math.abs(M49)) * Math.sin(D49 * d_r + M49 * m_r + MA49 * ma_r + F49 * f_r)
        bujur_bulan += koefisien50 * Math.pow(e, Math.abs(M50)) * Math.sin(D50 * d_r + M50 * m_r + MA50 * ma_r + F50 * f_r)
        bujur_bulan += koefisien51 * Math.pow(e, Math.abs(M51)) * Math.sin(D51 * d_r + M51 * m_r + MA51 * ma_r + F51 * f_r)
        bujur_bulan += koefisien52 * Math.pow(e, Math.abs(M52)) * Math.sin(D52 * d_r + M52 * m_r + MA52 * ma_r + F52 * f_r)
        bujur_bulan += koefisien53 * Math.pow(e, Math.abs(M53)) * Math.sin(D53 * d_r + M53 * m_r + MA53 * ma_r + F53 * f_r)
        bujur_bulan += koefisien54 * Math.pow(e, Math.abs(M54)) * Math.sin(D54 * d_r + M54 * m_r + MA54 * ma_r + F54 * f_r)
        bujur_bulan += koefisien55 * Math.pow(e, Math.abs(M55)) * Math.sin(D55 * d_r + M55 * m_r + MA55 * ma_r + F55 * f_r)
        bujur_bulan += koefisien56 * Math.pow(e, Math.abs(M56)) * Math.sin(D56 * d_r + M56 * m_r + MA56 * ma_r + F56 * f_r)
        bujur_bulan += koefisien57 * Math.pow(e, Math.abs(M57)) * Math.sin(D57 * d_r + M57 * m_r + MA57 * ma_r + F57 * f_r)
        bujur_bulan += koefisien58 * Math.pow(e, Math.abs(M58)) * Math.sin(D58 * d_r + M58 * m_r + MA58 * ma_r + F58 * f_r)
        bujur_bulan += koefisien59 * Math.pow(e, Math.abs(M59)) * Math.sin(D59 * d_r + M59 * m_r + MA59 * ma_r + F59 * f_r)
        
        val argumenA1 = (119.75 + 131.849 * t).mod(360.0)
        val argumenA2 = (53.09 + 479264.29 * t).mod(360.0)
        val argumenA3 = (313.45 + 481266.484 * t).mod(360.0)
        val aA1_r = Math.toRadians(argumenA1)
        val aA2_r = Math.toRadians(argumenA2)
        val aA3_r = Math.toRadians(argumenA3)
        bujur_bulan =
            (bujur_bulan +
                3958 * Math.sin(aA1_r) +
                1962 * Math.sin(l1_r - f_r) +
                318 * Math.sin(aA2_r)) / 1000000

        // mengambil nilai lintang bulan yg ada pada fungsi periodikLintang
        val lintang_bulan = periodikLintang(e, d_r, m_r, ma_r, f_r, l1_r, aA1_r, aA3_r)[1]

        // mengambil nilai jarak bumi bulan
        val jarakBulan = jarakBumiBulan(e, d_r, m_r, ma_r, f_r)[1]

        // mengembalikan nilai dalam bentuk variable array
        /*
        1 = d = elongasi rata2 bulan
        2 = m = anomali rata2 Matahari
        3 = ma = anomali rata2 bulan
        4 = f = argumen bujur bulan
        5 = e = eksentrisitas orbit
        6 = bujur_bulan = koreksi bujur bulan
        7 = lintang_bulan = true/apparent lintang bulan
        8 = jarakBulan = koreksi jarak bumi-bulan
         */
        return doubleArrayOf(0.0, d, m, ma, f, e, bujur_bulan, lintang_bulan, jarakBulan)
    }
    
    /**
    * periodikLintang
    * agar lebih mudah, akses dari fungsi periodikBujur
    * 
    * ```
    *     e: Double,
    *     d_r: Double,
    *     m_r: Double,
    *     ma_r: Double,
    *     f_r: Double,
    *     l1_r: Double,
    *     aA1_r: Double,
    *     aA3_r: Double
    * ```
    *
    * @return doubleArrayOf(0.0, lintang_bulan)
    */
    fun periodikLintang(
        e: Double,
        d_r: Double,
        m_r: Double,
        ma_r: Double,
        f_r: Double,
        l1_r: Double,
        aA1_r: Double,
        aA3_r: Double
    ): DoubleArray {
        val D1 = 0.0
        val M1 = 0.0
        val MA1 = 0.0
        val F1 = 1.0
        val koefisien1 = 5128122.0
        val D2 = 0.0
        val M2 = 0.0
        val MA2 = 1.0
        val F2 = 1.0
        val koefisien2 = 280602.0
        val D3 = 0.0
        val M3 = 0.0
        val MA3 = 1.0
        val F3 = -1.0
        val koefisien3 = 277693.0
        val D4 = 2.0
        val M4 = 0.0
        val MA4 = 0.0
        val F4 = -1.0
        val koefisien4 = 173237.0
        val D5 = 2.0
        val M5 = 0.0
        val MA5 = -1.0
        val F5 = 1.0
        val koefisien5 = 55413.0
        val D6 = 2.0
        val M6 = 0.0
        val MA6 = -1.0
        val F6 = -1.0
        val koefisien6 = 46271.0
        val D7 = 2.0
        val M7 = 0.0
        val MA7 = 0.0
        val F7 = 1.0
        val koefisien7 = 32573.0
        val D8 = 0.0
        val M8 = 0.0
        val MA8 = 2.0
        val F8 = 1.0
        val koefisien8 = 17198.0
        val D9 = 2.0
        val M9 = 0.0
        val MA9 = 1.0
        val F9 = -1.0
        val koefisien9 = 9266.0
        val D10 = 0.0
        val M10 = 0.0
        val MA10 = 2.0
        val F10 = -1.0
        val koefisien10 = 8822.0
        val D11 = 2.0
        val M11 = -1.0
        val MA11 = 0.0
        val F11 = -1.0
        val koefisien11 = 8216.0
        val D12 = 2.0
        val M12 = 0.0
        val MA12 = -2.0
        val F12 = -1.0
        val koefisien12 = 4324.0
        val D13 = 2.0
        val M13 = 0.0
        val MA13 = 1.0
        val F13 = 1.0
        val koefisien13 = 4200.0
        val D14 = 2.0
        val M14 = 1.0
        val MA14 = 0.0
        val F14 = -1.0
        val koefisien14 = -3359.0
        val D15 = 2.0
        val M15 = -1.0
        val MA15 = -1.0
        val F15 = 1.0
        val koefisien15 = 2463.0
        val D16 = 2.0
        val M16 = -1.0
        val MA16 = 0.0
        val F16 = 1.0
        val koefisien16 = 2211.0
        val D17 = 2.0
        val M17 = -1.0
        val MA17 = -1.0
        val F17 = -1.0
        val koefisien17 = 2065.0
        val D18 = 0.0
        val M18 = 1.0
        val MA18 = -1.0
        val F18 = -1.0
        val koefisien18 = -1870.0
        val D19 = 4.0
        val M19 = 0.0
        val MA19 = -1.0
        val F19 = -1.0
        val koefisien19 = 1828.0
        val D20 = 0.0
        val M20 = 1.0
        val MA20 = 0.0
        val F20 = 1.0
        val koefisien20 = -1794.0
        val D21 = 0.0
        val M21 = 0.0
        val MA21 = 0.0
        val F21 = 3.0
        val koefisien21 = -1749.0
        val D22 = 0.0
        val M22 = 1.0
        val MA22 = -1.0
        val F22 = 1.0
        val koefisien22 = -1565.0
        val D23 = 1.0
        val M23 = 0.0
        val MA23 = 0.0
        val F23 = 1.0
        val koefisien23 = -1491.0
        val D24 = 0.0
        val M24 = 1.0
        val MA24 = 1.0
        val F24 = 1.0
        val koefisien24 = -1475.0
        val D25 = 0.0
        val M25 = 1.0
        val MA25 = 1.0
        val F25 = -1.0
        val koefisien25 = -1410.0
        val D26 = 0.0
        val M26 = 1.0
        val MA26 = 0.0
        val F26 = -1.0
        val koefisien26 = -1344.0
        val D27 = 1.0
        val M27 = 0.0
        val MA27 = 0.0
        val F27 = -1.0
        val koefisien27 = -1335.0
        val D28 = 0.0
        val M28 = 0.0
        val MA28 = 3.0
        val F28 = 1.0
        val koefisien28 = 1107.0
        val D29 = 4.0
        val M29 = 0.0
        val MA29 = 0.0
        val F29 = -1.0
        val koefisien29 = 1021.0
        val D30 = 4.0
        val M30 = 0.0
        val MA30 = -1.0
        val F30 = 1.0
        val koefisien30 = 833.0
        val D31 = 0.0
        val M31 = 0.0
        val MA31 = 1.0
        val F31 = -3.0
        val koefisien31 = 777.0
        val D32 = 4.0
        val M32 = 0.0
        val MA32 = -2.0
        val F32 = 1.0
        val koefisien32 = 671.0
        val D33 = 2.0
        val M33 = 0.0
        val MA33 = 0.0
        val F33 = -3.0
        val koefisien33 = 607.0
        val D34 = 2.0
        val M34 = 0.0
        val MA34 = 2.0
        val F34 = -1.0
        val koefisien34 = 596.0
        val D35 = 2.0
        val M35 = -1.0
        val MA35 = 1.0
        val F35 = -1.0
        val koefisien35 = 491.0
        val D36 = 2.0
        val M36 = 0.0
        val MA36 = -2.0
        val F36 = 1.0
        val koefisien36 = -451.0
        val D37 = 0.0
        val M37 = 0.0
        val MA37 = 3.0
        val F37 = -1.0
        val koefisien37 = 439.0
        val D38 = 2.0
        val M38 = 0.0
        val MA38 = 2.0
        val F38 = 1.0
        val koefisien38 = 422.0
        val D39 = 2.0
        val M39 = 0.0
        val MA39 = -3.0
        val F39 = -1.0
        val koefisien39 = 421.0
        val D40 = 2.0
        val M40 = 1.0
        val MA40 = -1.0
        val F40 = 1.0
        val koefisien40 = -366.0
        val D41 = 2.0
        val M41 = 1.0
        val MA41 = 0.0
        val F41 = 1.0
        val koefisien41 = -351.0
        val D42 = 4.0
        val M42 = 0.0
        val MA42 = 0.0
        val F42 = 1.0
        val koefisien42 = 331.0
        val D43 = 2.0
        val M43 = -1.0
        val MA43 = 1.0
        val F43 = 1.0
        val koefisien43 = 315.0
        val D44 = 2.0
        val M44 = -2.0
        val MA44 = 0.0
        val F44 = -1.0
        val koefisien44 = 302.0
        val D45 = 0.0
        val M45 = 0.0
        val MA45 = 1.0
        val F45 = 3.0
        val koefisien45 = -283.0
        val D46 = 2.0
        val M46 = 1.0
        val MA46 = 1.0
        val F46 = -1.0
        val koefisien46 = -229.0
        val D47 = 1.0
        val M47 = 1.0
        val MA47 = 0.0
        val F47 = -1.0
        val koefisien47 = 223.0
        val D48 = 1.0
        val M48 = 1.0
        val MA48 = 0.0
        val F48 = 1.0
        val koefisien48 = 223.0
        val D49 = 0.0
        val M49 = 1.0
        val MA49 = -2.0
        val F49 = -1.0
        val koefisien49 = -220.0
        val D50 = 2.0
        val M50 = 1.0
        val MA50 = -1.0
        val F50 = -1.0
        val koefisien50 = -220.0
        val D51 = 1.0
        val M51 = 0.0
        val MA51 = 1.0
        val F51 = 1.0
        val koefisien51 = -185.0
        val D52 = 2.0
        val M52 = -1.0
        val MA52 = -2.0
        val F52 = -1.0
        val koefisien52 = 181.0
        val D53 = 0.0
        val M53 = 1.0
        val MA53 = 2.0
        val F53 = 1.0
        val koefisien53 = -177.0
        val D54 = 4.0
        val M54 = 0.0
        val MA54 = -2.0
        val F54 = -1.0
        val koefisien54 = 176.0
        val D55 = 4.0
        val M55 = -1.0
        val MA55 = -1.0
        val F55 = -1.0
        val koefisien55 = 166.0
        val D56 = 1.0
        val M56 = 0.0
        val MA56 = 1.0
        val F56 = -1.0
        val koefisien56 = -164.0
        val D57 = 4.0
        val M57 = 0.0
        val MA57 = 1.0
        val F57 = -1.0
        val koefisien57 = 132.0
        val D58 = 1.0
        val M58 = 0.0
        val MA58 = -1.0
        val F58 = -1.0
        val koefisien58 = -119.0
        val D59 = 4.0
        val M59 = -1.0
        val MA59 = 0.0
        val F59 = -1.0
        val koefisien59 = 115.0
        val D60 = 2.0
        val M60 = -2.0
        val MA60 = 0.0
        val F60 = 1.0
        val koefisien60 = 107.0
        
        var lintang_bulan = 0.0
        lintang_bulan += koefisien1 * Math.pow(e, Math.abs(M1)) * Math.sin(D1 * d_r + M1 * m_r + MA1 * ma_r + F1 * f_r)
        lintang_bulan += koefisien2 * Math.pow(e, Math.abs(M2)) * Math.sin(D2 * d_r + M2 * m_r + MA2 * ma_r + F2 * f_r)
        lintang_bulan += koefisien3 * Math.pow(e, Math.abs(M3)) * Math.sin(D3 * d_r + M3 * m_r + MA3 * ma_r + F3 * f_r)
        lintang_bulan += koefisien4 * Math.pow(e, Math.abs(M4)) * Math.sin(D4 * d_r + M4 * m_r + MA4 * ma_r + F4 * f_r)
        lintang_bulan += koefisien5 * Math.pow(e, Math.abs(M5)) * Math.sin(D5 * d_r + M5 * m_r + MA5 * ma_r + F5 * f_r)
        lintang_bulan += koefisien6 * Math.pow(e, Math.abs(M6)) * Math.sin(D6 * d_r + M6 * m_r + MA6 * ma_r + F6 * f_r)
        lintang_bulan += koefisien7 * Math.pow(e, Math.abs(M7)) * Math.sin(D7 * d_r + M7 * m_r + MA7 * ma_r + F7 * f_r)
        lintang_bulan += koefisien8 * Math.pow(e, Math.abs(M8)) * Math.sin(D8 * d_r + M8 * m_r + MA8 * ma_r + F8 * f_r)
        lintang_bulan += koefisien9 * Math.pow(e, Math.abs(M9)) * Math.sin(D9 * d_r + M9 * m_r + MA9 * ma_r + F9 * f_r)
        lintang_bulan += koefisien10 * Math.pow(e, Math.abs(M10)) * Math.sin(D10 * d_r + M10 * m_r + MA10 * ma_r + F10 * f_r)
        lintang_bulan += koefisien11 * Math.pow(e, Math.abs(M11)) * Math.sin(D11 * d_r + M11 * m_r + MA11 * ma_r + F11 * f_r)
        lintang_bulan += koefisien12 * Math.pow(e, Math.abs(M12)) * Math.sin(D12 * d_r + M12 * m_r + MA12 * ma_r + F12 * f_r)
        lintang_bulan += koefisien13 * Math.pow(e, Math.abs(M13)) * Math.sin(D13 * d_r + M13 * m_r + MA13 * ma_r + F13 * f_r)
        lintang_bulan += koefisien14 * Math.pow(e, Math.abs(M14)) * Math.sin(D14 * d_r + M14 * m_r + MA14 * ma_r + F14 * f_r)
        lintang_bulan += koefisien15 * Math.pow(e, Math.abs(M15)) * Math.sin(D15 * d_r + M15 * m_r + MA15 * ma_r + F15 * f_r)
        lintang_bulan += koefisien16 * Math.pow(e, Math.abs(M16)) * Math.sin(D16 * d_r + M16 * m_r + MA16 * ma_r + F16 * f_r)
        lintang_bulan += koefisien17 * Math.pow(e, Math.abs(M17)) * Math.sin(D17 * d_r + M17 * m_r + MA17 * ma_r + F17 * f_r)
        lintang_bulan += koefisien18 * Math.pow(e, Math.abs(M18)) * Math.sin(D18 * d_r + M18 * m_r + MA18 * ma_r + F18 * f_r)
        lintang_bulan += koefisien19 * Math.pow(e, Math.abs(M19)) * Math.sin(D19 * d_r + M19 * m_r + MA19 * ma_r + F19 * f_r)
        lintang_bulan += koefisien20 * Math.pow(e, Math.abs(M20)) * Math.sin(D20 * d_r + M20 * m_r + MA20 * ma_r + F20 * f_r)
        lintang_bulan += koefisien21 * Math.pow(e, Math.abs(M21)) * Math.sin(D21 * d_r + M21 * m_r + MA21 * ma_r + F21 * f_r)
        lintang_bulan += koefisien22 * Math.pow(e, Math.abs(M22)) * Math.sin(D22 * d_r + M22 * m_r + MA22 * ma_r + F22 * f_r)
        lintang_bulan += koefisien23 * Math.pow(e, Math.abs(M23)) * Math.sin(D23 * d_r + M23 * m_r + MA23 * ma_r + F23 * f_r)
        lintang_bulan += koefisien24 * Math.pow(e, Math.abs(M24)) * Math.sin(D24 * d_r + M24 * m_r + MA24 * ma_r + F24 * f_r)
        lintang_bulan += koefisien25 * Math.pow(e, Math.abs(M25)) * Math.sin(D25 * d_r + M25 * m_r + MA25 * ma_r + F25 * f_r)
        lintang_bulan += koefisien26 * Math.pow(e, Math.abs(M26)) * Math.sin(D26 * d_r + M26 * m_r + MA26 * ma_r + F26 * f_r)
        lintang_bulan += koefisien27 * Math.pow(e, Math.abs(M27)) * Math.sin(D27 * d_r + M27 * m_r + MA27 * ma_r + F27 * f_r)
        lintang_bulan += koefisien28 * Math.pow(e, Math.abs(M28)) * Math.sin(D28 * d_r + M28 * m_r + MA28 * ma_r + F28 * f_r)
        lintang_bulan += koefisien29 * Math.pow(e, Math.abs(M29)) * Math.sin(D29 * d_r + M29 * m_r + MA29 * ma_r + F29 * f_r)
        lintang_bulan += koefisien30 * Math.pow(e, Math.abs(M30)) * Math.sin(D30 * d_r + M30 * m_r + MA30 * ma_r + F30 * f_r)
        lintang_bulan += koefisien31 * Math.pow(e, Math.abs(M31)) * Math.sin(D31 * d_r + M31 * m_r + MA31 * ma_r + F31 * f_r)
        lintang_bulan += koefisien32 * Math.pow(e, Math.abs(M32)) * Math.sin(D32 * d_r + M32 * m_r + MA32 * ma_r + F32 * f_r)
        lintang_bulan += koefisien33 * Math.pow(e, Math.abs(M33)) * Math.sin(D33 * d_r + M33 * m_r + MA33 * ma_r + F33 * f_r)
        lintang_bulan += koefisien34 * Math.pow(e, Math.abs(M34)) * Math.sin(D34 * d_r + M34 * m_r + MA34 * ma_r + F34 * f_r)
        lintang_bulan += koefisien35 * Math.pow(e, Math.abs(M35)) * Math.sin(D35 * d_r + M35 * m_r + MA35 * ma_r + F35 * f_r)
        lintang_bulan += koefisien36 * Math.pow(e, Math.abs(M36)) * Math.sin(D36 * d_r + M36 * m_r + MA36 * ma_r + F36 * f_r)
        lintang_bulan += koefisien37 * Math.pow(e, Math.abs(M37)) * Math.sin(D37 * d_r + M37 * m_r + MA37 * ma_r + F37 * f_r)
        lintang_bulan += koefisien38 * Math.pow(e, Math.abs(M38)) * Math.sin(D38 * d_r + M38 * m_r + MA38 * ma_r + F38 * f_r)
        lintang_bulan += koefisien39 * Math.pow(e, Math.abs(M39)) * Math.sin(D39 * d_r + M39 * m_r + MA39 * ma_r + F39 * f_r)
        lintang_bulan += koefisien40 * Math.pow(e, Math.abs(M40)) * Math.sin(D40 * d_r + M40 * m_r + MA40 * ma_r + F40 * f_r)
        lintang_bulan += koefisien41 * Math.pow(e, Math.abs(M41)) * Math.sin(D41 * d_r + M41 * m_r + MA41 * ma_r + F41 * f_r)
        lintang_bulan += koefisien42 * Math.pow(e, Math.abs(M42)) * Math.sin(D42 * d_r + M42 * m_r + MA42 * ma_r + F42 * f_r)
        lintang_bulan += koefisien43 * Math.pow(e, Math.abs(M43)) * Math.sin(D43 * d_r + M43 * m_r + MA43 * ma_r + F43 * f_r)
        lintang_bulan += koefisien44 * Math.pow(e, Math.abs(M44)) * Math.sin(D44 * d_r + M44 * m_r + MA44 * ma_r + F44 * f_r)
        lintang_bulan += koefisien45 * Math.pow(e, Math.abs(M45)) * Math.sin(D45 * d_r + M45 * m_r + MA45 * ma_r + F45 * f_r)
        lintang_bulan += koefisien46 * Math.pow(e, Math.abs(M46)) * Math.sin(D46 * d_r + M46 * m_r + MA46 * ma_r + F46 * f_r)
        lintang_bulan += koefisien47 * Math.pow(e, Math.abs(M47)) * Math.sin(D47 * d_r + M47 * m_r + MA47 * ma_r + F47 * f_r)
        lintang_bulan += koefisien48 * Math.pow(e, Math.abs(M48)) * Math.sin(D48 * d_r + M48 * m_r + MA48 * ma_r + F48 * f_r)
        lintang_bulan += koefisien49 * Math.pow(e, Math.abs(M49)) * Math.sin(D49 * d_r + M49 * m_r + MA49 * ma_r + F49 * f_r)
        lintang_bulan += koefisien50 * Math.pow(e, Math.abs(M50)) * Math.sin(D50 * d_r + M50 * m_r + MA50 * ma_r + F50 * f_r)
        lintang_bulan += koefisien51 * Math.pow(e, Math.abs(M51)) * Math.sin(D51 * d_r + M51 * m_r + MA51 * ma_r + F51 * f_r)
        lintang_bulan += koefisien52 * Math.pow(e, Math.abs(M52)) * Math.sin(D52 * d_r + M52 * m_r + MA52 * ma_r + F52 * f_r)
        lintang_bulan += koefisien53 * Math.pow(e, Math.abs(M53)) * Math.sin(D53 * d_r + M53 * m_r + MA53 * ma_r + F53 * f_r)
        lintang_bulan += koefisien54 * Math.pow(e, Math.abs(M54)) * Math.sin(D54 * d_r + M54 * m_r + MA54 * ma_r + F54 * f_r)
        lintang_bulan += koefisien55 * Math.pow(e, Math.abs(M55)) * Math.sin(D55 * d_r + M55 * m_r + MA55 * ma_r + F55 * f_r)
        lintang_bulan += koefisien56 * Math.pow(e, Math.abs(M56)) * Math.sin(D56 * d_r + M56 * m_r + MA56 * ma_r + F56 * f_r)
        lintang_bulan += koefisien57 * Math.pow(e, Math.abs(M57)) * Math.sin(D57 * d_r + M57 * m_r + MA57 * ma_r + F57 * f_r)
        lintang_bulan += koefisien58 * Math.pow(e, Math.abs(M58)) * Math.sin(D58 * d_r + M58 * m_r + MA58 * ma_r + F58 * f_r)
        lintang_bulan += koefisien59 * Math.pow(e, Math.abs(M59)) * Math.sin(D59 * d_r + M59 * m_r + MA59 * ma_r + F59 * f_r)
        lintang_bulan += koefisien60 * Math.pow(e, Math.abs(M60)) * Math.sin(D60 * d_r + M60 * m_r + MA60 * ma_r + F60 * f_r)
        lintang_bulan =
            (lintang_bulan - 2235 * Math.sin(l1_r) +
                382 * Math.sin(aA3_r) +
                175 * Math.sin(aA1_r - f_r) +
                175 * Math.sin(aA1_r + f_r) +
                127 * Math.sin(l1_r - ma_r) - 115 * Math.sin(l1_r + ma_r)) / 1000000
        return doubleArrayOf(0.0, lintang_bulan)
    }
    
    /**
    * jarakBumiBulan
    * agar lebih mudah, akses dari fungsi periodikBujur
    * 
    * ```
    *     e: Double,
    *     d_r: Double,
    *     m_r: Double,
    *     ma_r: Double,
    *     f_r: Double
    * ```
    * 
    * @return doubleArrayOf(0.0, jarakBulan)
    */
    fun jarakBumiBulan(
        e: Double,
        d_r: Double,
        m_r: Double,
        ma_r: Double,
        f_r: Double
    ): DoubleArray {
        val D1 = 0.0
        val M1 = 0.0
        val MA1 = 1.0
        val F1 = 0.0
        val koefisien1 = -20905355.0
        val D2 = 2.0
        val M2 = 0.0
        val MA2 = -1.0
        val F2 = 0.0
        val koefisien2 = -3699111.0
        val D3 = 2.0
        val M3 = 0.0
        val MA3 = 0.0
        val F3 = 0.0
        val koefisien3 = -2955968.0
        val D4 = 0.0
        val M4 = 0.0
        val MA4 = 2.0
        val F4 = 0.0
        val koefisien4 = -569925.0
        val D5 = 2.0
        val M5 = 0.0
        val MA5 = -2.0
        val F5 = 0.0
        val koefisien5 = 246158.0
        val D6 = 2.0
        val M6 = -1.0
        val MA6 = 0.0
        val F6 = 0.0
        val koefisien6 = -204586.0
        val D7 = 2.0
        val M7 = 0.0
        val MA7 = 1.0
        val F7 = 0.0
        val koefisien7 = -170733.0
        val D8 = 2.0
        val M8 = -1.0
        val MA8 = -1.0
        val F8 = 0.0
        val koefisien8 = -152138.0
        val D9 = 0.0
        val M9 = 1.0
        val MA9 = -1.0
        val F9 = 0.0
        val koefisien9 = -129620.0
        val D10 = 1.0
        val M10 = 0.0
        val MA10 = 0.0
        val F10 = 0.0
        val koefisien10 = 108743.0
        val D11 = 0.0
        val M11 = 1.0
        val MA11 = 1.0
        val F11 = 0.0
        val koefisien11 = 104755.0
        val D12 = 0.0
        val M12 = 0.0
        val MA12 = 1.0
        val F12 = -2.0
        val koefisien12 = 79661.0
        val D13 = 0.0
        val M13 = 1.0
        val MA13 = 0.0
        val F13 = 0.0
        val koefisien13 = 48888.0
        val D14 = 4.0
        val M14 = 0.0
        val MA14 = -1.0
        val F14 = 0.0
        val koefisien14 = -34782.0
        val D15 = 2.0
        val M15 = 1.0
        val MA15 = 0.0
        val F15 = 0.0
        val koefisien15 = 30824.0
        val D16 = 2.0
        val M16 = 1.0
        val MA16 = -1.0
        val F16 = 0.0
        val koefisien16 = 24208.0
        val D17 = 0.0
        val M17 = 0.0
        val MA17 = 3.0
        val F17 = 0.0
        val koefisien17 = -23210.0
        val D18 = 4.0
        val M18 = 0.0
        val MA18 = -2.0
        val F18 = 0.0
        val koefisien18 = -21636.0
        val D19 = 1.0
        val M19 = 1.0
        val MA19 = 0.0
        val F19 = 0.0
        val koefisien19 = -16675.0
        val D20 = 2.0
        val M20 = 0.0
        val MA20 = -3.0
        val F20 = 0.0
        val koefisien20 = 14403.0
        val D21 = 2.0
        val M21 = -1.0
        val MA21 = 1.0
        val F21 = 0.0
        val koefisien21 = -12831.0
        val D22 = 4.0
        val M22 = 0.0
        val MA22 = 0.0
        val F22 = 0.0
        val koefisien22 = -11650.0
        val D23 = 2.0
        val M23 = 0.0
        val MA23 = 2.0
        val F23 = 0.0
        val koefisien23 = -10445.0
        val D24 = 2.0
        val M24 = 0.0
        val MA24 = 0.0
        val F24 = -2.0
        val koefisien24 = 10321.0
        val D25 = 2.0
        val M25 = -1.0
        val MA25 = -2.0
        val F25 = 0.0
        val koefisien25 = 10056.0
        val D26 = 2.0
        val M26 = -2.0
        val MA26 = 0.0
        val F26 = 0.0
        val koefisien26 = -9884.0
        val D27 = 2.0
        val M27 = 0.0
        val MA27 = -1.0
        val F27 = -2.0
        val koefisien27 = 8752.0
        val D28 = 1.0
        val M28 = 0.0
        val MA28 = -1.0
        val F28 = 0.0
        val koefisien28 = -8379.0
        val D29 = 0.0
        val M29 = 1.0
        val MA29 = -2.0
        val F29 = 0.0
        val koefisien29 = -7003.0
        val D30 = 1.0
        val M30 = 0.0
        val MA30 = 1.0
        val F30 = 0.0
        val koefisien30 = 6322.0
        val D31 = 0.0
        val M31 = 1.0
        val MA31 = 2.0
        val F31 = 0.0
        val koefisien31 = 5751.0
        val D32 = 2.0
        val M32 = -2.0
        val MA32 = -1.0
        val F32 = 0.0
        val koefisien32 = -4950.0
        val D33 = 0.0
        val M33 = 0.0
        val MA33 = 2.0
        val F33 = -2.0
        val koefisien33 = -4421.0
        val D34 = 2.0
        val M34 = 0.0
        val MA34 = 1.0
        val F34 = -2.0
        val koefisien34 = 4130.0
        val D35 = 4.0
        val M35 = -1.0
        val MA35 = -1.0
        val F35 = 0.0
        val koefisien35 = -3958.0
        val D36 = 3.0
        val M36 = 0.0
        val MA36 = -1.0
        val F36 = 0.0
        val koefisien36 = 3258.0
        val D37 = 0.0
        val M37 = 0.0
        val MA37 = 0.0
        val F37 = 2.0
        val koefisien37 = -3149.0
        val D38 = 2.0
        val M38 = 1.0
        val MA38 = 1.0
        val F38 = 0.0
        val koefisien38 = 2616.0
        val D39 = 2.0
        val M39 = 2.0
        val MA39 = -1.0
        val F39 = 0.0
        val koefisien39 = 2354.0
        val D40 = 0.0
        val M40 = 2.0
        val MA40 = -1.0
        val F40 = 0.0
        val koefisien40 = -2117.0
        val D41 = 4.0
        val M41 = -1.0
        val MA41 = -2.0
        val F41 = 0.0
        val koefisien41 = -1897.0
        val D42 = 1.0
        val M42 = 0.0
        val MA42 = -2.0
        val F42 = 0.0
        val koefisien42 = -1739.0
        val D43 = 4.0
        val M43 = -1.0
        val MA43 = 0.0
        val F43 = 0.0
        val koefisien43 = -1571.0
        val D44 = 4.0
        val M44 = 0.0
        val MA44 = 1.0
        val F44 = 0.0
        val koefisien44 = -1423.0
        val D45 = 0.0
        val M45 = 2.0
        val MA45 = 1.0
        val F45 = 0.0
        val koefisien45 = 1165.0
        val D46 = 0.0
        val M46 = 0.0
        val MA46 = 4.0
        val F46 = 0.0
        val koefisien46 = -1117.0
        
        var jarakBulan = 0.0
        jarakBulan += koefisien1 * Math.pow(e, Math.abs(M1)) * Math.cos(D1 * d_r + M1 * m_r + MA1 * ma_r + F1 * f_r)
        jarakBulan += koefisien2 * Math.pow(e, Math.abs(M2)) * Math.cos(D2 * d_r + M2 * m_r + MA2 * ma_r + F2 * f_r)
        jarakBulan += koefisien3 * Math.pow(e, Math.abs(M3)) * Math.cos(D3 * d_r + M3 * m_r + MA3 * ma_r + F3 * f_r)
        jarakBulan += koefisien4 * Math.pow(e, Math.abs(M4)) * Math.cos(D4 * d_r + M4 * m_r + MA4 * ma_r + F4 * f_r)
        jarakBulan += koefisien5 * Math.pow(e, Math.abs(M5)) * Math.cos(D5 * d_r + M5 * m_r + MA5 * ma_r + F5 * f_r)
        jarakBulan += koefisien6 * Math.pow(e, Math.abs(M6)) * Math.cos(D6 * d_r + M6 * m_r + MA6 * ma_r + F6 * f_r)
        jarakBulan += koefisien7 * Math.pow(e, Math.abs(M7)) * Math.cos(D7 * d_r + M7 * m_r + MA7 * ma_r + F7 * f_r)
        jarakBulan += koefisien8 * Math.pow(e, Math.abs(M8)) * Math.cos(D8 * d_r + M8 * m_r + MA8 * ma_r + F8 * f_r)
        jarakBulan += koefisien9 * Math.pow(e, Math.abs(M9)) * Math.cos(D9 * d_r + M9 * m_r + MA9 * ma_r + F9 * f_r)
        jarakBulan += koefisien10 * Math.pow(e, Math.abs(M10)) * Math.cos(D10 * d_r + M10 * m_r + MA10 * ma_r + F10 * f_r)
        jarakBulan += koefisien11 * Math.pow(e, Math.abs(M11)) * Math.cos(D11 * d_r + M11 * m_r + MA11 * ma_r + F11 * f_r)
        jarakBulan += koefisien12 * Math.pow(e, Math.abs(M12)) * Math.cos(D12 * d_r + M12 * m_r + MA12 * ma_r + F12 * f_r)
        jarakBulan += koefisien13 * Math.pow(e, Math.abs(M13)) * Math.cos(D13 * d_r + M13 * m_r + MA13 * ma_r + F13 * f_r)
        jarakBulan += koefisien14 * Math.pow(e, Math.abs(M14)) * Math.cos(D14 * d_r + M14 * m_r + MA14 * ma_r + F14 * f_r)
        jarakBulan += koefisien15 * Math.pow(e, Math.abs(M15)) * Math.cos(D15 * d_r + M15 * m_r + MA15 * ma_r + F15 * f_r)
        jarakBulan += koefisien16 * Math.pow(e, Math.abs(M16)) * Math.cos(D16 * d_r + M16 * m_r + MA16 * ma_r + F16 * f_r)
        jarakBulan += koefisien17 * Math.pow(e, Math.abs(M17)) * Math.cos(D17 * d_r + M17 * m_r + MA17 * ma_r + F17 * f_r)
        jarakBulan += koefisien18 * Math.pow(e, Math.abs(M18)) * Math.cos(D18 * d_r + M18 * m_r + MA18 * ma_r + F18 * f_r)
        jarakBulan += koefisien19 * Math.pow(e, Math.abs(M19)) * Math.cos(D19 * d_r + M19 * m_r + MA19 * ma_r + F19 * f_r)
        jarakBulan += koefisien20 * Math.pow(e, Math.abs(M20)) * Math.cos(D20 * d_r + M20 * m_r + MA20 * ma_r + F20 * f_r)
        jarakBulan += koefisien21 * Math.pow(e, Math.abs(M21)) * Math.cos(D21 * d_r + M21 * m_r + MA21 * ma_r + F21 * f_r)
        jarakBulan += koefisien22 * Math.pow(e, Math.abs(M22)) * Math.cos(D22 * d_r + M22 * m_r + MA22 * ma_r + F22 * f_r)
        jarakBulan += koefisien23 * Math.pow(e, Math.abs(M23)) * Math.cos(D23 * d_r + M23 * m_r + MA23 * ma_r + F23 * f_r)
        jarakBulan += koefisien24 * Math.pow(e, Math.abs(M24)) * Math.cos(D24 * d_r + M24 * m_r + MA24 * ma_r + F24 * f_r)
        jarakBulan += koefisien25 * Math.pow(e, Math.abs(M25)) * Math.cos(D25 * d_r + M25 * m_r + MA25 * ma_r + F25 * f_r)
        jarakBulan += koefisien26 * Math.pow(e, Math.abs(M26)) * Math.cos(D26 * d_r + M26 * m_r + MA26 * ma_r + F26 * f_r)
        jarakBulan += koefisien27 * Math.pow(e, Math.abs(M27)) * Math.cos(D27 * d_r + M27 * m_r + MA27 * ma_r + F27 * f_r)
        jarakBulan += koefisien28 * Math.pow(e, Math.abs(M28)) * Math.cos(D28 * d_r + M28 * m_r + MA28 * ma_r + F28 * f_r)
        jarakBulan += koefisien29 * Math.pow(e, Math.abs(M29)) * Math.cos(D29 * d_r + M29 * m_r + MA29 * ma_r + F29 * f_r)
        jarakBulan += koefisien30 * Math.pow(e, Math.abs(M30)) * Math.cos(D30 * d_r + M30 * m_r + MA30 * ma_r + F30 * f_r)
        jarakBulan += koefisien31 * Math.pow(e, Math.abs(M31)) * Math.cos(D31 * d_r + M31 * m_r + MA31 * ma_r + F31 * f_r)
        jarakBulan += koefisien32 * Math.pow(e, Math.abs(M32)) * Math.cos(D32 * d_r + M32 * m_r + MA32 * ma_r + F32 * f_r)
        jarakBulan += koefisien33 * Math.pow(e, Math.abs(M33)) * Math.cos(D33 * d_r + M33 * m_r + MA33 * ma_r + F33 * f_r)
        jarakBulan += koefisien34 * Math.pow(e, Math.abs(M34)) * Math.cos(D34 * d_r + M34 * m_r + MA34 * ma_r + F34 * f_r)
        jarakBulan += koefisien35 * Math.pow(e, Math.abs(M35)) * Math.cos(D35 * d_r + M35 * m_r + MA35 * ma_r + F35 * f_r)
        jarakBulan += koefisien36 * Math.pow(e, Math.abs(M36)) * Math.cos(D36 * d_r + M36 * m_r + MA36 * ma_r + F36 * f_r)
        jarakBulan += koefisien37 * Math.pow(e, Math.abs(M37)) * Math.cos(D37 * d_r + M37 * m_r + MA37 * ma_r + F37 * f_r)
        jarakBulan += koefisien38 * Math.pow(e, Math.abs(M38)) * Math.cos(D38 * d_r + M38 * m_r + MA38 * ma_r + F38 * f_r)
        jarakBulan += koefisien39 * Math.pow(e, Math.abs(M39)) * Math.cos(D39 * d_r + M39 * m_r + MA39 * ma_r + F39 * f_r)
        jarakBulan += koefisien40 * Math.pow(e, Math.abs(M40)) * Math.cos(D40 * d_r + M40 * m_r + MA40 * ma_r + F40 * f_r)
        jarakBulan += koefisien41 * Math.pow(e, Math.abs(M41)) * Math.cos(D41 * d_r + M41 * m_r + MA41 * ma_r + F41 * f_r)
        jarakBulan += koefisien42 * Math.pow(e, Math.abs(M42)) * Math.cos(D42 * d_r + M42 * m_r + MA42 * ma_r + F42 * f_r)
        jarakBulan += koefisien43 * Math.pow(e, Math.abs(M43)) * Math.cos(D43 * d_r + M43 * m_r + MA43 * ma_r + F43 * f_r)
        jarakBulan += koefisien44 * Math.pow(e, Math.abs(M44)) * Math.cos(D44 * d_r + M44 * m_r + MA44 * ma_r + F44 * f_r)
        jarakBulan += koefisien45 * Math.pow(e, Math.abs(M45)) * Math.cos(D45 * d_r + M45 * m_r + MA45 * ma_r + F45 * f_r)
        jarakBulan += koefisien46 * Math.pow(e, Math.abs(M46)) * Math.cos(D46 * d_r + M46 * m_r + MA46 * ma_r + F46 * f_r)
        jarakBulan = jarakBulan / 1000
        
        return doubleArrayOf(0.0, jarakBulan)
    }
}
