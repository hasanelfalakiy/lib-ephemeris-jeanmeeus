
package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.pow

/**
* deltaT dari tabel buku ilmu falak rumusan syar'i dan astronomi seri 3
*/
fun deltaT(date: Int, month: Int, year: Int): Double {
    
    var u: Double
    var korC: Double
    var deltaT: Double
    
    val y = year + (month - 1).toDouble() / 12 + (date).toDouble() / 365
    
    when {
        
        year <= -500 -> {
            u = (y - 1820) / 100
            deltaT = -20 + 32 * u.pow(2)
        }
        
        year > -500 && year <= 500 -> {
            u = y / 100
            deltaT = 10583.6 - 1014.41 * u + 33.78311 * u.pow(2) - 5.952053 * u.pow(3) - 0.1798452 * u.pow(4) + 0.022174192 * u.pow(5) + 0.0090316521 * u.pow(6)
        }
        
        year > 500 && year <= 1600 -> {
            u = (y - 1000) / 100
            deltaT = 1574.2 - 556.01 * u + 71.23472 * u.pow(2) + 0.319781 * u.pow(3) - 0.8503463 * u.pow(4) - 0.005050998 * u.pow(5) + 0.0083572073 * u.pow(6)
        }
        
        year > 1600 && year <= 1700 -> {
            u = (y - 1600) / 100
            deltaT = 120 - 98.08 * u - 153.2 * u.pow(2) + u.pow(3) / 0.007129
        }
        
        year > 1700 && year <= 1800 -> {
            u = (y - 1700) / 100
            deltaT = 8.83 + 16.03 * u - 59.285 * u.pow(2) + 133.36 * u.pow(3) - u.pow(4) / 0.01174
        }
        
        year > 1800 && year <= 1860 -> {
            u = (y - 1800) / 100
            deltaT = 13.72 - 33.2447 * u + 68.612 * u.pow(2) + 4111.6 * u.pow(3) - 37436 * u.pow(4) + 121272 * u.pow(5) - 1699000 * u.pow(6) + 87500 * u.pow(7)
        }
        
        year > 1860 && year <= 1900 -> {
            u = (y - 1860) / 100
            deltaT = 7.62 + 57.37 * u - 2517.54 * u.pow(2) + 16806.68 * u.pow(3) - 44736.24 * u.pow(4) + u.pow(5) / 0.00000233174
        }
        
        year > 1900 && year <= 1920 -> {
            u = (y - 1900) / 100
            deltaT = -2.79 + 149.4119 * u - 598.939 * u.pow(2) + 6196.6 * u.pow(3) - 19700 * u.pow(4)
        }
        
        year > 1920 && year <= 1941 -> {
            u = (y - 1920) / 100
            deltaT = 21.20 + 84.493 * u - 761.00 * u.pow(2) + 2093.6 * u.pow(3)
        }
        
        year > 1941 && year <= 1961 -> {
            u = (y - 1950) / 100
            deltaT = 29.07 + 40.7 * u - u.pow(2) / 0.0233 + u.pow(3) / 0.002547
        }
        
        year > 1961 && year <= 1986 -> {
            u = (y - 1975) / 100
            deltaT = 45.45 + 106.7 * u - u.pow(2) / 0.026 - u.pow(3) / 0.000718
        }
        
        year > 1986 && year <= 2005 -> {
            u = (y - 2000) / 100
            deltaT = 63.86 + 33.45 * u - 603.74 * u.pow(2) + 1727.5 * u.pow(3) + 65181.4 * u.pow(4) + 237359.9 * u.pow(5)
        }
        
        year > 2005 && year <= 2015 -> {
            u = y - 2005
            deltaT = 64.69 + 0.293 * u
        }
        
        year > 2015 && year <= 3000 -> {
            u = y - 2015
            deltaT = 67.62 + 0.3645 * u + 0.0039755 * u.pow(2)
        }
        
        else -> {
            deltaT = 0.0
        }
        
        
    }
    
    if (year < 1955 || year > 2005) {
        korC = -0.000012932 * (y - 1955).pow(2)
        deltaT = deltaT + korC
    } else {
        deltaT = deltaT
    }
    
    return deltaT
}

/*
dr astronomical algorithm jean meeus
fun deltaT(date: Int, month: Int, year: Int): Double {
    
    var t = (year - 2000).toDouble() / 100
    var deltaT: Double
    
    deltaT = when {
        year < 948 -> (2715.6 + 573.36 * t + 46.5 * t.pow(2))
        year <= 948 && year <= 1600 -> (50.6 + 67.5 * t + 22.5 * t.pow(2))
        year > 1600 -> (102.3 + 123.5 * t + 32.5 * t.pow(2))
        else -> 0.0
    }
    
    return deltaT
}
*/ 
