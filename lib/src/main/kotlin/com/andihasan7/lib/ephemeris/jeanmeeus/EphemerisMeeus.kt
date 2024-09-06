

package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.atan2
import kotlin.math.atan
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.pow
import kotlin.math.PI
import com.andihasan7.lib.ephemeris.jeanmeeus.util.masehiToJD
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2
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
*        timeZone: Double = 0.0, // zona waktu
*        hourDouble: Double = 0.0, // jam double/desimal
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
    timeZone: Double = 0.0, // zona waktu
    hourDouble: Double = 0.0, // jam desimal
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
	 * sun geometric longitude lamdaM `
	 */
	val sunGeometricLonLamdaM = TabelMatahari.bujurEkliptik(tau, nilaiT)[3]
    
    /**
	 * sun geometric longitude lamdaM ` DMS
	 */
	val sunGeometricLonLamdaMDMS = toDegreeFullRound2(sunGeometricLonLamdaM)
    
	/**
	 * sun geometric longitude lamdaM ` radians
	 */
	val sunGeometricLonLamdaMRadians = TabelMatahari.bujurEkliptik(tau, nilaiT)[6]
    
    /**
	 * sun geometric longitude lamdaM ` radians DMS
	 */
	val sunGeometricLonLamdaMRadiansDMS = toDegreeFullRound2(sunGeometricLonLamdaMRadians)
    
    /**
	 * sun geometric longitude lamdaM ` radians
	 */
	val sunGeometricLonLamdaMDegrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[3]
    
    /**
	 * sun geometric longitude lamdaM ` radians DMS
	 */
	val sunGeometricLonLamdaMDegreesDMS = toDegreeFullRound2(sunGeometricLonLamdaMDegrees)
    
    
	/**
	 * sun true geocentric equinox J2000 Degrees
	 */
	val sunTrueGeocentricLonJ2000Degrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[5]
    
    /**
	 * sun true geocentric equinox J2000 Degrees DMS
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
    
    
    
    
    
    val test = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
    
    
    
    
    
    
    
}
