
package com.andihasan7.lib.ephemeris.jeanmeeus.arahqiblat

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.tan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.mod
import com.andihasan7.lib.ephemeris.jeanmeeus.EphemerisMeeus

class ArahQiblat {
    
    fun arahQiblat(latitude: Double, longitude: Double): DoubleArray {
        
        val latitudeKabah = 21.4225
        val longitudeKabah = 39.826111111111

        // Selisih azimuthBUjur

        val selisihazimuthBUjur = 360 - longitudeKabah + longitude - 360

        val h = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(latitudeKabah)) + cos(Math.toRadians(latitude))  * cos(Math.toRadians(latitudeKabah)) * cos(Math.toRadians(selisihazimuthBUjur))))

        // Azimuth U-B
        val azimuthUB = Math.toDegrees(acos((sin(Math.toRadians(latitudeKabah)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(h))) / cos(Math.toRadians(latitude)) / cos(Math.toRadians(h))))

        // Azimuth B-U
        val azimuthBU = 90 - azimuthUB

        // Azimuth UTSB
        val azimuthUTSB = 360 - azimuthUB
            
        return doubleArrayOf(azimuthUB, azimuthBU, azimuthUTSB)
    }
    
    fun rashduQiblat(
        date: Int,
        month: Int,
        year: Int,
        latitude: Double,
        longitude: Double,
        elevation: Double,
        timeZone: Double,
        azimuthUTSB: Double
        ): DoubleArray {
        
        val jm = EphemerisMeeus(
            date = date,
            month = month,
            year = year,
            latitude = latitude,
            longitude = longitude,
            elevation = elevation,
            timeZone = timeZone,
            hourDouble = 5.0
        )
        
        val dek = jm.sunApparentGeoDeclination
        val eq = jm.equationOfTimeHour
        
        // Roshdul Qiblat
        val b = 90 - latitude 

        val pR = Math.toDegrees(atan(1.0 / (cos(Math.toRadians(b)) * tan(Math.toRadians(azimuthUTSB)))))

        val cA = Math.toDegrees(acos(tan(Math.toRadians(dek)) * tan(Math.toRadians(b)) * cos(Math.toRadians(pR))))

        // Roshdul Qiblat 1

        val rq1 = -((pR + cA) / 15) + 12//% 24
        val rq2 = rq1.mod(24.0)

        // Roshdul Qiblat 1 TimeZone
        val rashdu1 = (rq2 - eq - (longitude - ((timeZone).toDouble() * 15)) / 15).mod(24.0)

        // Roshdul Qiblat 2

        val rQ = -(pR - cA) / 15 + 12

        // Roshdul Qiblat 2 TimeZone
        val rashdu2 = (rQ - eq - (longitude - ((timeZone).toDouble() * 15)) / 15).mod(24.0)
        
        return doubleArrayOf(rashdu1, rashdu2)
    
    }
    
    
    
}