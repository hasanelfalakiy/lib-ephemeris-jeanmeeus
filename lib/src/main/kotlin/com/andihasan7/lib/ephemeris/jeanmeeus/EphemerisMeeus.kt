

package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.PI
import com.andihasan7.lib.ephemeris.jeanmeeus.util.masehiToJD
import com.andihasan7.lib.ephemeris.jeanmeeus.util.toDegreeFullRound2

/**
* 
* 
* ```
*    class EphemerisMeeus(
*        date: Int, // tanggal masehi
*        month: Int, // bulan masehi
*        year: Int, // tahun masehi
*        latitude: Double = 0.0, // lintang tempat
*        longitude: Double = 0.0, // bujur tempat
*        timeZone: Double = 0.0, // zona waktu
*        hourDouble: Double = 0.0, // jam double
*        checkDeltaT: Boolean = true // pilihan pakai deltaT atau tidak
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
    * nilai T / JCE julian ephemeris century
    */
    val nilaiT = (jde - 2451545.0).toDouble() / 36525.0
    
    /**
    * nilai tau / JME julian ephemeris millenium
    */
    val tau = nilaiT / 10.0
    
    /**
    * deltaPsi (nutation in longitude)
    */
    val deltaPsi = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
    val deltaPsiDMS = toDegreeFullRound2(deltaPsi)
    // deltaPsi_d
    val deltaPsi_d = Nutasi.deltaPsiDanEpsilon(nilaiT)[2]
    // u
    val u = Nutasi.deltaPsiDanEpsilon(nilaiT)[3]
    // epsilonZero (mean obliquity of ecliptic)
    val meanObliquityOfEcliptic = Nutasi.deltaPsiDanEpsilon(nilaiT)[4]
    val meanObliquityOfEclipticDMS = toDegreeFullRound2(meanObliquityOfEcliptic)
    /**
    * deltaEpsilon (nutation of obliquity)
    */
    val deltaEps = Nutasi.deltaPsiDanEpsilon(nilaiT)[5]
    val deltaEpsDMS = toDegreeFullRound2(deltaEps)
    /**
    * epsilon (true obliquity of ecliptic)
    */
    val trueObliquityOfEcliptic = Nutasi.deltaPsiDanEpsilon(nilaiT)[6]
    val trueObliquityOfEclipticDMS = toDegreeFullRound2(trueObliquityOfEcliptic)
    
	/**
	 * earth heliocentric longitude radians
	 */
	val earthHeliocentricLongitudeRadians = TabelMatahari.bujurEkliptik(tau, nilaiT)[0]
	
	/**
	 * earth heliocentric longitude degrees 
	 */
	val earthHeliocentricLongitudeDegrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[1]
    val earthHeliocentricLongitudeDegreesDMS = toDegreeFullRound2(earthHeliocentricLongitudeDegrees)
	
	/**
	 * sun geometric longitude degrees
	 */
	val sunGeometricLongitudeDegrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[2]
	val sunGeometricLongitudeDegreesDMS = toDegreeFullRound2(sunGeometricLongitudeDegrees)
    
	/**
	 * sun geometric longitude lamdaM `
	 */
	val sunGeometricLonLamdaM = TabelMatahari.bujurEkliptik(tau, nilaiT)[3]
	val sunGeometricLonLamdaMDMS = toDegreeFullRound2(sunGeometricLonLamdaM)
    
	/**
	 * sun geometric longitude lamdaM ` radians
	 */
	val sunGeometricLonLamdaMRadians = TabelMatahari.bujurEkliptik(tau, nilaiT)[6]
	val sunGeometricLonLamdaMRadiansDMS = toDegreeFullRound2(sunGeometricLonLamdaMRadians)
    
	/**
	 * sun true geocentric equinox J2000 Degrees
	 */
	val sunTrueGeocentricJ2000Degrees = TabelMatahari.bujurEkliptik(tau, nilaiT)[5]
    val sunTrueGeocentricJ2000DegreesDMS = toDegreeFullRound2(sunTrueGeocentricJ2000Degrees)
    
    /**
	 * earth heliocentric latitude radians
	 */
	val earthHeliocentricLatitudeRadians = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMRadians)[0]
    val earthHeliocentricLatitudeRadiansDMS = toDegreeFullRound2(earthHeliocentricLatitudeRadians)
	
	/**
	 * koreksi delta theta untuk equinox dynamic FK5 system
	 */
	val deltaThetaDynamicFK5 = (-0.09033 + 0.03916 * (cos(sunGeometricLonLamdaM) + sin(sunGeometricLonLamdaM)) * tan(earthHeliocentricLatitudeRadians))
	
	/**
	 * sun true geocentric FK5 Degrees
	 */
	val sunTrueGeocentricLonFK5Degrees = sunGeometricLongitudeDegrees + deltaThetaDynamicFK5
    val sunTrueGeocentricLonFK5DegreesDMS = toDegreeFullRound2(sunTrueGeocentricLonFK5Degrees)
	
	
	
	/**
	 * delta beta
	 */
	val deltaBeta = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMRadians)[1]
	
	/**
	 * sun true geocentric latitude radians
	 */
	val sunTrueGeocentricLatitudeDegrees = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMRadians)[2]
	val sunTrueGeocentricLatitudeDegreesDMS = toDegreeFullRound2(sunTrueGeocentricLatitudeDegrees)
	/**
	 * sun true geocentric latitude degrees
	 */
	val sunTrueGeocentricLatitudeRadians = (sunTrueGeocentricLatitudeDegrees * 180) / PI
	val sunTrueGeocentricLatitudeRadiansDMS = toDegreeFullRound2(sunTrueGeocentricLatitudeRadians)
	
	/**
	 * b_detikBusur
	 */
	val b_detikBusur = TabelMatahari.lintangEkliptikB(tau, sunGeometricLonLamdaMRadians)[3]
    
    /**
    * true geocentric distance AU, vector radius jarak bumi matahari
    */
    val trueGeocentricDistanceAU = TabelMatahari.jarakBumiMat(tau)
	
	/**
	 * true geocentric distance KM
	 */
	val trueGeocentricDistanceKM = trueGeocentricDistanceAU * 149597870.7
	
	/**
	 * true geocentric distance ER
	 */
	val trueGeocentricDistanceER = trueGeocentricDistanceAU * 149597870.7 / 6371
    
    val test = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
}
