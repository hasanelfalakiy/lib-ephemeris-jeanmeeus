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

import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.correction.Correction
import com.andihasan7.lib.ephemeris.jeanmeeus.sunposition.SunPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DistanceType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.SunAltType
import com.andihasan7.lib.ephemeris.jeanmeeus.earthposition.EarthPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DateFormat
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.JulianType
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.DeltaT
import kotlin.math.cos
import kotlin.mod

/**
* Sun Datas of Jean Meeus
* Complete Sun Ephemeris Data
*
* ```
*    date: Int = 1, // Gregorian date
*    month: Int = 1, // Gregorian month
*    year: Int = 2000, // Gregorian year
*    latitude: Double = 0.0, // observer latitude
*    longitude: Double = 0.0, // observer longitude
*    elevation: Double = 0.0, // observer elevation in meters
*    timeZone: Double = 0.0, // time zone
*    hourDouble: Double = 0.0, // decimal hour
*    temperature: Double = 10, // average annual local temperature (in °C)
*    pressure: Double = 1010, // annual average local air pressure (in millibars)
*    checkDeltaT: Boolean = true // choice to use deltaT or not, true = deltaT, false = ignore deltaT
* ```
*/
class SunJM(
    var date: Int = 1, // Gregorian date
    var month: Int = 1, // Gregorian month
    var year: Int = 2000, // Gregorian year
    var latitude: Double = 0.0, // observer latitude
    var longitude: Double = 0.0, // observer longitude
    var elevation: Double = 0.0, // observer elevation in meters
    var timeZone: Double = 0.0, // time zone
    var hourDouble: Double = 0.0, // decimal hour
    var temperature: Double = 10.0, // average annual local temperature (in °C)
    var pressure: Double = 1010.0, // annual average local air pressure (in millibars)
    var deltaTMode: Int = 0, // choice deltaT mode: 0=polynomial, 1=ignore, 2=custom
    var customDeltaT: Double = 0.0,
) {
    /**
    * Julian Day
    */
    val jd get() = TimeUtil.gregorianToJD(date, month, year, hourDouble, timeZone)
    
    /**
    * Delta T
    */
    val deltaT: Double get() = when (deltaTMode) {
        0 -> DeltaT.deltaT(jd)
        1 -> 0.0
        2 -> customDeltaT
        else -> DeltaT.deltaT(jd)
    }
    
    /**
    * Number of Date
    */
    val numbDate: Int? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.DATE)
    
    /**
    * Number of Month
    */
    val numbMonth: Int? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.MONTH_INT)
    
    /**
    * Name of Month
    */
    val nameMonth: String? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.MONTH_NAME)
    
    /**
    * Number of Year
    */
    val numbYear: Int? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.YEAR)
    
    /**
    * Number of Day
    */
    val numbDay: Int? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.DAY_INT)
    
    /**
    * Name of Day
    */
    val nameDay: String? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.DAY_NAME)
    
    /**
    * Number of Pasaran
    */
    val numbPasaran: Int? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.PASARAN_INT)
    
    /**
    * Name of pasaran
    */
    val namePasaran: String? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.PASARAN_NAME)
    
    /**
    * Hour Double or Decimal
    */
    val hourDoubleDecimal: Double? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.HOUR_DOUBLE)
    
    /**
    * Frac of Day
    */
    val fracDay: Double? get() = TimeUtil.jdToGregorian(jd, timeZone, DateFormat.FRAC_DAY)
    
    
    
    /**
    * Julian Day Ephemeris, JDE
    */
    val jde get() = TimeUtil.julianType(jd, deltaT, JulianType.JDE)
    
    /**
    * Julian Century (JC) for standart epoch 2000
    */
    val jc get() = TimeUtil.julianType(jd, deltaT, JulianType.JC)
    
    /**
    * Julian Century Ephemeris (JCE) for standart epoch 2000
    */
    val jce get() = TimeUtil.julianType(jd, deltaT, JulianType.JCE)
    
    /**
    * Julian Millennium (JM) for standart epoch 2000
    */
    val jm get() = TimeUtil.julianType(jd, deltaT, JulianType.JM)
    
    /**
    * Julian Millennium Ephemeris (JME) for standart epoch 2000
    */
    val jme get() = TimeUtil.julianType(jd, deltaT, JulianType.JME)
    
    /**
    * Nutation in Longitude, deltaPsi
    * model of IAU 1980
    */
    val nutationInLongitude get() = Nutation.nutationInLonAndObliquity(jd, deltaT)[0]

    /**
     * Nutation in Longitude DMS, deltaPsi
     * model of IAU 1980
     */
    val nutationInLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(nutationInLongitude)
    
    /**
    * Nutation of Obliquity, deltaEpsilon
    * model of IAU 1980
    */
    val nutationInObliquity get() = Nutation.nutationInLonAndObliquity(jd, deltaT)[1]

    /**
     * Nutation of Obliquity DMS, deltaEpsilon
     * model of IAU 1980
     */
    val nutationInObliquityDMS get() = ConvertUtil.toDegreeFullRound2(nutationInObliquity)
    
    /**
    * Mean Obliquity of Ecliptic, epsilon zero
    */
    val meanObliquityOfEcliptic get() = Nutation.meanObliquityOfEcliptic(jd, deltaT)

    /**
     * Mean Obliquity of Ecliptic DMS, epsilon zero
     */
    val meanObliquityOfEclipticDMS get() = ConvertUtil.toDegreeFullRound2(meanObliquityOfEcliptic)
    
    /**
    * True Obliquity of Ecliptic, epsilon
    */
    val trueObliquityOfEcliptic get() = Nutation.trueObliquityOfEcliptic(jd, deltaT)

    /**
     * True Obliquity of EclipticDMS, epsilon
     */
    val trueObliquityOfEclipticDMS get() = ConvertUtil.toDegreeFullRound2(trueObliquityOfEcliptic)
    
    /**
    * Earth Heliocentric Longitude in radian
    */
    val earthHeliocentricLongitudeRadian get() = EarthPosition.earthHeliocentricLongitude(jd, deltaT, UnitType.RADIANS)
    
    /**
    * Earth Heliocentric Longitude in degree
    */
    val earthHeliocentricLongitudeDegree get() = EarthPosition.earthHeliocentricLongitude(jd, deltaT, UnitType.DEGREES)

    /**
     * Earth Heliocentric Longitude DMS in degree
     */
    val earthHeliocentricLongitudeDegreeDMS get() = ConvertUtil.toDegreeFullRound2(earthHeliocentricLongitudeDegree)
    
    /**
    * Earth Heliocentric Latitude in radian
    */
    val earthHeliocentricLatitudeRadian get() = EarthPosition.earthHeliocentricLatitude(jd, deltaT, UnitType.RADIANS)
    
    /**
    * Earth Heliocentric Latitude in degree
    */
    val earthHeliocentricLatitudeDegree get() = EarthPosition.earthHeliocentricLatitude(jd, deltaT, UnitType.DEGREES)

    /**
     * Earth Heliocentric Latitude DMS in degree
     */
    val earthHeliocentricLatitudeDegreeDMS get() = ConvertUtil.toDegreeFullRound2(earthHeliocentricLatitudeDegree)
    
    /**
    * Sun True Geocentric Longitude in degree
    */
    val sunTrueGeocentricLongitude get() = SunPosition.sunTrueGeocentricLongitude(jd, deltaT, UnitType.DEGREES)

    /**
     * Sun True Geocentric Longitude DMS in degree
     */
    val sunTrueGeocentricLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTrueGeocentricLongitude)
    
    /**
    * Sun True Geocentric Latitude in degree
    */
    val sunTrueGeocentricLatitude get() = SunPosition.sunTrueGeocentricLatitude(jd, deltaT, UnitType.DEGREES)

    /**
     * Sun True Geocentric Latitude DMS in degree
     */
    val sunTrueGeocentricLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTrueGeocentricLatitude)
    
    /**
     * Sun True Geocentric Latitude SS,ss in degree
     */
    val sunTrueGeocentricLatitudeSS2 get() = ConvertUtil.toDegreeSS2(sunTrueGeocentricLatitude * 3600)
    
    /**
    * Abration
    */
    val abr get() = Correction.abration(jd, deltaT) / 3600.0

    /**
     * Abration DMS
     */
    val abrDMS get() = ConvertUtil.toDegreeFullRound2(abr)
    
    /**
    * Sun Apparent Geocentric Longitude in degree
    */
    val sunApparentGeocentricLongitude get() = SunPosition.sunApparentGeocentricLongitude(jd, deltaT, UnitType.DEGREES)

    /**
     * Sun Apparent Geocentric Longitude DMS in degree
     */
    val sunApparentGeocentricLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunApparentGeocentricLongitude)
    
    /**
    * Sun True Geocentric Distance AU
    */
    val sunGeocentricDistanceAU get() = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.AU)
    
    /**
    * Sun True Geocentric Distance KM
    */
    val sunGeocentricDistanceKM get() = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.KM)
    
    /**
    * Sun True Geocentric Distance ER
    */
    val sunGeocentricDistanceER get() = EarthPosition.earthRadiusVector(jd, deltaT, DistanceType.ER)
    
    /**
    * Sun Apparent Geocentric Semidiameter in degree, s
    */
    val sunApparentGeoSemidiameter get() = SunPosition.sunApparentGeoSemidiameter(jd, deltaT)

    /**
     * Sun Apparent Geocentric Semidiameter DMS in degree, s
     */
    val sunApparentGeoSemidiameterDMS get() = ConvertUtil.toDegreeFullRound2(sunApparentGeoSemidiameter)
    
    /**
     * Sun Apparent Geocentric Semidiameter MM' SS,ss" in degree, s
     */
    val sunApparentGeoSemidiameterMMSS2 get() = ConvertUtil.toDegreeMMSS2(sunApparentGeoSemidiameter)
    
    /**
    * Sun Apparent Geocentric Right Ascension in degree, a
    */
    val sunApparentGeoRightAscension get() = SunPosition.sunApparentGeoRightAscension(jd, deltaT)

    /**
     * Sun Apparent Geocentric Right Ascension DMS in degree, a
     */
    val sunApparentGeoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(sunApparentGeoRightAscension)

    /**
     * Sun Apparent Geocentric Declination in degree, d
     */
    val sunApparentGeoDeclination get() = SunPosition.sunApparentGeoDeclination(jd, deltaT)

    /**
     * Sun Apparent Geocentric Declination DMS in degree, d
     */
    val sunApparentGeoDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(sunApparentGeoDeclination)

    /**
    * Greenwich Mean Sidereal Time default in degree, GMST, v0
    */
    val greenwichMeanSiderealTime get() = SunPosition.greenwichMeanSiderealTime(jd, UnitType.DEGREES)

    /**
     * Greenwich Mean Sidereal Time DMS default in degree, GMST, v0
     */
    val greenwichMeanSiderealTimeDMS get() = ConvertUtil.toDegreeFullRound2(greenwichMeanSiderealTime)
    
    /**
    * Greenwich Sidereal Time Hour, GMST pukul, v0
    */
    val greenwichMeanSiderealTimeHour get() = (greenwichMeanSiderealTime / 15).mod(24.0)

    /**
    * Greenwich Apparent Sidereal Time, GAST, v
    */
    val greenwichApparentSiderealTime get() = SunPosition.greenwichApparentSiderealTime(jd, deltaT)

    /**
     * Greenwich Apparent Sidereal Time DMS, GAST, v
     */
    val greenwichApparentSiderealTimeDMS get() = ConvertUtil.toDegreeFullRound2(greenwichApparentSiderealTime)
    
    /**
    * Greenwich Apparent Sidereal Time Hour, apparent GAST pukul, v
    */
    val greenwichApparentSiderealTimeHour get() = (greenwichApparentSiderealTime / 15).mod(24.0) // (greenwichMeanSiderealTime + nutationInLongitude * cos(Math.toRadians(trueObliquityOfEcliptic)) / 15).mod(24.0)
    
    /**
    * Greenwich Apparent Sidereal Time Hour HMS, GAST, v
    */
    val greenwichApparentSiderealTimeHourHMS get() = ConvertUtil.toCounterHHMMSS24(greenwichApparentSiderealTimeHour, 3)
    
    /**
    * Local Apparent Sidereal Time, LAST, theta
    */
    val localApparentSiderealTime get() = SunPosition.localApparentSiderealTime(jd, longitude, deltaT, UnitType.DEGREES)

    /**
     * Local Apparent Sidereal Time DMS, LAST, theta
     */
    val localApparentSiderealTimeDMS get() = ConvertUtil.toDegreeFullRound2(localApparentSiderealTime)
    
    /**
    * Local Apparent Sidereal Time Hour, theta, apparent LAST pukul
    */
    val localApparentSiderealTimeHour get() = (localApparentSiderealTime / 15).mod(24.0)

    /**
    * Local Apparent Sidereal Time Hour, theta, apparent LAST pukul HMS
    */
    val localApparentSiderealTimeHourHMS get() = ConvertUtil.toCounterHHMMSS24(localApparentSiderealTimeHour, 3)
    
    /**
    * Sun Geocentric Greenwich Hour Angle, GHA, Ho
    */
    val sunGeoGreenwichHourAngle get() = SunPosition.sunGeoGreenwichHourAngle(jd, deltaT)

    /**
     * Sun Geocentric Greenwich Hour Angle DMS, GHA, Ho
     */
    val sunGeoGreenwichHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(sunGeoGreenwichHourAngle)
    
    /**
    * Sun Geocentric Greenwich Hour Angle Hour HMS, GHA, Ho
    */
    val sunGeoGreenwichHourAngleHMS get() = ConvertUtil.toCounterHHMMSS24((sunGeoGreenwichHourAngle / 15.0).mod(24.0), 3)
    
    /**
    * Sun Geocentric Local Hour Angle, LHA, H
    */
    val sunGeoLocalHourAngle get() = SunPosition.sunGeoLocalHourAngle(jd, longitude, deltaT, UnitType.DEGREES)

    /**
     * Sun Geocentric Local Hour Angle DMS, LHA, H
     */
    val sunGeoLocalHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(sunGeoLocalHourAngle)
    
    /**
    * Sun Geocentric Local Hour Angle Hour HMS, LHA, H
    */
    val sunGeoLocalHourAngleHMS get() = ConvertUtil.toCounterHHMMSS24((sunGeoLocalHourAngle / 15.0).mod(24.0), 3)
    
    /**
    * Sun Geocentric Azimuth, A
    */
    val sunGeoAzimuth get() = SunPosition.sunGeoAzimuth(jd, longitude, latitude, deltaT)

    /**
     * Sun Geocentric Azimuth DMS, A
     */
    val sunGeoAzimuthDMS get() = ConvertUtil.toDegreeFullRound2(sunGeoAzimuth)
    
    /**
    * Sun Geocentric Altitude, h
    */
    val sunGeoAltitude get() = SunPosition.sunGeoAltitude(jd, longitude, latitude, deltaT)

    /**
     * Sun Geocentric Altitude DMS, h
     */
    val sunGeoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunGeoAltitude)
    
    // Sun Topocentric Coordinate 
    
    /**
    * Sun Equatorial Horizontal Parallax, phi
    */
    val sunEquatorialHorizontalParallax get() = SunPosition.sunEquatorialHorizontalParallax(jd, deltaT)

    /**
     * Sun Equatorial Horizontal Parallax DMS, phi
     */
    val sunEquatorialHorizontalParallaxDMS get() = ConvertUtil.toDegreeFullRound2(sunEquatorialHorizontalParallax)
    
    /**
    * term u in radian
    */
    val termU get() = Correction.termU(latitude)
    
    /**
    * term x in radian
    */
    val termX get() = Correction.termX(latitude, elevation)
    
    /**
    * term y in radian
    */
    val termY get() = Correction.termY(latitude, elevation)
    
    /**
    * Sun term n in radian
    */
    val sunTermN get() = SunPosition.sunTermN(jd, longitude, latitude, elevation, deltaT)
    
    /**
    * Parallax in the Sun Right Ascension, deltaAlpha
    */
    val parallaxInTheSunRightAscension get() = SunPosition.parallaxInTheSunRightAscension(jd, longitude, latitude, elevation, deltaT, UnitType.DEGREES)

    /**
     * Parallax in the Sun Right Ascension DMS, deltaAlpha
     */
    val parallaxInTheSunRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(parallaxInTheSunRightAscension)

    /**
    * Parallax in the Sun Altitude
    */
    val parallaxInTheSunAltitude get() = SunPosition.parallaxInTheSunAltitude(jd, longitude, latitude, elevation, deltaT, UnitType.DEGREES)

    /**
     * Parallax in the Sun Altitude DMS
     */
    val parallaxInTheSunAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(parallaxInTheSunAltitude)

    /**
    * Atmospheric Refraction from Airless Altitude
    */
    val atmosphericRefractionFromAirlessAltitude get() = Correction.atmosphericRefractionFromAirlessAltitude(sunGeoAltitude, temperature, pressure)

    /**
     * Atmospheric Refraction from Airless Altitude DMS
     */
    val atmosphericRefractionFromAirlessAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(atmosphericRefractionFromAirlessAltitude)

    /**
    * Sun Topocentric Longitude, lambda apostrophe
    */
    val sunTopoLongitude get() = SunPosition.sunTopoLongitude(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Longitude DMS, lambda apostrophe
     */
    val sunTopoLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoLongitude)

    /**
    * Sun Topocentric Latitude, beta apostrophe
    */
    val sunTopoLatitude get() = SunPosition.sunTopoLatitude(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Latitude DMS, beta apostrophe
     */
    val sunTopoLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoLatitude)

    /**
    * Sun Topocentric Right Ascension, alpha apostrophe
    */
    val sunTopoRightAscension get() = SunPosition.sunTopoRightAscension(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Right Ascension DMS, alpha apostrophe
     */
    val sunTopoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoRightAscension)
    
    /**
    * Sun Topocentric Right Ascension Hour HMS, a`
    */
    val sunTopoRightAscensionHMS get() = ConvertUtil.toCounterHHMMSS24((sunTopoRightAscension / 15.0).mod(24.0), 3)
    

    /**
    * Sun Topocentric Declination, delta apostrophe
    */
    val sunTopoDeclination get() = SunPosition.sunTopoDeclination(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Declination DMS, delta apostrophe
     */
    val sunTopoDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoDeclination)
    
    /**
    * Sun Topocentric Greenwich Hour Angle, GHA'
    */
    val sunTopoGreenwichHourAngle get() = (greenwichApparentSiderealTime - sunTopoRightAscension).mod(360.0)
    
    /**
    * Sun Topocentric Greenwich Hour Angle DMS, GHA'
    */
    val sunTopoGreenwichHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoGreenwichHourAngle)
    
    /**
    * Sun Topocentric Greenwich Hour Angle Hour HMS, GHA'
    */
    val sunTopoGreenwichHourAngleHMS get() = ConvertUtil.toCounterHHMMSS24((sunTopoGreenwichHourAngle / 15.0).mod(24.0), 3)
    

    /**
    * Sun Topocentric Local Hour Angle default in degree, H apostrophe
    */
    val sunTopoLocalHourAngle get() = SunPosition.sunTopoLocalHourAngle(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Local Hour Angle DMS default in degree, H apostrophe
     */
    val sunTopoLocalHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoLocalHourAngle)
    
    /**
    * Sun Topocentric Local Hour Angle Hour HMS, H`
    */
    val sunTopoLocalHourAngleHMS get() = ConvertUtil.toCounterHHMMSS24((sunTopoLocalHourAngle / 15.0).mod(24.0), 3)
    

    /**
    * Sun Topocentric Azimuth, A apostrophe 
    */
    val sunTopoAzimuth get() = SunPosition.sunTopoAzimuth(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Azimuth DMS, A apostrophe
     */
    val sunTopoAzimuthDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoAzimuth)

    /**
    * Sun Airless Topocentric Altitude, h'
    */
    val sunAirlessTopoAltitude get() = SunPosition.sunTopoAltitude(jd, longitude, latitude, elevation, deltaT, SunAltType.AIRLESS, UnitType.DEGREES, temperature, pressure)

    /**
     * Sun Airless Topocentric Altitude DMS, h'
     */
    val sunAirlessTopoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunAirlessTopoAltitude)

    /**
    * Sun Apparent Topocentric Altitude, ha'
    */
    val sunApparentTopoAltitude get() = SunPosition.sunTopoAltitude(jd, longitude, latitude, elevation, deltaT, SunAltType.APPARENT, UnitType.DEGREES, temperature, pressure)

    /**
     * Sun Apparent Topocentric Altitude DMS, ha'
     */
    val sunApparentTopoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunApparentTopoAltitude)

    /**
    * Sun Observer Topocentric Altitude, ho'
    */
    val sunObserverTopoAltitude get() = SunPosition.sunTopoAltitude(jd, longitude, latitude, elevation, deltaT, SunAltType.OBSERVER, UnitType.DEGREES, temperature, pressure)

    /**
     * Sun Observer Topocentric Altitude DMS, ho'
     */
    val sunObserverTopoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(sunObserverTopoAltitude)

    /**
    * Sun Topocentric Semidiameter, s apostrophe
    */
    val sunTopoSemidiameter get() = SunPosition.sunTopoSemidiameter(jd, longitude, latitude, elevation, deltaT)

    /**
     * Sun Topocentric Semidiameter DMS, s apostrophe
     */
    val sunTopoSemidiameterDMS get() = ConvertUtil.toDegreeFullRound2(sunTopoSemidiameter)
    
    /**
     * Sun Topocentric Semidiameter MM' SS,ss", s apostrophe
     */
    val sunTopoSemidiameterMMSS2 get() = ConvertUtil.toDegreeMMSS2(sunTopoSemidiameter)

    /**
    * Equation of Time, e
    */
    val equationOfTime get() = SunPosition.equationOfTime(jd, deltaT)

    /**
     * Equation of Time MM m SS,ss s, e
     */
    val equationOfTimeHMS get() = ConvertUtil.toCounterMMSS2(equationOfTime)
    
}
