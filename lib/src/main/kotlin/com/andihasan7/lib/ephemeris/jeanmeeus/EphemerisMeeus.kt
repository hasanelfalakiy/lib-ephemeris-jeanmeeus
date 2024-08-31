/**
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

package com.andihasan7.lib.ephemeris.jeanmeeus

import com.andihasan7.lib.ephemeris.jeanmeeus.util.masehiToJD

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
	
    // val jamDes = (hour + minute / 60 + second / 3600)
    
    // JD
    val jd = masehiToJD(
        date,
        month,
        year,
        hourDouble,
        timeZone
    )
}
