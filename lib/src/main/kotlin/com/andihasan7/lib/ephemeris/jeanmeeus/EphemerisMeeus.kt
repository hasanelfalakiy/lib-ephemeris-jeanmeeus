

package com.andihasan7.lib.ephemeris.jeanmeeus

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
    
    
    val test = Nutasi.deltaPsiDanEpsilon(nilaiT)[1]
}
