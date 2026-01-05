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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.prayertimes

import com.andihasan7.lib.ephemeris.jeanmeeus.SunJM
import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.qibla.QiblaAzimuth
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.tan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.mod

/**
 *
 * Prayer Times
 *
 * ```
 *    date: Int = 1, // gregorian date
 *    month: Int = 1, // gregorian month
 *    year: Int = 2000, // gregorian year
 *    latitude: Double = 0.0, // latitude of observer
 *    longitude: Double = 0.0, // longitude of observer
 *    elevation: Double = 0.0, // elevation of observer
 *    timeZone: Double = 0.0, // time zone
 *    ihtiyatDzuhur: Int = 0, // dzuhur ihtiyat
 *    otherIhtiyat: Int = 0 // ihtiyat other than dzuhur is added except sunrise is reduced
 * ```
 */
class PrayerTimes(
    date: Int,
    month: Int,
    year: Int,
    latitude: Double,
    longitude: Double,
    elevation: Double,
    timeZone: Double,
    ihtiyatDzuhur: Int = 0,
    otherIhtiyat: Int = 0
) {

    // for dzuhur
    private val jm = SunJM(
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
     * declination 12 noon
     */
    val dek = jm.sunApparentGeoDeclination

    /**
     * equation of time 12 noon
     */
    val eq = jm.equationOfTime

    /**
     * semidiameter 12 noon
     */
    val semidiameter = jm.sunApparentGeoSemidiameter

    // private val transit = 12 + timeZone - longitude / 15 - eq
    private val meridianPass = 12 - eq

    /**
     * time transit / dzuhur local time/lokal/daerah
     */
    val dzuhurWD = meridianPass - kwd + (ihtiyatDzuhur.toDouble() / 60)

    /**
     * dzuhur local HMS
     */
    val dzuhurWD_HMS = ConvertUtil.toTimeFullRound2(dzuhurWD)

    /**
     * dzuhur istiwa`
     */
    val dzuhurWIS = dzuhurWD + eq + kwd

    /**
     * dzuhur istiwa` HMS
     */
    val dzuhurWIS_HMS = ConvertUtil.toTimeFullRound2(dzuhurWIS)

    // for ashar
    private val jma = SunJM(
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
    private val eq_ash = jma.equationOfTime
    private val meridianPassAshar = 12 - eq_ash
    private val zm = abs(latitude - dek_ash)
    private val alt_ashar = Math.toDegrees(atan(1.0 / (tan(Math.toRadians(zm)) + 1)))
    private val t_ashar = Math.toDegrees(acos((sin(Math.toRadians(alt_ashar)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_ash))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_ash)))))

    /**
     * ashar local
     */
    val asharWD = meridianPassAshar + (t_ashar / 15) - kwd + (otherIhtiyat.toDouble() / 60)

    /**
     * ashar local HMS
     */
    val asharWD_HMS = ConvertUtil.toTimeFullRound2(asharWD)

    /**
     * ashar istiwa`
     */
    val asharWIS = asharWD + eq_ash + kwd

    /**
     * ashar istiwa` HMS
     */
    val asharWIS_HMS = ConvertUtil.toTimeFullRound2(asharWIS)

    // for maghrib
    private val jmm = SunJM(
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
                    ) // convert to double here to make it easier to customize the elevation of observer
        } else { // elevation is set to 0 when elevation is dedtected below 0/minus
            (1.76 / 60) *
                    sqrt(
                        (0.0)
                    )
        }
    private val dek_maghr = jmm.sunApparentGeoDeclination
    private val eq_maghr = jmm.equationOfTime
    private val semid_maghr = jmm.sunApparentGeoSemidiameter
    private val meridianPassMaghr = 12 - eq_maghr

    private val hMaghrib = -((34.0 / 60) + semid_maghr + dip)
    private val t_maghr = Math.toDegrees(acos((sin(Math.toRadians(hMaghrib)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_maghr))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_maghr)))))

    private val _maghribWD = meridianPassMaghr + (t_maghr / 15) - kwd
    /**
     * maghrib local
     */
    val maghribWD = _maghribWD + (otherIhtiyat.toDouble() / 60)

    /**
     * maghrib local HMS
     */
    val maghribWD_HMS = ConvertUtil.toTimeFullRound2(maghribWD)

    /**
     * maghrib istiwa`
     */
    val maghribWIS = maghribWD + eq_maghr + kwd

    /**
     * maghrib istiwa` HMS
     */
    val maghribWIS_HMS = ConvertUtil.toTimeFullRound2(maghribWIS)

    // for isya`
    private val jmi = SunJM(
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
    private val eq_isya = jmi.equationOfTime
    private val meridianPassIsya = 12 - eq_isya
    private val hIsya = -18.0 // + hMaghrib
    private val t_isya = Math.toDegrees(acos((sin(Math.toRadians(hIsya)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_isya))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_isya)))))

    /**
     * isya local
     */
    val isyaWD = meridianPassIsya + (t_isya / 15) - kwd + (otherIhtiyat.toDouble() / 60)

    /**
     * isya local HMS
     */
    val isyaWD_HMS = ConvertUtil.toTimeFullRound2(isyaWD)

    /**
     * isya istiwa`
     */
    val isyaWIS = isyaWD + eq_isya + kwd

    /**
     * isya istiwa` HMS
     */
    val isyaWIS_HMS = ConvertUtil.toTimeFullRound2(isyaWIS)


    // for shubuh
    private val jms = SunJM(
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
    private val eq_shubuh = jms.equationOfTime
    private val meridianPassShubuh = 12 - eq_shubuh
    private val hShubuh = -20.0 // + hMaghrib
    private val t_shubuh = Math.toDegrees(acos((sin(Math.toRadians(hShubuh)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_shubuh))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_shubuh)))))

    private val _shubuhWD = meridianPassShubuh - (t_shubuh / 15) - kwd
    /**
     * shubuh local
     */
    val shubuhWD = _shubuhWD + (otherIhtiyat.toDouble() / 60)

    /**
     * shubuh local HMS
     */
    val shubuhWD_HMS = ConvertUtil.toTimeFullRound2(shubuhWD)

    /**
     * shubuh istiwa`
     */
    val shubuhWIS = shubuhWD + eq_shubuh + kwd

    /**
     * shubuh istiwa` HMS
     */
    val shubuhWIS_HMS = ConvertUtil.toTimeFullRound2(shubuhWIS)

    // for imsak

    /**
     * imsak local
     */
    val imsakWD = shubuhWD - (10.0 / 60)

    /**
     * imsak local HMS
     */
    val imsakWD_HMS = ConvertUtil.toTimeFullRound2(imsakWD)

    /**
     * imsak istiwa`
     */
    val imsakWIS = imsakWD + eq_shubuh + kwd

    /**
     * imsak istiwa` HMS
     */
    val imsakWIS_HMS = ConvertUtil.toTimeFullRound2(imsakWIS)

    // for terbit
    private val jmt = SunJM(
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
    private val eq_terbit = jmt.equationOfTime
    private val meridianPassTerbit = 12 - eq_terbit

    /**
     * sunrise local
     */
    val terbitWD = meridianPassTerbit - (t_maghr / 15) - kwd - (otherIhtiyat.toDouble() / 60)

    /**
     * sunrise local HMS
     */
    val terbitWD_HMS = ConvertUtil.toTimeFullRound2(terbitWD)

    /**
     * sunrise istiwa`
     */
    val terbitWIS = terbitWD + eq_terbit + kwd

    /**
     * sunrise istiwa` HMS
     */
    val terbitWIS_HMS = ConvertUtil.toTimeFullRound2(terbitWIS)

    // for dluha
    private val jmd = SunJM(
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
    private val eq_dluha = jmd.equationOfTime
    private val meridianPassDluha = 12 - eq_dluha
    private val hDluha = 4.5
    private val t_dluha = Math.toDegrees(acos((sin(Math.toRadians(hDluha)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(dek_dluha))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek_dluha)))))

    /**
     * dluha local
     */
    val dluhaWD = meridianPassDluha - (t_dluha / 15) - kwd + (otherIhtiyat.toDouble() / 60)

    /**
     * dluha local HMS
     */
    val dluhaWD_HMS = ConvertUtil.toTimeFullRound2(dluhaWD)

    /**
     * dluha istiwa`
     */
    val dluhaWIS = dluhaWD + eq_dluha + kwd

    /**
     * dluha istiwa` HMS
     */
    val dluhaWIS_HMS = ConvertUtil.toTimeFullRound2(dluhaWIS)

    // for midnight
    private val jmTM = SunJM(
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
    private val eq_TM = jmTM.equationOfTime

    /**
     * midnight local
     */
    val tengahMalamWD = (((_shubuhWD + 24) - _maghribWD) / 2 + _maghribWD).mod(24.0)

    /**
     * midnight local HMS
     */
    val tengahMalamWD_HMS = ConvertUtil.toTimeFullRound2(tengahMalamWD)

    /**
     * midnight istiwa`
     */
    val tengahMalamWIS = tengahMalamWD + eq_TM + kwd

    /**
     * midnight istiwa` HMS
     */
    val tengahMalamWIS_HMS = ConvertUtil.toTimeFullRound2(tengahMalamWIS)

    // for 2/3 night

    /**
     * 2/3 night local
     */
    val duaPer3MalamWD = ((24 + _shubuhWD - _maghribWD) / 3.0) * 2 + _maghribWD - 24

    /**
     * 2/3 night local HMS
     */
    val duaPer3MalamWD_HMS = ConvertUtil.toTimeFullRound2(duaPer3MalamWD)

    /**
     * 2/3 night istiwa`
     */
    val duaPer3MalamWIS = duaPer3MalamWD + eq_TM + kwd

    /**
     * 2/3 night istiwa` HMS
     */
    val duaPer3MalamWIS_HMS = ConvertUtil.toTimeFullRound2(duaPer3MalamWIS)



    private val azimuthUTSB = QiblaAzimuth().qiblaAzimuth(latitude, longitude)[2]

    // daily rashdul qiblat
    private val b = 90 - latitude

    private val pR = Math.toDegrees(atan(1.0 / (cos(Math.toRadians(b)) * tan(Math.toRadians(azimuthUTSB)))))

    private val cA = Math.toDegrees(acos(tan(Math.toRadians(dek)) * tan(Math.toRadians(b)) * cos(Math.toRadians(pR))))

    // roshdul qiblat 1

    private val rq1 = -(pR + cA) / 15

    /**
     * roshdul qiblat 1 local
     */
    val rashdu1 = (rq1 + (12 - eq) + ((timeZone * 15) - longitude) / 15).mod(24.0)

    /**
     * roshdul qiblat 1 local HMS
     */
    val rashdu1HMS = ConvertUtil.toTimeFullRound2(rashdu1)

    // roshdul qiblat 2
    private val rQ = -(pR - cA) / 15

    /**
     * roshdul qiblat 2 local
     */
    val rashdu2 = (rQ + (12 - eq) + ((timeZone * 15) - longitude) / 15).mod(24.0)

    /**
     * roshdul qiblat 2 local HMS
     */
    val rashdu2HMS = ConvertUtil.toTimeFullRound2(rashdu2)


    private val LATITUDEKABAH = QiblaAzimuth().LATITUDEKABAH
    private val LONGITUDEKABAH = QiblaAzimuth().LONGITUDEKABAH


    private val selisih = (longitude - LONGITUDEKABAH)

    /**
     * different hour between markaz ~ makkah
     */
    val selisihJam = selisih / 15

    /**
     * different hour between markaz ~ makkah HMS HH h MM m SS,ss s
     */
    val selisihJamHMS = ConvertUtil.toCounterHHMMSS2(selisihJam)

    private val nilaiAwal = Math.toDegrees(acos(sin(Math.toRadians(latitude)) * sin(Math.toRadians(LATITUDEKABAH)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(LATITUDEKABAH)) * cos(Math.toRadians(selisih))))

    /**
     * distance between markas ~ makkah (kilometer)
     */
    val jarakKeduanya = nilaiAwal / 360 * 6.283185307 * 6378.388

    /**
     *
     * different declination between latitude ~ ka'ba
     */
    val selisihDecLintangKabah = dek - LATITUDEKABAH

    /**
     * different declination between latitude ~ ka'ba DMS
     */
    val selisihDecLintangKabahDMS = ConvertUtil.toDegreeFullRound2(selisihDecLintangKabah)

    /**
     *
     * different declination between latitude ~ latitude of observer
     *
     */
    val selisihDecLintangTempat = dek - latitude

    /**
     *
     * different declination between latitude ~ latitude of observer DMS
     *
     */
    val selisihDecLintangTempatDMS = ConvertUtil.toDegreeFullRound2(selisihDecLintangTempat)
}
