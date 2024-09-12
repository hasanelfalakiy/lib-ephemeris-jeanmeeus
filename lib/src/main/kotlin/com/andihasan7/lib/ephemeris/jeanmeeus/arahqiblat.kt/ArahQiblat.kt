
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
        
        val LATITUDEKABAH = 21.4225
        val LONGITUDEKABAH = 39.826111111111

        // Selisih azimuthBUjur

        val selisihazimuthBUjur = 360 - LONGITUDEKABAH + longitude - 360

        val h = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(LATITUDEKABAH)) + cos(Math.toRadians(latitude))  * cos(Math.toRadians(LATITUDEKABAH)) * cos(Math.toRadians(selisihazimuthBUjur))))

        // Azimuth U-B
        val azimuthUB = Math.toDegrees(acos((sin(Math.toRadians(LATITUDEKABAH)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(h))) / cos(Math.toRadians(latitude)) / cos(Math.toRadians(h))))

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
            hourDouble = 12.0
        )
            
        
        val dek = jm.sunApparentGeoDeclination
        val eq = jm.equationOfTimeHour
        
        // Roshdul Qiblat
        val b = 90 - latitude 

        val pR = Math.toDegrees(atan(1.0 / (cos(Math.toRadians(b)) * tan(Math.toRadians(azimuthUTSB)))))

        val cA = Math.toDegrees(acos(tan(Math.toRadians(dek)) * tan(Math.toRadians(b)) * cos(Math.toRadians(pR))))

        // Roshdul Qiblat 1

        val rq1 = -(pR + cA) / 15

        // Roshdul Qiblat 1 TimeZone
        val rashdu1 = (rq1 + (12 - eq) + ((timeZone * 15) - longitude) / 15).mod(24.0)

        // Roshdul Qiblat 2

        val rQ = -(pR - cA) / 15

        // Roshdul Qiblat 2 TimeZone
        val rashdu2 = (rQ + (12 - eq) + ((timeZone * 15) - longitude) / 15).mod(24.0)
        
            
        return doubleArrayOf(rashdu1, rashdu2, dek, eq)
    
    }
    
    
    
}