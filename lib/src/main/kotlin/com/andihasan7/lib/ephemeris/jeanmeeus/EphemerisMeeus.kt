

package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.atan
import kotlin.math.asin
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan
import kotlin.math.pow
import kotlin.math.PI
import com.andihasan7.lib.ephemeris.jeanmeeus.util.masehiToJD
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toTimeFullRound2
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toRange360

/**
* 
* data ephemeris matahari bulan
*
* ```
*    class EphemerisMeeus(
*        date: Int = 1, // tanggal masehi
*        month: Int = 1, // bulan masehi
*        year: Int = 2000, // tahun masehi
*        latitude: Double = 0.0, // lintang tempat
*        longitude: Double = 0.0, // bujur tempat
*        elevation: Double = 0.0, // tinggi tempat
*        timeZone: Double = 0.0, // zona waktu
*        hourDouble: Double = 0.0, // jam double/desimal
*        temperature: Double = 10, // suhu lokal rata-rata tahunan (dalam °C)
*        pressure: Double = 1010, // tekanan udara lokal rata-rata tahunan (dalam millibars)
*        checkDeltaT: Boolean = true // pilihan pakai deltaT atau tidak, true = deltaT, false = abaikan deltaT
*    )
* ```
*/
class EphemerisMeeus(
    date: Int = 1, // tanggal masehi
    month: Int = 1, // bulan masehi
    year: Int = 2000, // tahun masehi
    latitude: Double = 0.0, // lintang tempat 
    longitude: Double = 0.0, // bujur tempat
    elevation: Double = 0.0, // tinggi tempat
    timeZone: Double = 0.0, // zona waktu
    hourDouble: Double = 0.0, // jam desimal
    temperature: Double = 10.0, // suhu lokal rata-rata tahunan (dalam °C)
    pressure: Double = 1010.0, // tekanan udara lokal rata-rata tahunan (dalam millibars)
    checkDeltaT: Boolean = true // pilihan pakai deltaT atau tidak
) {
    
    /**
    * julian day (JD)
    */
    val jd = masehiToJD(
        date,
        month,
        year,
        hourDouble,
        timeZone
    )
    
    /**
    * deltaT
    */
    val deltaT: Double = if (checkDeltaT == true) {
        deltaT(date, month, year)
    } else {
        0.0
    }
    
    /**
    * julian day ephemeris (JDE)
    */
    val jde = jd + deltaT / 86400.0
    
    /**
    * JC
    */
    val jc = (jd - 2451545.0).toDouble() / 36525.0
    
    /**
    * nilai T / JCE julian ephemeris century
    */
    val nilaiT = (jde - 2451545.0).toDouble() / 36525.0
    
    /**
    * nilai tau / JME julian ephemeris millenium
    */
    val tau = nilaiT / 10.0
    
    /**
    * dip
    */
    val dip = 1.75 / 60 * sqrt(elevation)
    
    /**
    * deltaPsi arcsecond (nutation in longitude)
    */
    val deltaPsiArcsec = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
    
    /**
    * deltaPsi arcsecond DMS (nutation in longitude)
    */
    val deltaPsiArcsecDMS = toDegreeFullRound2(deltaPsiArcsec)
    
    /**
    * deltaPsi degrees
    */
    val deltaPsiDegrees = Nutasi.deltaPsiDanEpsilon(nilaiT)[2]
    /**
    * deltaPsi degrees DMS
    */
    val deltaPsiDegreesDMS = toDegreeFullRound2(deltaPsiDegrees)
    
    // u
    val u = Nutasi.deltaPsiDanEpsilon(nilaiT)[3]
    
    /**
    * epsilonZero (mean obliquity of ecliptic)
    */
    val meanObliquityOfEcliptic = Nutasi.deltaPsiDanEpsilon(nilaiT)[4]
    
    /**
    * mean obliquity of ecliptic DMS
    */
    val meanObliquityOfEclipticDMS = toDegreeFullRound2(meanObliquityOfEcliptic)
    
     /**
    * deltaEpsilon arcsecond (nutation of obliquity)
    */
    val deltaEpsArcsec = Nutasi.deltaPsiDanEpsilon(nilaiT)[5]
    
    /**
    * deltaEpsilon arcsecond DMS (nutation of obliquity)
    */
    val deltaEpsArcsecDMS = toDegreeFullRound2(deltaEpsArcsec)
    
    /**
    * deltaEpsilon (nutation of obliquity)
    */
    val deltaEps = Nutasi.deltaPsiDanEpsilon(nilaiT)[6]
    
    /**
    * deltaEpsilon DMS (nutation of obliquity)
    */
    val deltaEpsDMS = toDegreeFullRound2(deltaEps)
    
    /**
    * epsilon (true obliquity of ecliptic)
    */
    val trueObliquityOfEcliptic = Nutasi.deltaPsiDanEpsilon(nilaiT)[7]
    
    /**
    * epsilon DMS (true obliquity of ecliptic)
    */
    val trueObliquityOfEclipticDMS = toDegreeFullRound2(trueObliquityOfEcliptic)
    
    
    // sun ecliptical coordinate
    
	/**
	 * earth heliocentric longitude radians
	 */
	val earthHeliocentricLongitudeRadians = TabelMatahari.bujurEkliptik(tau, nilaiT)[0]
	
	/**
	 * earth heliocentric longitude degrees 
	 */
	val earthHeliocentricLongitudeDegrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[1]
    
    /**
	 * earth heliocentric longitude degrees DMS
	 */
    val earthHeliocentricLongitudeDegreesDMS = toDegreeFullRound2(earthHeliocentricLongitudeDegrees)
	
	/**
	 * sun geometric longitude degrees
	 */
	val sunGeometricLongitudeDegrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[2]
    
    /**
	 * sun geometric longitude degrees DMS
	 */
	val sunGeometricLongitudeDegreesDMS = toDegreeFullRound2(sunGeometricLongitudeDegrees)
    
	/**
	 * sun geometric longitude lamdaM`
	 */
	val sunGeometricLonLamdaM = TabelMatahari.bujurEkliptik(tau, nilaiT)[3]
    
    /**
	 * sun geometric longitude lamdaM` DMS
	 */
	val sunGeometricLonLamdaMDMS = toDegreeFullRound2(sunGeometricLonLamdaM)
    
	/**
	 * sun geometric longitude lamdaM` radians
	 */
	val sunGeometricLonLamdaMRadians = TabelMatahari.bujurEkliptik(tau, nilaiT)[6]
    
    /**
	 * sun geometric longitude lamdaM` radians DMS
	 */
	val sunGeometricLonLamdaMRadiansDMS = toDegreeFullRound2(sunGeometricLonLamdaMRadians)
    
    /**
	 * sun geometric longitude lamdaM` radians
	 */
	val sunGeometricLonLamdaMDegrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[3]
    
    /**
	 * sun geometric longitude lamdaM` radians DMS
	 */
	val sunGeometricLonLamdaMDegreesDMS = toDegreeFullRound2(sunGeometricLonLamdaMDegrees)
    
    
	/**
	 * sun true geocentric equinox J2000 Degrees, tetha
     * 
	 */
	val sunTrueGeocentricLonJ2000Degrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[5]
    
    /**
	 * sun true geocentric equinox J2000 Degrees DMS, tetha
	 */
    val sunTrueGeocentricLonJ2000DegreesDMS = toDegreeFullRound2(sunTrueGeocentricLonJ2000Degrees)
    
    /**
	 * earth heliocentric latitude radians, B
	 */
	val earthHeliocentricLatitudeRadians = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMDegrees)[0]
    
    /**
	 * earth heliocentric latitude radians DMS, B
	 */
    val earthHeliocentricLatitudeRadiansDMS = toDegreeFullRound2(earthHeliocentricLatitudeRadians)
	
	/**
	 * koreksi delta theta untuk equinox dynamic FK5 system
	 */
	val deltaThetaDynamicFK5 = (-0.09033 + 0.03916 * (cos(sunGeometricLonLamdaM) + sin(sunGeometricLonLamdaM)) * tan(earthHeliocentricLatitudeRadians))
	
	/**
	 * sun true geocentric FK5 Degrees
	 */
	val sunTrueGeocentricLonFK5Degrees = (sunGeometricLongitudeDegrees + deltaThetaDynamicFK5).mod(360.0)
    
    /**
	 * sun true geocentric FK5 Degrees DMS
	 */
    val sunTrueGeocentricLonFK5DegreesDMS = toDegreeFullRound2(sunTrueGeocentricLonFK5Degrees)
	
	/**
	 * delta beta, masih satuan arcsecond bagi dengan 3600 untuk ke satuan derajat 
	 */
	val deltaBeta = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMDegrees)[1]
	
	/**
	 * sun true geocentric latitude degrees
	 */
	val sunTrueGeocentricLatitudeDegrees = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMDegrees)[2] / 3600
    
    /**
	 * sun true geocentric latitude degrees DMS
	 */
	val sunTrueGeocentricLatitudeDegreesDMS = toDegreeFullRound2(sunTrueGeocentricLatitudeDegrees)
    
	/**
	 * sun true geocentric latitude radians
	 */
	val sunTrueGeocentricLatitudeRadians = (sunTrueGeocentricLatitudeDegrees * 180) / PI
    
    /**
	 * sun true geocentric latitude radians DMS
	 */
	val sunTrueGeocentricLatitudeRadiansDMS = toDegreeFullRound2(sunTrueGeocentricLatitudeRadians)
	
	/**
	 * beta zero, masih satuan arcsecond bagi dengan 3600 untuk ke satuan derajat 
	 */
	val betaZero = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMDegrees)[3]
    
    /**
    * sun true geocentric distance AU, vector radius jarak bumi matahari
    */
    val sunTrueGeocentricDistanceAU = TabelMatahari.jarakBumiMat(tau)
	
	/**
	 * sun true geocentric distance KM
	 */
	val sunTrueGeocentricDistanceKM = sunTrueGeocentricDistanceAU * 149597870.7
	
	/**
	 * sun true geocentric distance ER
	 */
	val sunTrueGeocentricDistanceER = sunTrueGeocentricDistanceAU * 149597870.7 / 6371
    
    /**
    * abrasi arcsecond, abr
    */
    val abrasiArcsec = Abrasi.abrasi(tau, sunTrueGeocentricDistanceAU)
    
    /**
    * abrasi degrees, abr deg
    */
    val abrasiDegrees = abrasiArcsec / 3600
    // val abrasiDegrees = -20.4898 / (3600 * sunTrueGeocentricDistanceAU)
    
    
    /**
    * sun apparent geocentric longitude deg, lambda
    */
    val sunApparentGeoLongitude = (sunTrueGeocentricLonJ2000Degrees + deltaPsiDegrees + abrasiDegrees).mod(360.0)
    
    /**
    * sun apparent geocentric longitude deg DMS, lambda
    */
    val sunApparentGeoLongitudeDMS = toDegreeFullRound2(sunApparentGeoLongitude)
    
    /**
    * sun apparent geocentric latitude deg, beta
    */
    val sunApparentGeoLatitude = sunTrueGeocentricLatitudeDegrees
    
    /**
    * sun apparent geocentric latitude deg DMS, beta
    */
    val sunApparentGeoLatitudeDMS = toDegreeFullRound2(sunApparentGeoLatitude)
    
    /**
    * sun apparent geocentric semidiameter, s
    */
    val sunApparentGeocentricSemidiameter = 0.266563888889 / sunTrueGeocentricDistanceAU
    
    /**
    * sun apparent geocentric semidiameter DMS, s
    */
    val sunApparentGeocentricSemidiameterDMS = toDegreeFullRound2(sunApparentGeocentricSemidiameter)
    
    /**
    * sun apparent geocentric right ascension, a
    */
    val sunApparentGeoRightAscension = (Math.toDegrees(atan2((sin(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(trueObliquityOfEcliptic)) - tan(Math.toRadians(sunApparentGeoLatitude)) * sin(Math.toRadians(trueObliquityOfEcliptic))), cos(Math.toRadians(sunApparentGeoLongitude))))).mod(360.0)
    
    /**
    * sun apparent geocentric right ascension DMS, a
    */
    val sunApparentGeoRightAscensionDMS = toDegreeFullRound2(sunApparentGeoRightAscension)
    
    /**
    * sun apparent geocentric right ascension HMS, a
    */
    val sunApparentGeoRightAscensionHMS = toTimeFullRound2(sunApparentGeoRightAscension / 15.0)
    
    
    // sun equatorial coor
    
    /**
    * sun apparent geocentric declination, d
    */
    val sunApparentGeoDeclination = (Math.toDegrees(asin(sin(Math.toRadians(sunApparentGeoLatitude)) * cos(Math.toRadians(trueObliquityOfEcliptic)) + cos(Math.toRadians(sunApparentGeoLatitude)) * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(sunApparentGeoLongitude))))) //.mod(360.0)
    
    /**
    * sun apparent geocentric declination DMS, d
    */
    val sunApparentGeoDeclinationDMS = toDegreeFullRound2(sunApparentGeoDeclination)
    
    
    // sun horizontal coor
    
    /**
    * greenwich mean sideral time, vo, gmst
    */
    val greenwichMeanSideralTime = (280.46061837 + 360.98564736629 * (jd - 2451545) + 0.000387933 * jc.pow(2) - (jc.pow(3) / 38710000)).mod(360.0)
    
    /**
    * greenwich apparent sideral time, v, gast
    */
    val greenwichApparentSideralTime = (greenwichMeanSideralTime + deltaPsiDegrees * cos(Math.toRadians(trueObliquityOfEcliptic))).mod(360.0)
    
    /**
    * local apparent sideral time degrees, theta
    */
    val localApparentSideralTimeDegrees = (greenwichApparentSideralTime + longitude).mod(360.0)
    
    
    /**
    * greenwich sideral time hour, gst pukul
    */
    val greenwichSideralTimeHour = greenwichMeanSideralTime / 15
    
    /**
    * greenwich apparent sideral time, apparent gst pukul
    */
    val greenwichApparentSideralTimeHour = greenwichSideralTimeHour + deltaPsiDegrees * cos(Math.toRadians(trueObliquityOfEcliptic)) / 15
    
    /**
    * local apparent sideral time jam, theta, apparent lst pukul
    */
    val localApparentSideralTimeHour = (greenwichApparentSideralTimeHour + longitude / 15).mod(24.0)
    
    /**
    * sun geocentric greenwich hour angle, Ho, GHA
    */
    val sunGeocentricGreenwichHourAngle = (greenwichApparentSideralTime - sunApparentGeoRightAscension).mod(360.0)
    
    /**
    * sun geocentric greenwich hour angle DMS, Ho, GHA
    */
    val sunGeocentricGreenwichHourAngleDMS = toDegreeFullRound2(sunGeocentricGreenwichHourAngle)
    
    /**
    * sun geocentric local hour angle, H, LHA
    */
    val sunGeocentricLocalHourAngle = (sunGeocentricGreenwichHourAngle + longitude).mod(360.0)
    
    /**
    * sun geocentric local hour angle DMS, H, LHA
    */
    val sunGeocentricLocalHourAngleDMS = toDegreeFullRound2(sunGeocentricLocalHourAngle)
    
    /**
    * sun geocentric azimuth, A
    */
    val sunGeocentricAzimuth = (Math.toDegrees(atan2(sin(Math.toRadians(sunGeocentricLocalHourAngle)), cos(Math.toRadians(sunGeocentricLocalHourAngle)) * sin(Math.toRadians(latitude)) - tan(Math.toRadians(sunApparentGeoDeclination)) * cos(Math.toRadians(latitude)))) + 180).mod(360.0)
    
    /**
    * sun geocentric azimuth DMS, A
    */
    val sunGeocentricAzimuthDMS = toDegreeFullRound2(sunGeocentricAzimuth)
    
    /**
    * sun geocentric altitude, h
    */
    val sunGeocentricAltitude = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(sunApparentGeoDeclination)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(sunApparentGeoDeclination)) * cos(Math.toRadians(sunGeocentricLocalHourAngle))))
    
    /**
    * sun geocentric altitude DMS, h
    */
    val sunGeocentricAltitudeDMS = toDegreeFullRound2(sunGeocentricAltitude)
    
    
    // sun topocentric ecliptic coor
    
    /**
    * equator horizontal parallax of the sun, phi
    */
    val eqHorizontalParallaxSun = 8.794 / (3600 * sunTrueGeocentricDistanceAU)
    
    /**
    * sudut parallax matahari, mirip eqHorizontalParallaxSun
    */
    val sudutParallaxMatahari = Math.toDegrees(atan(6378.14 / sunTrueGeocentricDistanceKM))
    
    /**
    * jari-jari bumi di equator km, a
    */
    val JARI2BUMIEQUATOR = 6378.14
    
    /**
    * jari-jari bumi polar km, b
    */
    val jari2BumiPolar = JARI2BUMIEQUATOR * (1 - 1 / 298.257)
    
    /**
    * suku u dalam radian
    */
    val suku_u = atan(tan(Math.toRadians(latitude)) * jari2BumiPolar / JARI2BUMIEQUATOR)
    
    /**
    * suku y dalam radian
    */
    val suku_y = (jari2BumiPolar / JARI2BUMIEQUATOR) * sin(suku_u) + (elevation / 6378140) * sin(Math.toRadians(latitude))
    
    /**
    * suku x dalam radian
    */
    val suku_x = cos(suku_u) + (elevation / 6378140) * cos(Math.toRadians(latitude))
    
    /**
    * suku rho dalam radian
    */
    val suku_rho = sqrt(suku_y.pow(2) + suku_x.pow(2))
    
    /*
    /**
    * suku u dalam radian, buku seri 3
    */
    val suku_u = atan(0.99664719 * tan(Math.toRadians(latitude)))
    
    /**
    * suku x dalam radian, buku seri 3
    */
    val suku_x = cos(suku_u) + ((elevation).toDouble() / 6378140) * cos(Math.toRadians(latitude))
    
    /**
    * suku y dalam radian, buku seri 3
    */
    val suku_y = 0.99664719 * sin(suku_u) + ((elevation).toDouble() / 6378140) * sin(Math.toRadians(latitude))
    */
    
    /**
    * suku n dalam radian, buku seri 3
    */
    val suku_n = cos(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(sunApparentGeoLatitude)) - suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * cos(Math.toRadians(localApparentSideralTimeDegrees))
    
    
    /**
    * parallax in the sun right ascension, delta a buku seri 3
    */
    // val parallaxSunRightAscension = Math.toDegrees(atan2((-suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * sin(Math.toRadians(sunGeocentricLocalHourAngle))), (cos(Math.toRadians(sunApparentGeoDeclination)) - suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * cos(Math.toRadians(sunGeocentricLocalHourAngle)))))
    
    
    /**
    * parallax in the sun right ascension, delta a
    */
    val parallaxSunRightAscension = Math.toDegrees(atan2(-1 * suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * sin(Math.toRadians(sunGeocentricLocalHourAngle)), cos(Math.toRadians(sunApparentGeoDeclination)) -  suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * cos(Math.toRadians(sunGeocentricLocalHourAngle)) ))
    
    
    
    /**
    * sun atmospheric refraction, R
    */
    val sunAtmosphericRefraction = (1.02 / tan(Math.toRadians(sunGeocentricAltitude + 10.3 / (sunGeocentricAltitude + 5.11))) * pressure / 1010 * 283.0 / (273.0 + temperature) + 0.0019279204034639303) / 60
    
    /**
    * sun atmospheric refraction DMS, R
    */
    val sunAtmosphericRefractionDMS = toDegreeFullRound2(sunAtmosphericRefraction)
    
    /**
    * parallax in the sun altitude, P
    */
    val parallaxSunAltitude = Math.toDegrees(asin(sqrt(suku_y.pow(2) + suku_x.pow(2)) * sin(Math.toRadians(eqHorizontalParallaxSun)) * cos(Math.toRadians(sunGeocentricAltitude))))
    
    /**
    * parallax in the sun altitude DMS, P
    */
    val parallaxSunAltitudeDMS = toDegreeFullRound2(parallaxSunAltitude)
    
    /**
    * sun topocentric ecliptic longitude, lambda`
    */
    val sunTopocentricEclipLongitude = (Math.toDegrees(atan2(sin(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * sin(Math.toRadians(trueObliquityOfEcliptic)) + suku_x * cos(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(localApparentSideralTimeDegrees))), suku_n))).mod(360.0)
    // val sunTopocentricEclipLongitude = (Math.toDegrees(atan2(sin(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * sin(Math.toRadians(trueObliquityOfEcliptic)) + suku_x * cos(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(sunTrueGeocentricLonJ2000Degrees))), suku_n))).mod(360.0) buku seri 3
    
    /**
    * sun topocentric ecliptic longitude DMS, lambda`
    */
    val sunTopocentricEclipLongitudeDMS = toDegreeFullRound2(sunTopocentricEclipLongitude)
    
    /**
    * sun topocentric ecliptic latitude, beta`
    */
    val sunTopocentricEclipLatitude = Math.toDegrees(atan2(cos(Math.toRadians(sunTopocentricEclipLongitude)) * (sin(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * cos(Math.toRadians(trueObliquityOfEcliptic)) - suku_x * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(localApparentSideralTimeDegrees)))), suku_n))
    // val sunTopocentricEclipLatitude = Math.toDegrees(atan2(cos(Math.toRadians(sunTopocentricEclipLongitude)) * (sin(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * cos(Math.toRadians(trueObliquityOfEcliptic)) - suku_x * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(sunTrueGeocentricLonJ2000Degrees)))), suku_n))
    
    
    /**
    * sun topocentric ecliptic latitude DMS, beta`
    */
    val sunTopocentricEclipLatitudeDMS = toDegreeFullRound2(sunTopocentricEclipLatitude)
    
    
    // sun topocentric equatorial coor
    
    /**
    * sun topocentric right ascension, a`
    */
    val sunTopocentricRightAscension = (sunApparentGeoRightAscension + parallaxSunRightAscension).mod(360.0)
    
    /**
    * sun topocentric right ascension DMS, a`
    */
    val sunTopocentricRightAscensionDMS = toDegreeFullRound2(sunTopocentricRightAscension)
    
    /**
    * sun topocentric right ascension HMS, a`
    */
    val sunTopocentricRightAscensionHMS = toTimeFullRound2(sunTopocentricRightAscension / 15.0)
    
    /**
    * sun topocentric declination, d`
    */
    val sunTopocentricDeclination = Math.toDegrees(atan2((sin(Math.toRadians(sunApparentGeoDeclination)) - suku_y * sin(Math.toRadians(eqHorizontalParallaxSun))) * cos(Math.toRadians(parallaxSunRightAscension)), cos(Math.toRadians(sunApparentGeoDeclination)) - suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * cos(Math.toRadians(sunGeocentricLocalHourAngle))))
    
    /**
    * sun topocentric declination DMS, d`
    */
    val sunTopocentricDeclinationDMS = toDegreeFullRound2(sunTopocentricDeclination)
    
    
    // sun topocentric horizontal coor
    
    /**
    * sun topocentric local hour angle, H`
    */
    val sunTopocentricLocalHourAngle = sunGeocentricLocalHourAngle - parallaxSunRightAscension
    
    /**
    * sun topocentric local hour angle DMS, H`
    */
    val sunTopocentricLocalHourAngleDMS = toDegreeFullRound2(sunTopocentricLocalHourAngle)
    
    /**
    * sun topocentric azimuth, A`
    */
    val sunTopocentricAzimuth = (Math.toDegrees(atan2(sin(Math.toRadians(sunTopocentricLocalHourAngle)), cos(Math.toRadians(sunTopocentricLocalHourAngle)) * sin(Math.toRadians(latitude)) - tan(Math.toRadians(sunTopocentricDeclination)) * cos(Math.toRadians(latitude)))) + 180).mod(360.0)
    
    /**
    * sun topocentric azimuth DMS, A`
    */
    val sunTopocentricAzimuthDMS = toDegreeFullRound2(sunTopocentricAzimuth)
    
    /**
    * sun airless topocentric altitude, h`
    */
    val sunAirlessTopocentricAltitude = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(sunTopocentricDeclination)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(sunTopocentricDeclination)) * cos(Math.toRadians(sunTopocentricLocalHourAngle))))
    
    /**
    * sun airless topocentric altitude DMS, h`
    */
    val sunAirlessTopocentricAltitudeDMS = toDegreeFullRound2(sunAirlessTopocentricAltitude)
    
    /**
    * sun atmospheric refraction, R
    */
    val sunAtmosphericRefractionForSunApparent = (1.02 / tan(Math.toRadians(sunAirlessTopocentricAltitude + 10.3 / (sunAirlessTopocentricAltitude + 5.11))) * pressure / 1010 * 283.0 / (273.0 + temperature) + 0.0019279204034639303) / 60
    
    /**
    * sun apparent topocentric altitude, h`a
    */
    val sunApparentTopocentricAltitude = sunAirlessTopocentricAltitude + sunAtmosphericRefractionForSunApparent
    
    /**
    * sun apparent topocentric altitude DMS, h`a
    */
    val sunApparentTopocentricAltitudeDMS = toDegreeFullRound2(sunApparentTopocentricAltitude)
    
    /**
    * sun observed topocentric altitude, h`o
    */
    val sunObservedAltitude = sunApparentTopocentricAltitude + dip
    
    /**
    * sun apparent topocentric altitude DMS, h`a
    */
    val sunObservedAltitudeDMS = toDegreeFullRound2(sunObservedAltitude)
    
    /**
    * sun topocentric semidiameter, s`
    */
    val sunTopocentricSemidiameter = Math.toDegrees(asin(cos(Math.toRadians(sunTopocentricEclipLongitude)) * cos(Math.toRadians(sunTopocentricEclipLatitude)) * sin(Math.toRadians(sunApparentGeocentricSemidiameter)) / suku_n))
    
    /**
    * sun topocentric semidiameter DMS, s`
    */
    val sunTopocentricSemidiameterDMS = toDegreeFullRound2(sunTopocentricSemidiameter)
    
    // equation of time
    val _lo = (280.4664567 + 360007.6982779 * tau + 0.03032028 * tau.pow(2) + tau.pow(3) / 49931 - tau.pow(4) / 15300 - tau.pow(5) / 2000000).mod(360.0)
    val _e = (_lo - 0.0057183 - sunApparentGeoRightAscension + deltaPsiDegrees * cos(Math.toRadians(trueObliquityOfEcliptic)))
    
    /**
    * equation of time satuan menit, e
    */
    val equationOfTimeMinute = _e * 4
    
    /**
    * equation of time satuan jam, e
    */
    val equationOfTimeHour = when {
    
        abs(equationOfTimeMinute) < 20.0 -> {
            _e / 15
        }
        
        abs(equationOfTimeMinute) >= 20.0 && _e > 0.0 -> {
            _e / 15 - 24
        }
        
        abs(equationOfTimeMinute) >= 20.0 && _e < 0.0 -> {
            _e / 15 + 24
        }
        
        else -> {
            _e / 15
        }
    }
    
    /**
    * equation of time satuan jam HMS, e
    */
    val equationOfTimeHourHMS = toTimeFullRound2(equationOfTimeHour)
    
    
    // data bulan
    
    // moon ecliptic coordinate
    
    /**
    * bujur rata-rata bulan, L`, l1
    */
    val bujurRata2Bulan = (218.3164591 + 481267.88134236 * nilaiT - 0.0013268 * nilaiT.pow(2) + nilaiT.pow(3) / 538841 - nilaiT.pow(4) / 65194000).mod(360.0)
    
    /**
    * bujur rata-rata bulan DMS, L`, l1
    */
    val bujurRata2BulanDMS = toDegreeFullRound2(bujurRata2Bulan)
    
    /**
    * koreksi bujur bulan
    */
    val koreksiBujurBulan = TabelBulan.periodikBujur(nilaiT, bujurRata2Bulan)[6]
    
    /**
    * koreksi bujur bulan DMS
    */
    val koreksiBujurBulanDMS = toDegreeFullRound2(koreksiBujurBulan)
    
    /**
    * moon true geocentric longitude
    */
    val moonTrueGeocentricLongitude = (bujurRata2Bulan + koreksiBujurBulan).mod(360.0)
    
    /**
    * moon true geocentric longitude DMS
    */
    val moonTrueGeocentricLongitudeDMS = toDegreeFullRound2(moonTrueGeocentricLongitude)
    
    /**
    * moon apparent geocentric longitude, lambda
    */
    val moonApparentGeocentricLongitude = (moonTrueGeocentricLongitude + deltaPsiDegrees).mod(360.0)
    
    /**
    * moon apparent geocentric longitude DMS, lambda
    */
    val moonApparentGeocentricLongitudeDMS = toDegreeFullRound2(moonApparentGeocentricLongitude)
    
    /**
    * moon apparent geocentric latitude, beta
    */
    val moonApparentGeocentricLatitude = TabelBulan.periodikBujur(nilaiT, bujurRata2Bulan)[7]
    
    /**
    * moon apparent geocentric latitude DMS, beta
    */
    val moonApparentGeocentricLatitudeDMS = toDegreeFullRound2(moonApparentGeocentricLatitude)
    
    /**
    * koreksi jarak bumi bulan
    */
    val koreksiJarakBumiBulan = TabelBulan.periodikBujur(nilaiT, bujurRata2Bulan)[8]
    
    /**
    * moon true geocentric distance KM
    */
    val moonTrueGeocentricDistanceKM = 385000.56 + koreksiJarakBumiBulan
    
    /**
    * abrasi jarak bumi bulan
    */
    val moonAbr = 0.0708 * cos(Math.toRadians(225 + 477198.9 * nilaiT))
    
    /**
    * moon apparent geocentric distance KM
    */
    val moonApparentGeocentricDistanceKM = moonTrueGeocentricDistanceKM + moonAbr
    
    /**
    * moon apparent geocentric distance AU
    */
    val moonApparentGeocentricDistanceAU = moonApparentGeocentricDistanceKM / 149597870.7
    
    /**
    * moon apparent geocentric distance ER
    */
    val moonApparentGeocentricDistanceER = moonApparentGeocentricDistanceKM / 6371.0
    
    /**
    * moon equatorial horizontal parallax, phi
    */
    val moonEquatorialHorizontalParallax = Math.toDegrees(asin(6378.14 / moonTrueGeocentricDistanceKM))
    
    /**
    * moon equatorial horizontal parallax DMS, phi
    */
    val moonEquatorialHorizontalParallaxDMS = toDegreeFullRound2(moonEquatorialHorizontalParallax)
    
    
    /**
    * moon apparent geocentric semidiameter, s
    */
    val moonApparentGeocentricSemidiameter = Math.toDegrees(asin(0.272481 * sin(Math.toRadians(moonEquatorialHorizontalParallax))))
    //val moonApparentGeocentricSemidiameter = 358473400.0 / moonApparentGeocentricDistanceKM
    
    /**
    * moon apparent geocentric semidiameter DMS, s
    */
    val moonApparentGeocentricSemidiameterDMS = toDegreeFullRound2(moonApparentGeocentricSemidiameter)
    
    /**
    * moon apparent geocentric right ascension, a
    */
    val moonApparentGeocentricRightAscension = (Math.toDegrees(atan2(sin(Math.toRadians(moonApparentGeocentricLongitude)) * cos(Math.toRadians(trueObliquityOfEcliptic)) - tan(Math.toRadians(moonApparentGeocentricLatitude)) * sin(Math.toRadians(trueObliquityOfEcliptic)), cos(Math.toRadians(moonApparentGeocentricLongitude))))).mod(360.0)
    
    /**
    * moon apparent geocentric right ascension DMS, a
    */
    val moonApparentGeocentricRightAscensionDMS = toDegreeFullRound2(moonApparentGeocentricRightAscension)
    
    /**
    * moon apparent geocentric right ascension HMS, a
    */
    val moonApparentGeocentricRightAscensionHMS = toTimeFullRound2(moonApparentGeocentricRightAscension / 15.0)
    
    /**
    * moon apparent geocentric declination, d
    */
    val moonApparentGeoDeclination = Math.toDegrees(asin(sin(Math.toRadians(moonApparentGeocentricLatitude)) * cos(Math.toRadians(trueObliquityOfEcliptic)) + cos(Math.toRadians(moonApparentGeocentricLatitude)) * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(moonApparentGeocentricLongitude))))
    
    /**
    * moon apparent geocentric declination DMS, d
    */
    val moonApparentGeoDeclinationDMS = toDegreeFullRound2(moonApparentGeoDeclination)
    
    /**
    * moon geocentric greenwich hour angle, Ho
    */
    val moonGeoGreenwichHourAngle = (greenwichApparentSideralTime - moonApparentGeocentricRightAscension).mod(360.0)
    
    /**
    * moon geocentric greenwich hour angle DMS, Ho
    */
    val moonGeoGreenwichHourAngleDMS = toDegreeFullRound2(moonGeoGreenwichHourAngle)
    
    /**
    * moon geocentric local hour angle, H
    */
    val moonGeoLocalHourAngle = (moonGeoGreenwichHourAngle + longitude).mod(360.0)
    
    /**
    * moon geocentric local hour angle DMS, H
    */
    val moonGeoLocalHourAngleDMS = toDegreeFullRound2(moonGeoLocalHourAngle)
    
    /**
    * moon azimuth dari selatan
    */
    val moonGeoAzimuthFromSelatan = Math.toDegrees(atan2(sin(Math.toRadians(moonGeoLocalHourAngle)), cos(Math.toRadians(moonGeoLocalHourAngle)) * sin(Math.toRadians(latitude)) - tan(Math.toRadians(moonApparentGeoDeclination)) * cos(Math.toRadians(latitude))))
    
    /**
    * moon azimuth dari selatan DMS, A
    */
    val moonGeoAzimuthFromSelatanDMS = toDegreeFullRound2(moonGeoAzimuthFromSelatan)
    
    /**
    * moon geo azimuth dari utara
    */
    val moonGeoAzimuthFromUtara = (moonGeoAzimuthFromSelatan + 180).mod(360.0)
    
    /**
    * moon geo azimuth dari utara DMS, A
    */
    val moonGeoAzimuthFromUtaraDMS = toDegreeFullRound2(moonGeoAzimuthFromUtara)
    
    /**
    * moon geo altitude, tinggi bulan hakiki, h
    */
    val moonGeoAltitude = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(moonApparentGeoDeclination)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(moonApparentGeoDeclination)) * cos(Math.toRadians(moonGeoLocalHourAngle))))
    
    /**
    * moon geo altitude DMS, tinggi bulan hakiki, h
    */
    val moonGeoAltitudeDMS = toDegreeFullRound2(moonGeoAltitude)
    
    /**
    * moon sun apparent geocentric elongation, d
    */
    val moonSunApparentGeoElongation = Math.toDegrees(acos(sin(Math.toRadians(moonApparentGeoDeclination)) * sin(Math.toRadians(sunApparentGeoDeclination)) + cos(Math.toRadians(moonApparentGeoDeclination)) * cos(Math.toRadians(sunApparentGeoDeclination)) * cos(Math.toRadians(moonApparentGeocentricRightAscension - sunApparentGeoRightAscension))))
    
    /**
    * moon sun apparent geocentric elongation DMS, d
    */
    val moonSunApparentGeoElongationDMS = toDegreeFullRound2(moonSunApparentGeoElongation)
    
    /**
    * moon apparent geocentric phase angle, i
    */
    val moonApparentGeoPhaseAngle = Math.toDegrees(atan2(sunTrueGeocentricDistanceKM * sin(Math.toRadians(moonSunApparentGeoElongation)), moonTrueGeocentricDistanceKM - sunTrueGeocentricDistanceKM * cos(Math.toRadians(moonSunApparentGeoElongation))))
    
    /**
    * moon apparent geocentric phase angle DMS, i
    */
    val moonApparentGeoPhaseAngleDMS = toDegreeFullRound2(moonApparentGeoPhaseAngle)
    
    /**
    * moon apparent geocentric disk illuminated fraction, k
    */
    val moonApparentGeoDiskIlluminatedFraction = (1 + cos(Math.toRadians(moonApparentGeoPhaseAngle))) / 2
    
    /**
    * moon apparent geocentric disk illuminated fraction % percent, k%
    */
    val moonApparentGeoDiskIlluminatedFractionPercent = 100 * moonApparentGeoDiskIlluminatedFraction
    
    /**
    * moon apparent geocentric bright limb angle, X
    */
    val moonApparentGeoBrightLimbAngle = (Math.toDegrees(atan2(cos(Math.toRadians(sunApparentGeoDeclination)) * sin(Math.toRadians(sunApparentGeoRightAscension - moonApparentGeocentricRightAscension)), sin(Math.toRadians(sunApparentGeoDeclination)) * cos(Math.toRadians(moonApparentGeoDeclination)) - cos(Math.toRadians(sunApparentGeoDeclination)) * sin(Math.toRadians(moonApparentGeoDeclination)) * cos(Math.toRadians(sunApparentGeoRightAscension - moonApparentGeocentricRightAscension))))).mod(360.0)
    
    /**
    * moon apparent geocentric bright limb angle DMS, X
    */
    val moonApparentGeoBrightLimbAngleDMS = toDegreeFullRound2(moonApparentGeoBrightLimbAngle)
    
    
    // moon topocentric ecliptic coor
    
    /**
    * suku n bulan dalam radian
    */
    val suku_n_moon = cos(Math.toRadians(moonApparentGeocentricLongitude)) * cos(Math.toRadians(moonApparentGeocentricLatitude)) - suku_x * sin(Math.toRadians(moonEquatorialHorizontalParallax)) * cos(Math.toRadians(localApparentSideralTimeDegrees))
    
    /**
    * moon apparent topocentric eclip longitude, lambda`
    */
    val moonApparentTopoLongitude = (Math.toDegrees(atan2(sin(Math.toRadians(moonApparentGeocentricLongitude)) * cos(Math.toRadians(moonApparentGeocentricLatitude)) - sin(Math.toRadians(moonEquatorialHorizontalParallax)) * (suku_y * sin(Math.toRadians(trueObliquityOfEcliptic)) + suku_x * cos(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(localApparentSideralTimeDegrees))), suku_n_moon))).mod(360.0)
    
    /**
    * moon apparent topocentric eclip longitude DMS, lambda`
    */
    val moonApparentTopoLongitudeDMS = toDegreeFullRound2(moonApparentTopoLongitude)
    
    /**
    * moon apparent topocentric eclip latitude, beta`
    */
    val moonApparentTopoLatitude = Math.toDegrees(atan(cos(Math.toRadians(moonApparentTopoLongitude)) * (sin(Math.toRadians(moonApparentGeocentricLatitude)) - sin(Math.toRadians(moonEquatorialHorizontalParallax)) * (suku_y * cos(Math.toRadians(trueObliquityOfEcliptic)) - suku_x * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(localApparentSideralTimeDegrees)))) / suku_n_moon))
    
    /**
    * moon apparent topocentric eclip latitude DMS, beta`
    */
    val moonApparentTopoLatitudeDMS = toDegreeFullRound2(moonApparentTopoLatitude)
    
    /**
    * moon apparent topocentric semidiameter, s`
    */
    val moonApparentTopoSemidiameter = Math.toDegrees(asin(cos(Math.toRadians(moonApparentTopoLongitude)) * cos(Math.toRadians(moonApparentTopoLatitude)) * sin(Math.toRadians(moonApparentGeocentricSemidiameter)) / suku_n_moon))
    
    /**
    * moon apparent topocentric semidiameter DMS, s`
    */
    val moonApparentTopoSemidiameterDMS = toDegreeFullRound2(moonApparentTopoSemidiameter)
    
    /**
    * parallax in the moon right ascension, delta a
    */
    val parallaxMoonRightAscension = Math.toDegrees(atan2(-1 * suku_x * sin(Math.toRadians(moonEquatorialHorizontalParallax)) * sin(Math.toRadians(moonGeoLocalHourAngle)), cos(Math.toRadians(moonApparentGeoDeclination)) -  suku_x * sin(Math.toRadians(moonEquatorialHorizontalParallax)) * cos(Math.toRadians(moonGeoLocalHourAngle))))
    
    /**
    * parallax in the moon right ascension DMS, delta a
    */
    val parallaxMoonRightAscensionDMS = toDegreeFullRound2(parallaxMoonRightAscension)
    
    /**
    * moon apparent topocentric right ascension, a`
    */
    val moonApparentTopoRightAscension = (moonApparentGeocentricRightAscension + parallaxMoonRightAscension).mod(360.0)
    
    /**
    * moon apparent topocentric right ascension DMS, a`
    */
    val moonApparentTopoRightAscensionDMS = toDegreeFullRound2(moonApparentTopoRightAscension)
    
    /**
    * moon apparent topocentric declination, d`
    */
    val moonApparentTopoDeclination = Math.toDegrees(atan2(cos(Math.toRadians(parallaxMoonRightAscension)) * (sin(Math.toRadians(moonApparentGeoDeclination)) - suku_y * sin(Math.toRadians(moonEquatorialHorizontalParallax))), cos(Math.toRadians(moonApparentGeoDeclination)) - suku_x * sin(Math.toRadians(moonEquatorialHorizontalParallax)) * cos(Math.toRadians(moonGeoLocalHourAngle))))
    
    /**
    * moon apparent topocentric declination DMS, d`
    */
    val moonApparentTopoDeclinationDMS = toDegreeFullRound2(moonApparentTopoDeclination)
    
    /**
    * moon apparent topocentric local hour angle, H`
    */
    val moonApparentTopoLocalHourAngle = Math.toDegrees(atan2(cos(Math.toRadians(moonApparentGeoDeclination)) * sin(Math.toRadians(moonGeoLocalHourAngle)), cos(Math.toRadians(moonApparentGeoDeclination)) * cos(Math.toRadians(moonGeoLocalHourAngle)) - suku_x * sin(Math.toRadians(moonEquatorialHorizontalParallax))))
    
    /**
    * moon apparent topocentric local hour angle DMS, H`
    */
    val moonApparentTopoLocalHourAngleDMS = toDegreeFullRound2(moonApparentTopoLocalHourAngle)
    
    /**
    * moon topocentric azimuth, A`
    */
    val moonTopocentricAzimuth = (Math.toDegrees(atan2(sin(Math.toRadians(moonApparentTopoLocalHourAngle)), cos(Math.toRadians(moonApparentTopoLocalHourAngle)) * sin(Math.toRadians(latitude)) - tan(Math.toRadians(moonApparentTopoDeclination)) * cos(Math.toRadians(latitude)))) + 180).mod(360.0)
    
    /**
    * moon topocentric azimuth DMS, A`
    */
    val moonTopocentricAzimuthDMS = toDegreeFullRound2(moonTopocentricAzimuth)
    
    /**
    * parallax in the moon altitude, P
    */
    val parallaxMoonAltitude = Math.toDegrees(asin(suku_rho * sin(Math.toRadians(moonEquatorialHorizontalParallax)) * cos(Math.toRadians(moonGeoAltitude))))
    
    /**
    * parallax in the moon altitude DMS, P
    */
    val parallaxMoonAltitudeDMS = toDegreeFullRound2(parallaxMoonAltitude)
    
    // center
    
    /**
    * moon airless topocentric altitude center limb, h`c
    */
    val moonAirlessTopoAltitudeCenterLimb = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(moonApparentTopoDeclination)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(moonApparentTopoDeclination)) * cos(Math.toRadians(moonApparentTopoLocalHourAngle))))
    
    /**
    * moon airless topocentric altitude center limb DMS, h`c
    */
    val moonAirlessTopoAltitudeCenterLimbDMS = toDegreeFullRound2(moonAirlessTopoAltitudeCenterLimb)
    
    /**
    * moon atmospheric refraction for center limb, Rc
    */
    val moonAtmosphericRefractionForCenterLimb = (1.02 / tan(Math.toRadians(moonAirlessTopoAltitudeCenterLimb + 10.3 / (moonAirlessTopoAltitudeCenterLimb + 5.11))) * pressure / 1010 * 283.0 / (273.0 + temperature) + 0.0019279204034639303) / 60
    
    /**
    * moon atmospheric refraction for center limb DMS, Rc
    */
    val moonAtmosphericRefractionForCenterLimbDMS = toDegreeFullRound2(moonAtmosphericRefractionForCenterLimb)
    
    /**
    * moon apparent topocentric altitude center limb, h`ac
    */
    val moonApparentTopoAltitudeCenterLimb = moonAirlessTopoAltitudeCenterLimb + moonAtmosphericRefractionForCenterLimb
    
    /**
    * moon apparent topocentric altitude center limb DMS, h`ac
    */
    val moonApparentTopoAltitudeCenterLimbDMS = toDegreeFullRound2(moonApparentTopoAltitudeCenterLimb)
    
    /**
    * moon observed altitude center limb, h`oc
    */
    val moonObservedAltitudeCenterLimb = moonApparentTopoAltitudeCenterLimb + dip
    
    /**
    * moon observed altitude center limb DMS, h`oc
    */
    val moonObservedAltitudeCenterLimbDMS = toDegreeFullRound2(moonObservedAltitudeCenterLimb)
    
    // upper
    
    /**
    * moon airless topocentric altitude upper limb, h`u
    */
    val moonAirlessTopoAltitudeUpperLimb = moonAirlessTopoAltitudeCenterLimb + moonApparentTopoSemidiameter
    
    /**
    * moon airless topocentric altitude upper limb DMS, h`u
    */
    val moonAirlessTopoAltitudeUpperLimbDMS = toDegreeFullRound2(moonAirlessTopoAltitudeUpperLimb)
    
    /**
    * moon atmospheric refraction for upper limb, Ru
    */
    val moonAtmosphericRefractionForUpperLimb = (1.02 / tan(Math.toRadians(moonAirlessTopoAltitudeUpperLimb + 10.3 / (moonAirlessTopoAltitudeUpperLimb + 5.11))) * pressure / 1010 * 283.0 / (273.0 + temperature) + 0.0019279204034639303) / 60
    
    /**
    * moon atmospheric refraction for upper limb DMS, Ru
    */
    val moonAtmosphericRefractionForUpperLimbDMS = toDegreeFullRound2(moonAtmosphericRefractionForUpperLimb)
    
    /**
    * moon apparent topocentric altitude upper limb, h`au
    */
    val moonApparentTopoAltitudeUpperLimb = moonAirlessTopoAltitudeUpperLimb + moonAtmosphericRefractionForUpperLimb
    
    /**
    * moon apparent topocentric altitude upper limb DMS, h`au
    */
    val moonApparentTopoAltitudeUpperLimbDMS = toDegreeFullRound2(moonApparentTopoAltitudeUpperLimb)
    
    /**
    * moon observed altitude upper limb, h`ou
    */
    val moonObservedAltitudeUpperLimb = moonApparentTopoAltitudeUpperLimb + dip
    
    /**
    * moon observed altitude upper limb DMS, h`ou
    */
    val moonObservedAltitudeUpperLimbDMS = toDegreeFullRound2(moonObservedAltitudeUpperLimb)
    
    // lower
    
    /**
    * moon airless topocentric altitude lower limb, h`l
    */
    val moonAirlessTopoAltitudeLowerLimb = moonAirlessTopoAltitudeCenterLimb - moonApparentTopoSemidiameter
    
    /**
    * moon airless topocentric altitude lower limb DMS, h`l
    */
    val moonAirlessTopoAltitudeLowerLimbDMS = toDegreeFullRound2(moonAirlessTopoAltitudeLowerLimb)
    
    /**
    * moon atmospheric refraction for lower limb, Rl
    */
    val moonAtmosphericRefractionForLowerLimb = (1.02 / tan(Math.toRadians(moonAirlessTopoAltitudeLowerLimb + 10.3 / (moonAirlessTopoAltitudeLowerLimb + 5.11))) * pressure / 1010 * 283.0 / (273.0 + temperature) + 0.0019279204034639303) / 60
    
    /**
    * moon atmospheric refraction for lower limb DMS, Rl
    */
    val moonAtmosphericRefractionForLowerLimbDMS = toDegreeFullRound2(moonAtmosphericRefractionForLowerLimb)
    
    /**
    * moon apparent topocentric altitude lower limb, h`al
    */
    val moonApparentTopoAltitudeLowerLimb = moonAirlessTopoAltitudeLowerLimb + moonAtmosphericRefractionForLowerLimb
    
    /**
    * moon apparent topocentric altitude lower limb DMS, h`al
    */
    val moonApparentTopoAltitudeLowerLimbDMS = toDegreeFullRound2(moonApparentTopoAltitudeLowerLimb)
    
    /**
    * moon observed altitude lower limb, h`ol
    */
    val moonObservedAltitudeLowerLimb = moonApparentTopoAltitudeLowerLimb + dip
    
    /**
    * moon observed altitude lower limb DMS, h`ol
    */
    val moonObservedAltitudeLowerLimbDMS = toDegreeFullRound2(moonObservedAltitudeLowerLimb)
    
    
    
    
    
    
    
    // val test = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
    
    
}
