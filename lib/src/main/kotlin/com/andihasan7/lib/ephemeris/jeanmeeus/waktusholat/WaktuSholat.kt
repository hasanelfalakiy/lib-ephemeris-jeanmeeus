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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.waktusholat

import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.tan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.mod
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus
import com.andihasan7.lib.ephemeris.jeanmeeus.arahqiblat.ArahQiblat
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toCounterHHMMSS2
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toTimeFullRound2

/**
*
* WaktuSholat
*
* ```
*    date: Int = 1, // tanggal masehi
*    month: Int = 1, // bulan masehi
*    year: Int = 2000, // tahun masehi
*    latitude: Double = 0.0, // lintang tempat
*    longitude: Double = 0.0, // bujur tempat
*    elevation: Double = 0.0, // tinggi tempat
*    timeZone: Double = 0.0, // zona waktu
*    ihtiyatDzuhur: Int = 0, // ihtiyat dzuhur
*    ihtiyatLain: Int = 0 // ihtiyat selain dzuhur ditambah kecuali terbit dikurangi
* ```
*/
class WaktuSholat(
    date: Int,
    month: Int,
    year: Int,
    latitude: Double,
    longitude: Double,
    elevation: Double,
    timeZone: Double,
    ihtiyatDzuhur: Int = 0,
    ihtiyatLain: Int = 0
) {
    
    
    // untuk dzuhur
    private val jm = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 12.0
        )
            
    private val kwd = (longitude - (timeZone * 15)) / 15
    
    /**
    * deklinasi 12 siang
    */
    val dek = jm.sunApparentGeoDeclination
    
    /**
    * equation of time 12 siang
    */
    val eq = jm.equationOfTimeHour
    
    /**
    * semidiameter 12 siang
    */
    val semidiameter = jm.sunApparentGeocentricSemidiameter
    
    // private val transit = 12 + timeZone - longitude / 15 - eq
    private val meridianPass = 12 - eq
    
    /**
    * waktu transit / dzuhur waktu daerah/lokal
    */
    val dzuhurWD = meridianPass - kwd + (ihtiyatDzuhur.toDouble() / 60)
    
    /**
    * dzuhur lokal HMS
    */
    val dzuhurWD_HMS = toTimeFullRound2(dzuhurWD)
    
    /**
    * dzuhur istiwa`
    */
    val dzuhurWIS = dzuhurWD + eq + kwd
    
    /**
    * dzuhur istiwa` HMS
    */
    val dzuhurWIS_HMS = toTimeFullRound2(dzuhurWIS)
    
    // untuk ashar
    private val jma = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 15.0
        )
     
    private val dek_ash = jma.sunApparentGeoDeclination
    private val eq_ash = jma.equationOfTimeHour
    private val meridianPassAshar = 12 - eq_ash
    private val zm = abs(latitude - dek_ash)
    private val alt_ashar = Math.toDegrees(atan(1.0 / (tan(Math.toRadians(zm)) + 1)))
    private val t_ashar = Math.toDegrees(acos((sin(Math.toRadians(alt_ashar)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_ash))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_ash)))))
    
    /**
    * ashar lokal
    */
    val asharWD = meridianPassAshar + (t_ashar / 15) - kwd + (ihtiyatLain.toDouble() / 60)
    
    /**
    * ashar lokal HMS
    */
    val asharWD_HMS = toTimeFullRound2(asharWD)
    
    /**
    * ashar istiwa`
    */
    val asharWIS = asharWD + eq_ash + kwd
    
    /**
    * ashar istiwa` HMS
    */
    val asharWIS_HMS = toTimeFullRound2(asharWIS)
    
    // untuk maghrib
    private val jmm = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 18.0
        )
    
    private val dip =
        if ((elevation).toDouble() >= 0.0) {
                (1.76 / 60) *
            sqrt(
                (elevation).toDouble()
            ) // konversi ke Double disini untuk memudahkan suatu saat custom tinggi tempat
        } else { // tinggitempat diset ke 0 ketika tinggitempat terdeteksi di bawah 0/minus
                (1.76 / 60) *
            sqrt(
                (0.0)
            )
        }
    private val dek_maghr = jmm.sunApparentGeoDeclination
    private val eq_maghr = jmm.equationOfTimeHour
    private val semid_maghr = jmm.sunApparentGeocentricSemidiameter
    private val meridianPassMaghr = 12 - eq_maghr
    
    private val hMaghrib = -((34.0 / 60) + semid_maghr + dip)
    private val t_maghr = Math.toDegrees(acos((sin(Math.toRadians(hMaghrib)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_maghr))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_maghr)))))
    
    private val _maghribWD = meridianPassMaghr + (t_maghr / 15) - kwd
    /**
    * maghrib lokal
    */
    val maghribWD = _maghribWD + (ihtiyatLain.toDouble() / 60)
    
    /**
    * maghrib lokal HMS
    */
    val maghribWD_HMS = toTimeFullRound2(maghribWD)
    
    /**
    * maghrib istiwa`
    */
    val maghribWIS = maghribWD + eq_maghr + kwd
    
    /**
    * maghrib istiwa` HMS
    */
    val maghribWIS_HMS = toTimeFullRound2(maghribWIS)
    
    // untuk isya`
    private val jmi = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 19.0
        )
    
    private val dek_isya = jmi.sunApparentGeoDeclination
    private val eq_isya = jmi.equationOfTimeHour
    private val meridianPassIsya = 12 - eq_isya
    private val hIsya = -18.0 // + hMaghrib
    private val t_isya = Math.toDegrees(acos((sin(Math.toRadians(hIsya)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_isya))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_isya)))))
    
    /**
    * isya lokal
    */
    val isyaWD = meridianPassIsya + (t_isya / 15) - kwd + (ihtiyatLain.toDouble() / 60)
    
    /**
    * isya lokal HMS
    */
    val isyaWD_HMS = toTimeFullRound2(isyaWD)
    
    /**
    * isya istiwa`
    */
    val isyaWIS = isyaWD + eq_isya + kwd
    
    /**
    * isya istiwa` HMS
    */
    val isyaWIS_HMS = toTimeFullRound2(isyaWIS)
    
    
    // untuk shubuh
    private val jms = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 4.0
        )
    
    private val dek_shubuh = jms.sunApparentGeoDeclination
    private val eq_shubuh = jms.equationOfTimeHour
    private val meridianPassShubuh = 12 - eq_shubuh
    private val hShubuh = -20.0 // + hMaghrib
    private val t_shubuh = Math.toDegrees(acos((sin(Math.toRadians(hShubuh)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_shubuh))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_shubuh)))))
    
    private val _shubuhWD = meridianPassShubuh - (t_shubuh / 15) - kwd
    /**
    * shubuh lokal
    */
    val shubuhWD = _shubuhWD + (ihtiyatLain.toDouble() / 60)
    
    /**
    * shubuh lokal HMS
    */
    val shubuhWD_HMS = toTimeFullRound2(shubuhWD)
    
    /**
    * shubuh istiwa`
    */
    val shubuhWIS = shubuhWD + eq_shubuh + kwd
    
    /**
    * shubuh istiwa` HMS
    */
    val shubuhWIS_HMS = toTimeFullRound2(shubuhWIS)
    
    // untuk imsak
    
    /**
    * imsak lokal
    */
    val imsakWD = shubuhWD - (10.0 / 60)
    
    /**
    * imsak lokal HMS
    */
    val imsakWD_HMS = toTimeFullRound2(imsakWD)
    
    /**
    * imsak istiwa`
    */
    val imsakWIS = imsakWD + eq_shubuh + kwd
    
    /**
    * imsak istiwa` HMS
    */
    val imsakWIS_HMS = toTimeFullRound2(imsakWIS)
    
    // untuk terbit
    private val jmt = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 6.0
        )
    
    private val dek_terbit = jmt.sunApparentGeoDeclination
    private val eq_terbit = jmt.equationOfTimeHour
    private val meridianPassTerbit = 12 - eq_terbit
    
    /**
    * terbit lokal
    */
    val terbitWD = meridianPassTerbit - (t_maghr / 15) - kwd - (ihtiyatLain.toDouble() / 60)
    
    /**
    * terbit lokal HMS
    */
    val terbitWD_HMS = toTimeFullRound2(terbitWD)
    
    /**
    * terbit istiwa`
    */
    val terbitWIS = terbitWD + eq_terbit + kwd
    
    /**
    * terbit istiwa` HMS
    */
    val terbitWIS_HMS = toTimeFullRound2(terbitWIS)
    
    // untuk dluha
    private val jmd = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 6.25
        )
    
    private val dek_dluha = jmd.sunApparentGeoDeclination
    private val eq_dluha = jmd.equationOfTimeHour
    private val meridianPassDluha = 12 - eq_dluha
    private val hDluha = 4.5
    private val t_dluha = Math.toDegrees(acos((sin(Math.toRadians(hDluha)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_dluha))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_dluha)))))
    
    /**
    * dluha lokal
    */
    val dluhaWD = meridianPassDluha - (t_dluha / 15) - kwd + (ihtiyatLain.toDouble() / 60)
    
    /**
    * dluha lokal HMS
    */
    val dluhaWD_HMS = toTimeFullRound2(dluhaWD)
    
    /**
    * dluha istiwa`
    */
    val dluhaWIS = dluhaWD + eq_dluha + kwd
    
    /**
    * dluha istiwa` HMS
    */
    val dluhaWIS_HMS = toTimeFullRound2(dluhaWIS)
    
    // untuk tengah malam
    private val jmTM = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 24.0
        )
    
    private val dek_TM = jmTM.sunApparentGeoDeclination
    private val eq_TM = jmTM.equationOfTimeHour
    
    /**
    * tengah malam lokal
    */
    val tengahMalamWD = (((_shubuhWD + 24) - _maghribWD) / 2 + _maghribWD).mod(24.0)
    
    /**
    * tengah malam lokal HMS
    */
    val tengahMalamWD_HMS = toTimeFullRound2(tengahMalamWD)
    
    /**
    * tengah malam istiwa`
    */
    val tengahMalamWIS = tengahMalamWD + eq_TM + kwd
    
    /**
    * tengah malam istiwa` HMS
    */
    val tengahMalamWIS_HMS = toTimeFullRound2(tengahMalamWIS)
    
    // untuk 2/3 malam
    
    /**
    * 2/3 malam lokal
    */
    val duaPer3MalamWD = ((24 + _shubuhWD - _maghribWD) / 3.0) * 2 + _maghribWD - 24
    
    /**
    * 2/3 malam lokal HMS
    */
    val duaPer3MalamWD_HMS = toTimeFullRound2(duaPer3MalamWD)
    
    /**
    * 2/3 malam istiwa`
    */
    val duaPer3MalamWIS = duaPer3MalamWD + eq_TM + kwd
    
    /**
    * 2/3 malam istiwa` HMS
    */
    val duaPer3MalamWIS_HMS = toTimeFullRound2(duaPer3MalamWIS)
    
    
    
    private val azimuthUTSB = ArahQiblat().arahQiblat(latitude, longitude)[2]
    
    // rashdul qiblat harian
    private val b = 90 - latitude 

    private val pR = Math.toDegrees(atan(1.0 / (cos(Math.toRadians(b)) * tan(Math.toRadians(azimuthUTSB)))))

    private val cA = Math.toDegrees(acos(tan(Math.toRadians(dek)) * tan(Math.toRadians(b)) * cos(Math.toRadians(pR))))

    // roshdul qiblat 1

    private val rq1 = -(pR + cA) / 15

    /**
    * roshdul qiblat 1 lokal
    */
    val rashdu1 = (rq1 + (12 - eq) + ((timeZone * 15) - longitude) / 15).mod(24.0)
    
    /**
    * roshdul qiblat 1 lokal HMS
    */
    val rashdu1HMS = toTimeFullRound2(rashdu1)

    // roshdul qiblat 2
    private val rQ = -(pR - cA) / 15

    /**
    * roshdul qiblat 2 lokal
    */
    val rashdu2 = (rQ + (12 - eq) + ((timeZone * 15) - longitude) / 15).mod(24.0)
    
    /**
    * roshdul qiblat 2 lokal HMS
    */
    val rashdu2HMS = toTimeFullRound2(rashdu2)
	
	
	private val LATITUDEKABAH = ArahQiblat().LATITUDEKABAH
	private val LONGITUDEKABAH = ArahQiblat().LONGITUDEKABAH
	
	
    private val selisih = (longitude - LONGITUDEKABAH)
	
	/**
	 * selisih jam antara markaz ~ makkah
	 */ 
    val selisihJam = selisih / 15
    
    /**
	 * selisih jam antara markaz ~ makkah HMS HH h MM m SS,ss s
	 */ 
    val selisihJamHMS = toCounterHHMMSS2(selisihJam)
    
	private val nilaiAwal = Math.toDegrees(acos(sin(Math.toRadians(latitude)) * sin(Math.toRadians(LATITUDEKABAH)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(LATITUDEKABAH)) * cos(Math.toRadians(selisih))))
	
    /**
	 * jarak antara markas ~ makkah (kilometer)
	 */ 
    val jarakKeduanya = nilaiAwal / 360 * 6.283185307 * 6378.388
    
    /**
	 * 
	 * selisih deklinasi dengan lintang ka'bah
	 */
    val selisihDecLintangKabah = dek - LATITUDEKABAH
    
    /**
    * selisih deklinasi dengan lintang ka'bah DMS
    */
    val selisihDecLintangKabahDMS = toDegreeFullRound2(selisihDecLintangKabah)
    
    /**
	 * 
	 * selisih deklinasi dengan lintang tempat
	 * 
	 */
    val selisihDecLintangTempat = dek - latitude
    
    /**
	 * 
	 * selisih deklinasi dengan lintang tempat DMS
	 * 
	 */
    val selisihDecLintangTempatDMS = toDegreeFullRound2(selisihDecLintangTempat)
	
    
}
