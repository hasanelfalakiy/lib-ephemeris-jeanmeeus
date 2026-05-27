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
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DeltaTMode
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.DeltaT
import com.andihasan7.lib.ephemeris.jeanmeeus.timeutil.TimeUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.DistanceType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.PositionType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.MoonAltType
import com.andihasan7.lib.ephemeris.jeanmeeus.enum.UnitType
import com.andihasan7.lib.ephemeris.jeanmeeus.moonposition.MoonPosition
import kotlin.mod

/**
* Moon Datas of Jean Meeus
* Complete Moon Ephemeris Data
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
class MoonJM(
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
    var deltaTMode: DeltaTMode = DeltaTMode.POLYNOMIAL,
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
        DeltaTMode.POLYNOMIAL -> DeltaT.deltaT(jd)
        DeltaTMode.ABAIKAN -> 0.0
        DeltaTMode.CUSTOM -> customDeltaT
    }
    
    // Moon Data
    /**
    * Moon True Geocentric Longitude in degree, lambda apostrophe
    */
    val moonTrueGeocentricLongitude get() = MoonPosition.moonGeocentricLongitude(jd, deltaT, PositionType.TRUE, UnitType.DEGREES)

    /**
     * Moon True Geocentric Longitude DMS in degree, lambda apostrophe
     */
    val moonTrueGeocentricLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonTrueGeocentricLongitude)

    /**
    * Moon Apparent Geocentric Longitude in degree, lambda
    */
    val moonApparentGeocentricLongitude get() = MoonPosition.moonGeocentricLongitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)

    /**
     * Moon Apparent Geocentric Longitude DMS in degree, lambda
     */
    val moonApparentGeocentricLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonApparentGeocentricLongitude)

    /**
    * Moon True Geocentric Latitude in degree, beta apostrophe
    */
    val moonTrueGeocentricLatitude get() = MoonPosition.moonGeocentricLatitude(jd, deltaT, PositionType.TRUE, UnitType.DEGREES)

    /**
     * Moon True Geocentric Latitude DMS in degree, beta apostrophe
     */
    val moonTrueGeocentricLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonTrueGeocentricLatitude)

    /**
    * Moon Apparent Geocentric Latitude in degree, beta
    */
    val moonApparentGeocentricLatitude get() = MoonPosition.moonGeocentricLatitude(jd, deltaT, PositionType.APPARENT, UnitType.DEGREES)

    /**
     * Moon Apparent Geocentric Latitude DMS in degree, beta
     */
    val moonApparentGeocentricLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonApparentGeocentricLatitude)

    /**
    * Moon True Geocentric Distance KM
    */
    val moonTrueGeocentricDistanceKM get() = MoonPosition.moonGeocentricDistance(jd, deltaT, PositionType.TRUE, DistanceType.KM)
    
    /**
    * Moon True Geocentric Distance AU
    */
    val moonTrueGeocentricDistanceAU get() = MoonPosition.moonGeocentricDistance(jd, deltaT, PositionType.TRUE, DistanceType.AU)
    
    /**
    * Moon True Geocentric Distance ER
    */
    val moonTrueGeocentricDistanceER get() = MoonPosition.moonGeocentricDistance(jd, deltaT, PositionType.TRUE, DistanceType.ER)
    
    /**
    * Moon Apparent Geocentric Distance KM
    */
    val moonAppaGeocentricDistanceKM get() = MoonPosition.moonGeocentricDistance(jd, deltaT, PositionType.APPARENT, DistanceType.KM)
    
    /**
    * Moon Apparent Geocentric Distance AU
    */
    val moonAppaGeocentricDistanceAU get() = MoonPosition.moonGeocentricDistance(jd, deltaT, PositionType.APPARENT, DistanceType.AU)
    
    /**
    * Moon Apparent Geocentric Distance ER
    */
    val moonAppaGeocentricDistanceER get() = MoonPosition.moonGeocentricDistance(jd, deltaT, PositionType.APPARENT, DistanceType.ER)
    
    /**
    * Moon Apparent Geocentric Right Ascension, alpha
    */
    val moonAppaGeocentricRightAscension get() = MoonPosition.moonAppaGeocentricRightAscension(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Apparent Geocentric Right Ascension DMS, alpha
     */
    val moonAppaGeocentricRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(moonAppaGeocentricRightAscension)

    /**
    * Moon Apparent Geocentric Declination, delta
    */
    val moonAppaGeocentricDeclination get() = MoonPosition.moonAppaGeocentricDeclination(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Apparent Geocentric Declination DMS, delta
     */
    val moonAppaGeocentricDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(moonAppaGeocentricDeclination)

    /**
    * Moon Geocentric Greenwich Hour Angle default in degree
    */
    val moonGeoGreenwichHourAngle get() = MoonPosition.moonGeoGreenwichHourAngle(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Geocentric Greenwich Hour Angle DMS default in degree
     */
    val moonGeoGreenwichHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoGreenwichHourAngle)

    /**
    * Moon Geocentric Local Hour Angle default in degree
    */
    val moonGeoLocalHourAngle get() = MoonPosition.moonGeoLocalHourAngle(jd, longitude, deltaT, UnitType.DEGREES)

    /**
     * Moon Geocentric Local Hour Angle DMS default in degree
     */
    val moonGeoLocalHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoLocalHourAngle)

    /**
    * Moon Geocentric Azimuth default in degree, A
    */
    val moonGeoAzimuth get() = MoonPosition.moonGeoAzimuth(jd, longitude, latitude, deltaT, UnitType.DEGREES)

    /**
     * Moon Geocentric Azimuth DMS default in degree, A
     */
    val moonGeoAzimuthDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoAzimuth)

    /**
    * Moon Geocentric Altitude default in degree, h
    */
    val moonGeoAltitude get() = MoonPosition.moonGeoAltitude(jd, longitude, latitude, deltaT, UnitType.DEGREES)

    /**
     * Moon Geocentric Altitude DMS default in degree, h
     */
    val moonGeoAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoAltitude)

    /**
    * Moon Equatorial Horizontal Parallax default in degree, phi
    */
    val moonEquatorialHorizontalParallax get() = MoonPosition.moonEquatorialHorizontalParallax(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Equatorial Horizontal Parallax DMS default in degree, phi
     */
    val moonEquatorialHorizontalParallaxDMS get() = ConvertUtil.toDegreeFullRound2(moonEquatorialHorizontalParallax)

    /**
    * Moon Apparent Geocentric Semidiameter default in degree, s
    */
    val moonGeoSemidiameter get() = MoonPosition.moonGeoSemidiameter(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Apparent Geocentric Semidiameter DMS default in degree, s
     */
    val moonGeoSemidiameterDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoSemidiameter)
    
    /**
     * Moon Apparent Geocentric Semidiameter MM' SS,ss" default in degree, s
     */
    val moonGeoSemidiameterMMSS2 get() = ConvertUtil.toDegreeMMSS2(moonGeoSemidiameter)

    /**
    * Moon-Sun Apparent Geocentric Elongation default in degree, d
    */
    val moonSunGeoElongation get() = MoonPosition.moonSunGeoElongation(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon-Sun Apparent Geocentric Elongation DMS default in degree, d
     */
    val moonSunGeoElongationDMS get() = ConvertUtil.toDegreeFullRound2(moonSunGeoElongation)

    /**
    * Moon Apparent Geocentric Phase Angle default in degree, i
    */
    val moonGeoPhaseAngle get() = MoonPosition.moonGeoPhaseAngle(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Apparent Geocentric Phase Angle default in degree, i
     */
    val moonGeoPhaseAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoPhaseAngle)

    /**
    * Moon Geocentric Disk Illuminated Fraction default in degree, k
    */
    val moonGeoDiskIlluminatedFraction get() = MoonPosition.moonGeoDiskIlluminatedFraction(jd, deltaT, UnitType.DEGREES)
    
    /**
    * Moon Geocentric Disk Illuminated Fraction Percent, k
    */
    val moonGeoDiskIlluminatedFractionPercent get() = moonGeoDiskIlluminatedFraction * 100
    
    /**
    * Moon Geocentric Bright Limb Angle default in degree, x
    */
    val moonGeoBrightLimbAngle get() = MoonPosition.moonGeoBrightLimbAngle(jd, deltaT, UnitType.DEGREES)

    /**
     * Moon Geocentric Bright Limb Angle DMS default in degree, x
     */
    val moonGeoBrightLimbAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonGeoBrightLimbAngle)

    /**
    * Moon term n in radian, n
    */
    val moonTermN get() = MoonPosition.moonTermN(jd, longitude, latitude, elevation, deltaT)
    
    /**
    * Parallax in the Moon Right Ascension default in degree, deltaAlpha
    */
    val parallaxInTheMoonRightAscension get() = MoonPosition.parallaxInTheMoonRightAscension(jd, longitude, latitude, elevation, deltaT, UnitType.DEGREES)

    /**
     * Parallax in the Moon Right Ascension DMS default in degree, deltaAlpha
     */
    val parallaxInTheMoonRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(parallaxInTheMoonRightAscension)

    /**
    * Parallax in the Moon Altitude default in degree, P
    */
    val parallaxInTheMoonAltitude get() = MoonPosition.parallaxInTheMoonAltitude(jd, longitude, latitude, elevation, deltaT)

    /**
     * Parallax in the Moon Altitude DMS default in degree, P
     */
    val parallaxInTheMoonAltitudeDMS get() = ConvertUtil.toDegreeFullRound2(parallaxInTheMoonAltitude)
    
    /**
    * Moon Topocentric Longitude default in degree
    */
    val moonTopoLongitude get() = MoonPosition.moonTopoLongitude(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Longitude DMS default in degree
     */
    val moonTopoLongitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoLongitude)

    /**
    * Moon Topocentric Latitude default in degree
    */
    val moonTopoLatitude get() = MoonPosition.moonTopoLatitude(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Latitude DMS default in degree
     */
    val moonTopoLatitudeDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoLatitude)

    /**
    * Moon Topocentric Right Ascension default in degree, alpha apostrophe
    */
    val moonTopoRightAscension get() = MoonPosition.moonTopoRightAscension(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Right Ascension DMS default in degree, alpha apostrophe
     */
    val moonTopoRightAscensionDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoRightAscension)
    
    /**
    * Moon Topocentric Right Ascension Hour, HMS, a`
    */
    val moonTopoRightAscensionHMS get() = ConvertUtil.toCounterHHMMSS24((moonTopoRightAscension / 15.0).mod(24.0), 3)
    
    

    /**
    * Moon Topocentric Declination default in degree, delta apostrophe
    */
    val moonTopoDeclination get() = MoonPosition.moonTopoDeclination(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Declination DMS default in degree, delta apostrophe
     */
    val moonTopoDeclinationDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoDeclination)

    /**
    * Moon Topocentric Semidiameter, s apostrophe
    */
    val moonTopoSemidiameter get() = MoonPosition.moonTopoSemidiameter(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Semidiameter DMS, s apostrophe
     */
    val moonTopoSemidiameterDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoSemidiameter)
    
    /**
     * Moon Topocentric Semidiameter MM' SS,ss", s apostrophe
     */
    val moonTopoSemidiameterMMSS2 get() = ConvertUtil.toDegreeMMSS2(moonTopoSemidiameter)

    /**
    * Moon Topocentric Greenwich Hour Angle default in degree, Ho', GHA'
    */
    val moonTopoGreenwichHourAngle get() = MoonPosition.moonTopoGreenwichHourAngle(jd, longitude, latitude, deltaT)

    /**
     * Moon Topocentric Greenwich Hour Angle DMS default in degree, Ho', GHA
     */
    val moonTopoGreenwichHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoGreenwichHourAngle)
    
    /**
     * Moon Topocentric Greenwich Hour Angle Hour HMS, Ho` GHA`
     */
    val moonTopoGreenwichHourAngleHMS get() = ConvertUtil.toCounterHHMMSS24((moonTopoGreenwichHourAngle / 15.0).mod(24.0), 3)
    

    /**
    * Moon Topocentric Local Hour Angle default in degree, LHA', H'
    */
    val moonTopoLocalHourAngle get() = MoonPosition.moonTopoLocalHourAngle(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Local Hour Angle DMS default in degree, LHA', H'
     */
    val moonTopoLocalHourAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoLocalHourAngle)
    
    /**
    * Moon Topocentric Local Hour Angle Hour HMS, LHA', H`
    */
    val moonTopoLocalHourAngleHMS get() = ConvertUtil.toCounterHHMMSS24((moonTopoLocalHourAngle / 15.0).mod(24.0), 3)
    

    /**
    * Moon Topocentric Azimuth default in degree, A apostrophe
    */
    val moonTopoAzimuth get() = MoonPosition.moonTopoAzimuth(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Azimuth DMS default in degree, A apostrophe
     */
    val moonTopoAzimuthDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoAzimuth)

    /**
    * Moon Airless Topocentric Altitude Center in degree
    */
    val moonAirlessTopoAltitudeCenterLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.AIRLESS_CENTER, temperature, pressure)

    /**
     * Moon Airless Topocentric Altitude Center DMS in degree
     */
    val moonAirlessTopoAltitudeCenterLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAirlessTopoAltitudeCenterLimb)

    /**
    * Moon Apparent Topocentric Altitude Center in degree
    */
    val moonApparentTopoAltitudeCenterLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.APPARENT_CENTER, temperature, pressure)

    /**
     * Moon Apparent Topocentric Altitude Center DMS in degree
     */
    val moonApparentTopoAltitudeCenterLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonApparentTopoAltitudeCenterLimb)

    /**
    * Moon Observed Topocentric Altitude Center in degree
    */
    val moonObservedTopoAltitudeCenterLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_CENTER, temperature, pressure)

    /**
     * Moon Observed Topocentric Altitude Center DMS in degree
     */
    val moonObservedTopoAltitudeCenterLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonObservedTopoAltitudeCenterLimb)

    // Upper
    /**
    * Moon Airless Topocentric Altitude Upper in degree
    */
    val moonAirlessTopoAltitudeUpperLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.AIRLESS_UPPER, temperature, pressure)

    /**
     * Moon Airless Topocentric Altitude Upper DMS in degree
     */
    val moonAirlessTopoAltitudeUpperLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAirlessTopoAltitudeUpperLimb)

    /**
    * Moon Apparent Topocentric Altitude Upper in degree
    */
    val moonApparentTopoAltitudeUpperLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.APPARENT_UPPER, temperature, pressure)

    /**
     * Moon Apparent Topocentric Altitude Upper DMS in degree
     */
    val moonApparentTopoAltitudeUpperLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonApparentTopoAltitudeUpperLimb)

    /**
    * Moon Observed Topocentric Altitude Upper in degree
    */
    val moonObservedTopoAltitudeUpperLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_UPPER, temperature, pressure)

    /**
     * Moon Observed Topocentric Altitude Upper DMS in degree
     */
    val moonObservedTopoAltitudeUpperLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonObservedTopoAltitudeUpperLimb)

    // lower
    /**
    * Moon Airless Topocentric Altitude Lower in degree
    */
    val moonAirlessTopoAltitudeLowerLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.AIRLESS_LOWER, temperature, pressure)

    /**
     * Moon Airless Topocentric Altitude Lower DMS in degree
     */
    val moonAirlessTopoAltitudeLowerLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonAirlessTopoAltitudeLowerLimb)

    /**
    * Moon Apparent Topocentric Altitude Lower in degree
    */
    val moonApparentTopoAltitudeLowerLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.APPARENT_LOWER, temperature, pressure)

    /**
     * Moon Apparent Topocentric Altitude Lower DMS in degree
     */
    val moonApparentTopoAltitudeLowerLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonApparentTopoAltitudeLowerLimb)

    /**
    * Moon Observed Topocentric Altitude Lower in degree
    */
    val moonObservedTopoAltitudeLowerLimb get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.OBSERVED_LOWER, temperature, pressure)

    /**
     * Moon Observed Topocentric Altitude Lower DMS in degree
     */
    val moonObservedTopoAltitudeLowerLimbDMS get() = ConvertUtil.toDegreeFullRound2(moonObservedTopoAltitudeLowerLimb)

    /**
    * Moon Atmospheric Refraction Topocentric Altitude Center in degree
    */
    val moonAtmosphericRefTopoAltitudeCenter get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.REFRACTION_CENTER, temperature, pressure)

    /**
     * Moon Atmospheric Refraction Topocentric Altitude Center DMS in degree
     */
    val moonAtmosphericRefTopoAltitudeCenterDMS get() = ConvertUtil.toDegreeFullRound2(moonAtmosphericRefTopoAltitudeCenter)

    /**
    * Moon Atmospheric Refraction Topocentric Altitude Upper in degree
    */
    val moonAtmosphericRefTopoAltitudeUpper get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.REFRACTION_UPPER, temperature, pressure)

    /**
     * Moon Atmospheric Refraction Topocentric Altitude Upper DMS in degree
     */
    val moonAtmosphericRefTopoAltitudeUpperDMS get() = ConvertUtil.toDegreeFullRound2(moonAtmosphericRefTopoAltitudeUpper)

    /**
    * Moon Atmospheric Refraction Topocentric Altitude Lower in degree
    */
    val moonAtmosphericRefTopoAltitudeLower get() = MoonPosition.moonTopoAltitude(jd, longitude, latitude, elevation, deltaT, MoonAltType.REFRACTION_LOWER, temperature, pressure)

    /**
     * Moon Atmospheric Refraction Topocentric Altitude Lower DMS in degree
     */
    val moonAtmosphericRefTopoAltitudeLowerDMS get() = ConvertUtil.toDegreeFullRound2(moonAtmosphericRefTopoAltitudeLower)

    /**
    * Moon Sun Topocentric Elongation default in degree, d apostrophe 
    */
    val moonSunTopoElongation get() = MoonPosition.moonSunTopoElongation(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Sun Topocentric Elongation DMS default in degree, d apostrophe
     */
    val moonSunTopoElongationDMS get() = ConvertUtil.toDegreeFullRound2(moonSunTopoElongation)

    /**
    * Moon Topocentric Phase Angle in degree, i apostrophe
    */
    val moonTopoPhaseAngle get() = MoonPosition.moonTopoPhaseAngle(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Phase Angle DMS in degree, i apostrophe
     */
    val moonTopoPhaseAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoPhaseAngle)

    /**
    * Moon Topocentric Disk Illuminated Fraction
    */
    val moonTopoDiskIlluminatedFraction get() = MoonPosition.moonTopoDiskIlluminatedFraction(jd, longitude, latitude, elevation, deltaT)

    /**
    * Moon Topocentric Disk Illuminated Fraction Percent 
    */
    val moonTopoDiskIlluminatedFractionPercent get() = moonTopoDiskIlluminatedFraction * 100
    
    /**
    * Moon Topocentric Bright Limb Angle
    */
    val moonTopoBrightLimbAngle get() = MoonPosition.moonTopoBrightLimbAngle(jd, longitude, latitude, elevation, deltaT)

    /**
     * Moon Topocentric Bright Limb Angle DMS
     */
    val moonTopoBrightLimbAngleDMS get() = ConvertUtil.toDegreeFullRound2(moonTopoBrightLimbAngle)
    
}
