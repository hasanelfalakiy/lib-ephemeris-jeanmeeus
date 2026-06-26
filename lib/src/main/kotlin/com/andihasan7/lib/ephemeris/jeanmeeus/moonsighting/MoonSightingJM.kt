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
 
package com.andihasan7.lib.ephemeris.jeanmeeus.moonsighting

import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.correction.Correction
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.ConjunctionReturn
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DateFormat
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DistanceType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonActivityType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonAltType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.PhaseType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.PositionType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.SunAltType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.moonactivity.MoonActivity
import com.andihasan7.lib.ephemeris.jeanmeeus.moonphase.MoonPhaseJM
import com.andihasan7.lib.ephemeris.jeanmeeus.moonposition.MoonPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.sunposition.SunPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.DeltaT
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import kotlin.math.floor
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan


/**
 * , 
 */
 
/**
* MoonSighting
* Data on the position of the sun and moon during ijtima and support for adding days
*
* ```
*    var monthOfHijri: Int = 1, // month of hijri
*    var yearOfHijri: Int = 1446, // year of hijri
*    var longitude: Double = 0.0, // longitude of observer
*    var latitude: Double = 0.0, // latitude of observer
*    var elevation: Double = 0.0, // elevation of observer
*    var timeZone: Double = 0.0, // time zone of observer
*    var addDate: Int = 0, // additional day/date
*    var checkDeltaT: Boolean = true, // choice to use deltaT or not
*    var temperature: Double = 10.0, // average annual local temperature (in °C)
*    var pressure: Double = 1010.0, // annual average local air pressure (in millibars)
* ```
*/ 
class MoonSightingJM(
    var monthOfHijri: Int = 1, // month of hijri
    var yearOfHijri: Int = 1446, // year of hijri
    var longitude: Double = 0.0, // longitude of observer
    var latitude: Double = 0.0, // latitude of observer
    var elevation: Double = 0.0, // elevation of observer
    var timeZone: Double = 0.0, // time zone of observer
    var addDate: Int = 0, // additional day/date
    var deltaTMode: Int = 0, // choice deltaT mode: 0=polynomial, 1=ignore, 2=custom
    var customDeltaT: Double = 0.0, // custom deltaT
    var temperature: Double = 10.0, // average annual local temperature (in °C)
    var pressure: Double = 1010.0, // annual average local air pressure (in millibars)
) {

    /**
     * jd New Moon from Astronomical Algorithm book without tabular interpolation
     */
    val jdNewMoonAstronomicalAlgorithm get() = MoonPhaseJM.moonPhase(monthOfHijri, yearOfHijri, PhaseType.NEWMOON)

    /**
     * JD julian day when new moon/JD ijtima with tabular interpolation without deltaT
     */
    val jdGeoNewMoon get() = MoonPhaseJM.moonGeoConjunction(monthOfHijri, yearOfHijri, 0.0, ConjunctionReturn.JDCONJUNCTION)

    /**
     * jd when maghrib with tabular interpolation with custom deltaT mode
     */
    val jdGhurubSyams get() = SunPosition.jdMaghrib(jdGeoNewMoon, longitude, latitude, elevation, timeZone, deltaTMode, customDeltaT)

    /**
     * jd when maghrib + addDate
     */
    val jdGhurubSyamsPlus get() = SunPosition.jdMaghrib(jdGeoNewMoon + addDate, longitude, latitude, elevation, timeZone, deltaTMode, customDeltaT)

    /**
     * deltaT without addDate
     */
    val deltaTNoAdd get() = when (deltaTMode) {
        0 -> DeltaT.deltaT(floor(jdGeoNewMoon) + 0.5)
        1 -> 0.0
        2 -> customDeltaT
        else -> DeltaT.deltaT(floor(jdGeoNewMoon) + 0.5)
    }

    /**
     * deltaT + addDate
     */
    val deltaT get() = when (deltaTMode) {
        0 -> DeltaT.deltaT(floor(jdGeoNewMoon + addDate) + 0.5)
        1 -> 0.0
        2 -> customDeltaT
        else -> DeltaT.deltaT(floor(jdGeoNewMoon + addDate) + 0.5)
    }
    
    /**
     * deltaT round 2
     */
    val deltaT2 get() = ConvertUtil.run { (deltaT).round(2) }

    /**
     * jd julian day geo when new moon/JD ijtima with deltaT
     */
    val jdGeoNewMoonCor get() = MoonPhaseJM.moonGeoConjunction(monthOfHijri, yearOfHijri, deltaTNoAdd, ConjunctionReturn.JDCONJUNCTION)
    
    // jdGeoNewMoonCor + addDate for argumen elp
    val jdGeoNewMoonCorPlus get() = jdGeoNewMoonCor + addDate

    /**
     * longitude new moon geocentric
     */
    val lonGeoNewMoon get() = MoonPhaseJM.moonGeoConjunction(monthOfHijri, yearOfHijri, deltaT, ConjunctionReturn.LONGITUDE)
    
    /**
     * longitude new moon geocentric DMS
     */
    val lonGeoNewMoonDMS get() = ConvertUtil.toDegreeFullRound2(lonGeoNewMoon)

    /**
     * jd julian day topo when new moon/jd ijtima with deltaT
     */
    val jdTopoNewMoon get() = MoonPhaseJM.moonTopoConjunction(monthOfHijri, yearOfHijri, deltaT, longitude, latitude, elevation, ConjunctionReturn.JDCONJUNCTION)

    /**
     * longitude new moon topocentric
     */
    val lonTopoNewMoon get() = MoonPhaseJM.moonTopoConjunction(monthOfHijri, yearOfHijri, deltaT, longitude, latitude, elevation, ConjunctionReturn.LONGITUDE)
    
    /**
     * longitude new moon topocentric DMS
     */
    val lonTopoNewMoonDMS get() = ConvertUtil.toDegreeFullRound2(lonTopoNewMoon)

    /**
     * date when new moon DPDDMMYYM format from astronomical algorithm
     */
    val dateNewMoonAA get() = TimeUtil.jdToGregorian<String>(jdNewMoonAstronomicalAlgorithm, timeZone, DateFormat.DPDDMMYYM)

    /**
     * date when new moon geocentric DPDDMMYYM format
     */
    val dateNewMoonGeo get() = TimeUtil.jdToGregorian<String>(jdGeoNewMoonCor, timeZone, DateFormat.DPDDMMYYM)
    
    /**
     * date when new moon topocentric DPDDMMYYM format
     */
    val dateNewMoonTopo get() = TimeUtil.jdToGregorian<String>(jdTopoNewMoon, timeZone, DateFormat.DPDDMMYYM)
    
    /**
    * day when new moon
    */
    val dayNewMoonGeo get() = TimeUtil.jdToGregorian<String>(jdGeoNewMoonCor, timeZone, DateFormat.DAY_NAME)
    
    /**
    * pasaran when new moon
    */
    val pasaranNewMoonGeo get() = TimeUtil.jdToGregorian<String>(jdGeoNewMoonCor, timeZone, DateFormat.PASARAN_NAME)
    
    /**
     * local hour when new moon geocentric from astronomical algorithm
     */
    val hourGeoNewMoonAA get() = TimeUtil.jdToGregorian<Double>(jdNewMoonAstronomicalAlgorithm, timeZone, DateFormat.HOUR_DOUBLE)
    
    /**
     * local hour when new moon geocentric from astronomical algorithm HMS
     */
    val hourGeoNewMoonAAHMS get() = ConvertUtil.toTimeFullRound2(hourGeoNewMoonAA ?: 0.0)

    /**
     * local hour when new moon geocentric
     */
    val hourGeoNewMoon get() = TimeUtil.jdToGregorian<Double>(jdGeoNewMoonCor, timeZone, DateFormat.HOUR_DOUBLE)
    
    /**
     * local hour when new moon geocentric HMS
     */
    val hourGeoNewMoonHMS get() = ConvertUtil.toTimeFullRound2(hourGeoNewMoon ?: 0.0)
    
    /**
     * UTC hour when new moon geocentric
     */
    val utcGeoHourNewMoon get() = TimeUtil.jdToGregorian<Double>(jdGeoNewMoonCor, 0.0, DateFormat.HOUR_DOUBLE)
    
    /**
     * UTC hour when new moon geocentric HMS
     */
    val utcGeoHourNewMoonHMS get() = ConvertUtil.toTimeFullRound2(utcGeoHourNewMoon ?: 0.0)
    
    /**
     * local hour when new moon topocentric 
     */
    val hourTopoNewMoon get() = TimeUtil.jdToGregorian<Double>(jdTopoNewMoon, timeZone, DateFormat.HOUR_DOUBLE)
    
    /**
     * local hour when new moon topocentric HMS
     */
    val hourTopoNewMoonHMS get() = ConvertUtil.toTimeFullRound2(hourTopoNewMoon ?: 0.0)
    
    /**
     * UTC hour when new moon topocentric 
     */
    val utcTopoHourNewMoon get() = TimeUtil.jdToGregorian<Double>(jdTopoNewMoon, 0.0, DateFormat.HOUR_DOUBLE)
    
    /**
     * UTC hour when new moon topocentric HMS
     */
    val utcTopoHourNewMoonHMS get() = ConvertUtil.toTimeFullRound2(utcTopoHourNewMoon ?: 0.0)

    /**
     * date new moon int
     */
    val dateNMInt get() = TimeUtil.jdToGregorian<Int>(jdGeoNewMoonCor, timeZone, DateFormat.DATE)
    /**
     * month new moon int
     */
    val monthNMInt get() = TimeUtil.jdToGregorian<Int>(jdGeoNewMoonCor, timeZone, DateFormat.MONTH_INT)
    /**
     * month name new moon
     */
    val monthNMName get() = TimeUtil.jdToGregorian<String>(jdGeoNewMoonCor, timeZone, DateFormat.MONTH_NAME)
    /**
     * year new moon int
     */
    val yearNMInt get() = TimeUtil.jdToGregorian<Int>(jdGeoNewMoonCor, timeZone, DateFormat.YEAR)
    
    /**
     * date sunset plus add date int
     */
    val dateSSetInt get() = TimeUtil.jdToGregorian<Int>(jdGhurubSyamsPlus, timeZone, DateFormat.DATE)
    /**
     * month sunset plus add date int
     */
    val monthSSetInt get() = TimeUtil.jdToGregorian<Int>(jdGhurubSyamsPlus, timeZone, DateFormat.MONTH_INT)
    /**
     * month name sunset plus add date
     */
    val monthSSetName get() = TimeUtil.jdToGregorian<String>(jdGhurubSyamsPlus, timeZone, DateFormat.MONTH_NAME)
    /**
     * year sunset plus add date int
     */
    val yearSSetInt get() = TimeUtil.jdToGregorian<Int>(jdGhurubSyamsPlus, timeZone, DateFormat.YEAR)
    

    /**
     * maghrib local
     */
    val maghribLocalDateNewMoon get() = TimeUtil.jdToGregorian<Double>(jdGhurubSyamsPlus, timeZone, DateFormat.HOUR_DOUBLE)
    
    /**
     * maghrib local HMS
     */
    val maghribLocalDateNewMoonHMS get() = ConvertUtil.toTimeFullRound2(maghribLocalDateNewMoon ?: 0.0)

    
    /**
    * dip/ku
    */
    val dip get() = Correction.dip(elevation)

    /**
     * moon airless geocentric altitude
     */
    val moonGeoAltitude get() = MoonPosition.moonGeoAltitude(jdGhurubSyamsPlus, longitude, latitude, deltaT)

    /**
     * moon airless geocentric altitude DMS, t hakiki
     */
    val moonGeoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoAltitude)

    // airless topocentric
    /**
     * moon airless topo altitude upper limb
     */
    val moonAirlessTopoAltitudeUpperLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.AIRLESS_UPPER, temperature, pressure)
    /**
     * moon airless topo altitude upper limb DMS
     */
    val moonAirlessTopoAltitudeUpperLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAirlessTopoAltitudeUpperLimb)
    /**
     * moon airless topo altitude center limb
     */
    val moonAirlessTopoAltitudeCenterLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.AIRLESS_CENTER, temperature, pressure)
    /**
     * moon airless topo altitude center limb DMS
     */
    val moonAirlessTopoAltitudeCenterLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAirlessTopoAltitudeCenterLimb)
    /**
     * moon airless topo altitude lower limb
     */
    val moonAirlessTopoAltitudeLowerLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.AIRLESS_LOWER, temperature, pressure)
    /**
     * moon airless topo altitude lower limb DMS
     */
    val moonAirlessTopoAltitudeLowerLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAirlessTopoAltitudeLowerLimb)

    // moon apparent topo altitude
    /**
     * moon apparent topo alt upper limb
     */
    val moonAppaTopoAltUpperLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.APPARENT_UPPER, temperature, pressure)

    /**
     * moon apparent topo alt upper limb DMS
     */
    val moonAppaTopoAltUpperLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAppaTopoAltUpperLimb)

    /**
     * moon apparent topo alt center limb
     */
    val moonAppaTopoAltCenterLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.APPARENT_CENTER, temperature, pressure)

    /**
     * moon apparent topo alt center limb DMS
     */
    val moonAppaTopoAltCenterLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAppaTopoAltCenterLimb)

    /**
     * moon apparent topo alt lower limb
     */
    val moonAppaTopoAltLowerLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.APPARENT_LOWER, temperature, pressure)

    /**
     * moon apparent topo alt lower limb DMS
     */
    val moonAppaTopoAltLowerLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAppaTopoAltLowerLimb)

    // observed topo alt, mar'i
    /**
     * moon observed topo alt upper limb
     */
    val moonObservedTopoAltUpperLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_UPPER, temperature, pressure)

    /**
     * moon observed topo alt upper limb DMS
     */
    val moonObservedTopoAltUpperLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonObservedTopoAltUpperLimb)

    /**
     * moon observed topo alt center limb
     */
    val moonObservedTopoAltCenterLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_CENTER, temperature, pressure)

    /**
     * moon observed topo alt center limb DMS
     */
    val moonObserrvedTopoAltCenterLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonObservedTopoAltCenterLimb)

    /**
     * moon observed topo alt lower limb
     */
    val moonObservedTopoAltLowerLimb get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_LOWER, temperature, pressure)

    /**
     * moon observed topo alt lower limb DMS
     */
    val moonObservedTopoAltLowerLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonObservedTopoAltLowerLimb)
    
    /**
    * sun topocentric longitude 
    */
    val sunTopoLongitude get() = SunPosition.sunTopoLongitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * sun topocentric longitude DMS
    */
    val sunTopoLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoLongitude)
    
    /**
    * sun topocentric latitude 
    */
    val sunTopoLatitude get() = SunPosition.sunTopoLatitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * sun topocentric latitude DMS
    */
    val sunTopoLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoLatitude)
    
    /**
    * moon topocentric longitude
    */
    val moonTopoLongitude get() = MoonPosition.moonTopoLongitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon topocentric longitude DMS
    */
    val moonTopoLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoLongitude)
    
    /**
    * moon topocentric latitude
    */
    val moonTopoLatitude get() = MoonPosition.moonTopoLatitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon topocentric latitude DMS
    */
    val moonTopoLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoLatitude)
    
    /**
    * sun appa geocentric right ascension
    */
    val sunGeoRightAscension get() = SunPosition.sunApparentGeoRightAscension(jdGhurubSyamsPlus, deltaT)
    
    /**
    * sun apparent geo right ascension DMS
    */
    val sunGeoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(sunGeoRightAscension)
    
    /**
    * sun apparent geo right ascension hour
    */
    val sunGeoRightAscensionHour get() = sunGeoRightAscension / 15
    
    /**
    * sun apparent geo right ascension HMS
    */
    val sunGeoRightAscensionHMS get() = ConvertUtil.toTimeFullRound2(sunGeoRightAscensionHour)
    
    /**
    * sun apparent topo right ascension
    */
    val sunTopoRightAscension get() = SunPosition.sunTopoRightAscension(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * sun apparent topo right ascension DMS
    */
    val sunTopoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoRightAscension)
    
    /**
    * sun apparent topo right ascension hour
    */
    val sunTopoRightAscensionHour get() = sunTopoRightAscension / 15
    
    /**
    * sun apparent topo right ascension HMS
    */
    val sunTopoRightAscensionHMS get() = ConvertUtil.toTimeFullRound2(sunTopoRightAscensionHour)
    
    /**
    * moon geo right ascension
    */
    val moonGeoRightAscension get() = MoonPosition.moonAppaGeocentricRightAscension(jdGhurubSyamsPlus, deltaT)
    
    /**
    * moon apparent geo right ascension DMS
    */
    val moonGeoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoRightAscension)
    
    /**
    * moon apparent geo right ascension hour
    */
    val moonGeoRightAscensionHour get() = moonGeoRightAscension / 15
    
    /**
    * moon apparent geo right ascension HMS
    */
    val moonGeoRightAscensionHMS get() = ConvertUtil.toTimeFullRound2(moonGeoRightAscensionHour)
    
    /**
    * moon apparent topo right ascension
    */
    val moonTopoRightAscension get() = MoonPosition.moonTopoRightAscension(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon apparent topo right ascension DMS
    */
    val moonTopoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoRightAscension)
    
    /**
    * moon apparent topo right ascension hour
    */
    val moonTopoRightAscensionHour get() = moonTopoRightAscension / 15
    
    /**
    * moon apparent topo right ascension HMS
    */
    val moonTopoRightAscensionHMS get() = ConvertUtil.toTimeFullRound2(moonTopoRightAscensionHour)
    
    /**
    * sun topo declination
    */
    val sunTopoDeclination get() = SunPosition.sunTopoDeclination(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * sun topo declination DMS
    */
    val sunTopoDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoDeclination)
    
    /**
    * moon geo declination
    */
    val moonGeoDeclination get() = MoonPosition.moonAppaGeocentricDeclination(jdGhurubSyamsPlus, deltaT)
    
    /**
    * moon geo declination DMS
    */
    val moonGeoDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoDeclination)
    
    /**
    * moon topo declination
    */
    val moonTopoDeclination get() = MoonPosition.moonTopoDeclination(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon topo declination DMS
    */
    val moonTopoDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoDeclination)
    
    /**
    * sun topo azimuth
    */
    val sunTopoAzimuth get() = SunPosition.sunTopoAzimuth(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * sun topo azimuth DMS
    */
    val sunTopoAzimuthDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoAzimuth)
    
    /**
    * sun airless topo altitude
    */
    val sunTopoAltitude get() = SunPosition.sunTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * sun airless topo altitude DMS
    */
    val sunTopoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoAltitude)
    
    /**
    * moon topo azimuth
    */
    val moonTopoAzimuth get() = MoonPosition.moonTopoAzimuth(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon topo azimuth DMS
    */
    val moonTopoAzimuthDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoAzimuth)
    
    /**
    * moon illuminated
    */
    val moonTopoIlluminated get() = MoonPosition.moonTopoDiskIlluminatedFraction(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon illuminated percent
    */
    val moonTopoIlluminatedPercent get() = moonTopoIlluminated * 100
    
    /**
    * moon illuminated percent round 2
    */
    val moonTopoIlluminatedPercent2 get() = ConvertUtil.run { (moonTopoIlluminatedPercent).round(2) }
    
    /**
    * moon geo semidiameter
    */
    val moonGeoSemidiameter get() = MoonPosition.moonGeoSemidiameter(jdGhurubSyamsPlus, deltaT)
    
    /**
    * moon geo semidiameter DMS
    */
    val moonGeoSemidiameterDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoSemidiameter)
    
    /**
    * moon topo semidiameter
    */
    val moonTopoSemidiameter get() = MoonPosition.moonTopoSemidiameter(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon topo semidiameter
    */
    val moonTopoSemidiameterDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoSemidiameter)
    
    /**
    * moon-sun geo elongation
    */
    val moonSunGeoElongation get() = MoonPosition.moonSunGeoElongation(jdGhurubSyamsPlus, deltaT)
    
    /**
    * moon-sun geo elongation DMS
    */
    val moonSunGeoElongationDMS get() = ConvertUtil.toDegreeFullRound2(moonSunGeoElongation)
    
    /**
    * moon-sun topo elongation
    */
    val moonSunTopoElongation get() = MoonPosition.moonSunTopoElongation(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT)
    
    /**
    * moon-sun topo elongation DMS
    */
    val moonSunTopoElongationDMS get() = ConvertUtil.toDegreeFullRound2(moonSunTopoElongation)
    
    /**
    * moon horizontal parallax
    */
    val moonHorizontalParallax get() = MoonPosition.moonEquatorialHorizontalParallax(jdGhurubSyamsPlus, deltaT)
    
    /**
    * moon horizontal parallax DMS
    */
    val moonHorizontalParallaxDMS get() = ConvertUtil.toDegreeFullRound2(moonHorizontalParallax)
    
    /**
    * moon geo distance km
    */
    val moonGeoDistanceKM get() = MoonPosition.moonGeocentricDistance(jdGhurubSyamsPlus, deltaT, PositionType.APPARENT, DistanceType.KM)
    
    /**
    * moon geo distance km round 2
    */
    val moonGeoDistanceKM2 get() = ConvertUtil.run { (moonGeoDistanceKM).round(2) }
    
    
    /**
    * muktsul hilal taqribi
    */
    val hilalDurationTaqribi get() = (moonGeoAltitude * (4.0 / 60))
    
    /**
    * muktsul hilal taqribi HMS
    */
    val hilalDurationTaqribiHMS get() = ConvertUtil.toCounterHHMMSS2(hilalDurationTaqribi)
    
    /**
    * diff RA sun - RA moon
    */
    val diffRASunMoon get() = (moonTopoRightAscension - sunTopoRightAscension)
    
    /**
    * hilal duration/muktsu from diff RA sun - RA moon
    */
    val hilalDurationOld get() = if (moonTopoRightAscension < sunTopoRightAscension) { // (diffRASunMoon / 15).mod(24.0)
        (0.0 - (diffRASunMoon / 15)).mod(24.0) // ((moonTopoRightAscension + 360) - sunTopoRightAscension) / 15
    } else {
        (diffRASunMoon / 15).mod(24.0)
    } 
    
    /**
    * hilal duration/muktsu Counter HMS from diff RA sun - RA moon
    */
    val hilalDurationOldHMS get() = ConvertUtil.toCounterHHMMSS2(hilalDurationOld)

    /**
     * moon set taqribi hc * 0 4m
     */
    val moonSetTaqribi get() = (maghribLocalDateNewMoon ?: 0.0) + hilalDurationTaqribi
    
    /**
    * moon set/hilal terbenam from Astronomical Algorithm & Explanatory Supplement 
    */
    val moonSet get() = MoonActivity.moonActivity(dateSSetInt ?: 0, monthSSetInt ?: 0, yearSSetInt ?: 0, longitude, latitude, elevation, timeZone, 2, MoonActivityType.SET, true, deltaTMode, customDeltaT)
    
    /**
    * moon set/hilal terbenam HMS from Astronomical Algorithm & Explanatory Supplement
    */
    val moonSetHMS get() = ConvertUtil.toTimeFullRound2(moonSet ?: 0.0)

    /**
     * moon set/hilal terbenam from diff ARm - ARs / 15
     */
    val moonSetDiffAR get() = (maghribLocalDateNewMoon ?: 0.0) + hilalDurationOld

    /**
     * moon set/hilal terbenam from diff ARm - ARs / 15 HMS
     */
    val moonSetDiffARHMS get() = ConvertUtil.toTimeFullRound2(moonSetDiffAR)

    // =========== rumus mencari lama hilal dan terbenam hilal ephemeris kemenag =========

    // sun geo dec
    val sunApparentGeoDeclination get() = SunPosition.sunApparentGeoDeclination(jdGhurubSyamsPlus, deltaT)
    // sun geo semidiameter
    val sunApparentGeoSemidiameter get() = SunPosition.sunApparentGeoSemidiameter(jdGhurubSyamsPlus, deltaT)
    // tinggi matahari ho
    val ho get() = 0 - sunApparentGeoSemidiameter - 0.575 - dip // 0.575 = 0 34 30

    // sudut waktu matahari (to)
    val to get() = Math.toDegrees(acos(-tan(Math.toRadians(latitude)) * tan(Math.toRadians(sunApparentGeoDeclination)) + sin(Math.toRadians(ho)) / cos(Math.toRadians(latitude)) / cos(Math.toRadians(sunApparentGeoDeclination))))
    // sudut waktu bulan (tc)
    val tc get() = sunGeoRightAscension - moonGeoRightAscension + to
    // nisful fudlah NF

    val nf: Double get() {
        val x =
            (sin(Math.toRadians(latitude)) *
                    sin(Math.toRadians(moonGeoDeclination))) /
                    (cos(Math.toRadians(latitude)) *
                            cos(Math.toRadians(moonGeoDeclination)))

        return abs(Math.toDegrees(asin(x)))
    }
    // val nf get() = abs(Math.toDegrees(asin((sin(Math.toRadians(latitude)) * sin(Math.toRadians(moonGeoDeclination))) / (cos(Math.toRadians(latitude)) * cos(Math.toRadians(moonGeoDeclination))))))
    // parallax nisful fudlah PNF
    val pnf get() = cos(Math.toRadians(nf)) * moonHorizontalParallax
    // setengah busur siang bulan hakiki SBSH
    val sbsh get() = 90 + nf
    // setengah busur siang bulan SBS
    val sbs get() = if (sbsh >= 90.0) {
        90 + nf - pnf + (moonGeoSemidiameter + 0.575 + dip)
    } else {
        90 + nf + pnf - (moonGeoSemidiameter + 0.575 + dip)
    }
    // lama hilal LH
    val hilalDurationEphe get() = ((sbs - tc) / 15) //.mod(24.0)
    // hilal terbenam
    val moonSetEphe get() = (maghribLocalDateNewMoon ?: 0.0) + hilalDurationEphe

    // ==============================


    /**
    * moon age/umur hilal
    */
    val moonAge get() = if (addDate == 0) {
        (maghribLocalDateNewMoon ?: 0.0) - (hourGeoNewMoon ?: 0.0)
    } else {
        24.0 + ((maghribLocalDateNewMoon ?: 0.0) - (hourGeoNewMoon ?: 0.0))
    }
    
    /**
    * moon age/umur hilal Counter HMS from diff RA sun - RA moon
    */
    val moonAgeHMS get() = ConvertUtil.toCounterHHMMSS2(moonAge)
    
    private val _jdMSet get() = TimeUtil.gregorianToJD(dateSSetInt ?: 0, monthSSetInt ?: 0, yearSSetInt ?: 0)
    /**
    * jd moon set
    */
    val jdMoonSet get() = (_jdMSet ?: 0.0) + ((moonSet ?: 0.0) - timeZone) / 24.0
    
    /**
    * moon muktsul hilal
    */
    // val moonDuration get() = ((jdMoonSet - jdGhurubSyamsPlus) * 24.0)
    
    /**
    * moon best time
    */
    val moonBestTime get() = (maghribLocalDateNewMoon ?: 0.0) + 4.0 / 9.0 * ((jdMoonSet - jdGhurubSyamsPlus) * 24.0)
    
    /**
    * moon best time HMS
    */
    val moonBestTimeHMS get() = ConvertUtil.toTimeFullRound2(moonBestTime)
    
    /**
    * crecent width topo/lebar hilal
    */
    val crecentWidthTopo get() = (moonTopoSemidiameter) * (1 - cos(Math.toRadians(moonSunTopoElongation)))
    
    /**
    * crecent width/lebar hilal DMS
    */
    val crecentWidthTopoDMS get() = ConvertUtil.toDegreeFullRound2(crecentWidthTopo)
    
    /**
    * moon topo azimuth set
    */
    val moonTopoAzimuthSet get() = MoonPosition.moonTopoAzimuth(jdMoonSet, longitude, latitude, elevation, deltaT)
    
    /**
    * moon topo azimuth set DMS
    */
    val moonTopoAzimuthSetDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoAzimuthSet)
    
    /**
    * related topo alt
    */
    val relatedTAlt get() = MoonPosition.moonTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT) + abs(SunPosition.sunTopoAltitude(jdGhurubSyamsPlus, longitude, latitude, elevation, deltaT, SunAltType.AIRLESS, UnitType.DEGREES, temperature, pressure))
    
    /**
    * range q Odeh
    */
    val qOdeh get() = relatedTAlt - (-0.1018 * (crecentWidthTopo * 60).pow(3) + 0.7319 * (crecentWidthTopo * 60).pow(2) - 6.3226 * (crecentWidthTopo * 60) + 7.1651)
    
    /**
    * range q Odeh round 3
    */
    val qOdeh3 get() = ConvertUtil.run { (qOdeh).round(3) }
    
    /**
    * moon topo position
    */
    val moonTopoPosition get() = moonTopoAzimuth - sunTopoAzimuth
    
    /**
    * moon topo position DMS
    */
    val moonTopoPositionDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoPosition)
    
    /**
    * moon topo position string, - selatan (south) + utara (north)
    */
    val moonTopoPositionString get() = if (moonTopoPosition < 0.0) {
        "Selatan Matahari"
    } else {
        "Utara Matahari"
    }
    
    /**
    * nurul hilal
    */
    val nurulHilal get() = (sqrt((moonTopoPosition).pow(2) + (moonObservedTopoAltCenterLimb).pow(2))) / 15
    
    /**
    * nurul hilal round 2
    */
    val nurulHilal2 get() = ConvertUtil.run { (nurulHilal).round(2) }
    
    /**
    * crescent moon tilt/kemiringan hilal
    */
    val mrg get() = Math.toDegrees(atan(moonTopoPosition / moonObservedTopoAltCenterLimb))
    
    /**
    * 
    */
    val mrg2 get() = ConvertUtil.run { (mrg).round(2) }
    
    /**
    * crescent moon tilt string/kemiringan hilal
    */
    val mrgString get() = if (mrg <= 15.0) {
        "Telentang"
    } else if (mrg > 15.0 && moonTopoPosition > 0.0) {
        "Miring ke Utara"
    } else if (mrg > 15.0 && moonTopoPosition < 0.0) {
        "Miring ke Selatan"
    } else {
        "Telentang"
    }
    
    /**
    * altitude hilal without add date
    */
    val tHilal get() = MoonPosition.moonTopoAltitude(jdGhurubSyams, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_CENTER, temperature, pressure)
    
    /**
    * moon-sun geocentric elongation without add date
    */
    val moonSunElo get() = MoonPosition.moonSunGeoElongation(jdGhurubSyams, deltaT)
    
    private val _irNU get() = if (tHilal >= 3.0 && moonSunElo >= 6.4) {
        1 // enter the new month
    } else {
        2 // istikmal
    }
    
    /**
    * raw prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val _predictionIRNU get() = ((floor(jdGeoNewMoonCor + 0.5 + timeZone / 24.0)) - timeZone / 24.0) + _irNU
    
    /**
    * prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val predictionIRNU get() = TimeUtil.jdToGregorian<String>(_predictionIRNU, timeZone, DateFormat.DPDDMMYYM)
    /**
    * day of prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val dayOfIRNUPrediction get() = TimeUtil.jdToGregorian<String>(_predictionIRNU, timeZone, DateFormat.DAY_NAME)
    /**
    * pasaran of prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val pasaranOfIRNUPrediction get() = TimeUtil.jdToGregorian<String>(_predictionIRNU, timeZone, DateFormat.PASARAN_NAME)
    /**
    * date of prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val dateOfIRNUPrediction get() = TimeUtil.jdToGregorian<Int>(_predictionIRNU, timeZone, DateFormat.DATE)
    /**
    * month of prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val monthOfIRNUPredictionString get() = TimeUtil.jdToGregorian<String>(_predictionIRNU, timeZone, DateFormat.MONTH_NAME)
    /**
    * year of prediction based of IRNU/neo mabims 3°, 6.4°
    */
    val yearOfIRNUPrediction get() = TimeUtil.jdToGregorian<Int>(_predictionIRNU, timeZone, DateFormat.YEAR)
    
    /**
    * is visible IRNU/neo mabims
    */
    val isVisibleIRNU get() = if (tHilal >= 3.0 && moonSunElo >= 6.4) {
        "Visible" // enter the new month
    } else {
        "Not Visible" // istikmal
    } 
    
    

}
