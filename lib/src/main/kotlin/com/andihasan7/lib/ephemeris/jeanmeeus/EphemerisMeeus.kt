

package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.atan
import kotlin.math.asin
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
    
    
    val krd = Nutasi.deltaPsiDanEpsilon(nilaiT)[8]
    val krm = Nutasi.deltaPsiDanEpsilon(nilaiT)[9]
    val krm1 = Nutasi.deltaPsiDanEpsilon(nilaiT)[10]
    val krf = Nutasi.deltaPsiDanEpsilon(nilaiT)[11]
    val kromega = Nutasi.deltaPsiDanEpsilon(nilaiT)[12]
    
    
    
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
    * local apparent sideral time, theta
    */
    val localApparentSideralTime = (greenwichApparentSideralTime + longitude).mod(360.0)
    
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
    val suku_y = (jari2BumiPolar / JARI2BUMIEQUATOR) * sin(suku_u) + (elevation / 6378140) * sin(Math.toRadians(latitude))
    val suku_x = cos(suku_u) + (elevation / 6378140) * cos(Math.toRadians(latitude))
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
    val suku_n = cos(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(sunApparentGeoLatitude)) - suku_x * sin(Math.toRadians(eqHorizontalParallaxSun)) * cos(Math.toRadians(localApparentSideralTime))
    
    
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
    // val sunTopocentricEclipLongitude = (Math.toDegrees(atan2(sin(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * sin(Math.toRadians(trueObliquityOfEcliptic)) + suku_x * cos(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(sunTrueGeocentricLonJ2000Degrees))), suku_n))).mod(360.0) buku seri 3
    
    val sunTopocentricEclipLongitude = (Math.toDegrees(atan2(sin(Math.toRadians(sunApparentGeoLongitude)) * cos(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * sin(Math.toRadians(trueObliquityOfEcliptic)) + suku_x * cos(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(localApparentSideralTime))), suku_n))).mod(360.0)
    
    
    /**
    * sun topocentric ecliptic longitude DMS, lambda`
    */
    val sunTopocentricEclipLongitudeDMS = toDegreeFullRound2(sunTopocentricEclipLongitude)
    
    /**
    * sun topocentric ecliptic latitude, beta`
    */
    // val sunTopocentricEclipLatitude = Math.toDegrees(atan2(cos(Math.toRadians(sunTopocentricEclipLongitude)) * (sin(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * cos(Math.toRadians(trueObliquityOfEcliptic)) - suku_x * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(sunTrueGeocentricLonJ2000Degrees)))), suku_n))
    
    val sunTopocentricEclipLatitude = Math.toDegrees(atan2(cos(Math.toRadians(sunTopocentricEclipLongitude)) * (sin(Math.toRadians(sunApparentGeoLatitude)) - sin(Math.toRadians(eqHorizontalParallaxSun)) * (suku_y * cos(Math.toRadians(trueObliquityOfEcliptic)) - suku_x * sin(Math.toRadians(trueObliquityOfEcliptic)) * sin(Math.toRadians(localApparentSideralTime)))), suku_n))
    
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
    * moon apparent geocentric longitude DMS, beta
    */
    val moonApparentGeocentricLongitudeDMS = toDegreeFullRound2(moonApparentGeocentricLongitude)
    
    
    
    // val test = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
    
    
}
