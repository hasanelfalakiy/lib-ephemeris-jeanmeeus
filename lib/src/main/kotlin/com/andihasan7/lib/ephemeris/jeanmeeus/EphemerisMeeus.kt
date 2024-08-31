

package com.andihasan7.lib.ephemeris.jeanmeeus

import com.andihasan7.lib.ephemeris.jeanmeeus.util.masehiToJD

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
	date: Int, // tanggal masehi
    month: Int, // bulan masehi
    year: Int, // tahun masehi
    latitude: Double = 0.0, // lintang tempat
    longitude: Double = 0.0, // bujur tempat
    timeZone: Double = 0.0, // zona waktu
    hourDouble: Double = 0.0, // jam double
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
    val deltaT = if (checkDeltaT == true) {
        deltaT(date, month, year)
    } else {
        0.0
    }
    
    /**
    * julian day ephemeris (JDE)
    */
    val jde = jd + (deltaT / 86400)
}
