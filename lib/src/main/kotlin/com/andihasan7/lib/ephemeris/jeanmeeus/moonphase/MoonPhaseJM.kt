/**
 * This file is part of lib-ephemeris-jeanmeeus.
 *
 * lib-ephemeris-jeanmeeus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * lib-ephemeris-jeanmeeus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with lib-ephemeris-jeanmeeus.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 * @programmed by: Andi Hasan A
 * @github: https://github.com/hasanelfalakiy
 * 
 *
 */
 
package com.andihasan7.lib.ephemeris.jeanmeeus.moonphase

import com.andihasan7.lib.ephemeris.jeanmeeus.enum.*
import com.andihasan7.lib.ephemeris.jeanmeeus.moonposition.MoonPosition
import com.andihasan7.lib.ephemeris.jeanmeeus.sunposition.SunPosition
import kotlin.math.*
import kotlin.mod

object MoonPhaseJM {
    /**
     * function to compute moon phases
     *
     * @param monthOfHijri month of hijri
     * @param yearOfHijri year of hijri
     * @param phaseType type of moon phases
     *
     * @return jde moon phase
     */
    fun moonPhase(monthOfHijri: Int, yearOfHijri: Int, phaseType: PhaseType): Double {

        // this formula (vHY and k) which modifies the gragorian input to hijri is taken from hisab-astronomis dev: Ust Abu Sabda
        val vHY = monthOfHijri.toDouble() + 12 * yearOfHijri.toDouble() - 17050
        val type = when (phaseType) {
            PhaseType.NEWMOON -> 0.0
            PhaseType.FIRSTQUARTER -> 0.25
            PhaseType.FULLMOON -> 0.5
            PhaseType.LASTQUARTER -> 0.75
        }
        val k = floor(vHY) + type
        val t = k.toDouble() / 1236.85
        val jdeMoonPhaseJM = 2451550.09766 + 29.530588861 * k +
                0.00015437 * t.pow(2) -
                0.00000015 * t.pow(3) +
                0.00000000073 * t.pow(4)
        val e = 1 - 0.002516 * t - 0.0000074 * t.pow(2)
        
        val m = Math.toRadians((2.5534 + 29.10535670 * k -
                0.0000014 * t.pow(2) -
                0.00000011 * t.pow(3)).mod(360.0))
        
        val m1 = Math.toRadians((201.5643 + 385.81693528 * k +
                0.0107582 * t.pow(2) +
                0.00001238 * t.pow(3) -
                0.000000058 * t.pow(4)).mod(360.0))
        
        val f = Math.toRadians((160.7108 + 390.67050284 * k -
                0.0016118 * t.pow(2) -
                0.00000227 * t.pow(3) +
                0.000000011 * t.pow(4)).mod(360.0))
        
        val omega = Math.toRadians((124.7746 - 1.56375588 * k +
                0.0020672 * t.pow(2) +
                0.00000215 * t.pow(3)).mod(360.0))

        // planetary argumnets
        val a1 = Math.toRadians((299.77 + 0.107408 * k - 0.009173 * t.pow(2)).mod(360.0))
        val a2 = Math.toRadians((251.88 + 0.016321 * k).mod(360.0))
        val a3 = Math.toRadians((251.83 + 26.651886 * k).mod(360.0))
        val a4 = Math.toRadians((349.42 + 36.412478 * k).mod(360.0))
        val a5 = Math.toRadians((84.66 + 18.206239 * k).mod(360.0))
        val a6 = Math.toRadians((141.74 + 53.303771 * k).mod(360.0))
        val a7 = Math.toRadians((207.14 + 2.453732 * k).mod(360.0))
        val a8 = Math.toRadians((154.84 + 7.30686 * k).mod(360.0))
        val a9 = Math.toRadians((34.52 + 27.261239 * k).mod(360.0))
        val a10 = Math.toRadians((207.19 + 0.121824 * k).mod(360.0))
        val a11 = Math.toRadians((291.34 + 1.844379 * k).mod(360.0))
        val a12 = Math.toRadians((161.72 + 24.198154 * k).mod(360.0))
        val a13 = Math.toRadians((239.56 + 25.513099 * k).mod(360.0))
        val a14 = Math.toRadians((331.55 + 3.592518 * k).mod(360.0))

        val jdeCor1 = when (phaseType) {
            PhaseType.NEWMOON -> -0.4072 * sin(m1) +
                    0.17241 * e * sin(m) +
                    0.01608 * sin(2 * m1) +
                    0.01039 * sin(2 * f) +
                    0.00739 * e * sin(m1 - m) -
                    0.00514 * e * sin(m1 + m) +
                    0.00208 * e.pow(2) * sin(2 * m) -
                    0.00111 * sin(m1 - 2 * f) -
                    0.00057 * sin(m1 + 2 * f) +
                    0.00056 * e * sin(2 * m1 + m) -
                    0.00042 * sin(3 * m1) +
                    0.00042 * e * sin(m + 2 * f) +
                    0.00038 * e * sin(m - 2 * f) -
                    0.00024 * e * sin(2 * m1 - m) -
                    0.00017 * sin(omega) -
                    0.00007 * sin(m1 + 2 * m) +
                    0.00004 * sin(2 * m1 - 2 * f) +
                    0.00004 * sin(3 * m) +
                    0.00003 * sin(m1 + m - 2 * f) +
                    0.00003 * sin(2 * m1 + 2 * f) -
                    0.00003 * sin(m1 + m + 2 * f) +
                    0.00003 * sin(m1 - m + 2 * f) -
                    0.00002 * sin(m1 - m - 2 * f) -
                    0.00002 * sin(3 * m1 + m) +
                    0.00002 * sin(4 * m1)

            PhaseType.FULLMOON -> -0.40614 * sin(m1) +
                    0.17302 * e * sin(m) +
                    0.01614 * sin(2 * m1) +
                    0.01043 * sin(2 * f) +
                    0.00734 * e * sin(m1 - m) -
                    0.00515 * e * sin(m1 + m) +
                    0.00209 * e.pow(2) * sin(2 * m) -
                    0.00111 * sin(m1 - 2 * f) -
                    0.00057 * sin(m1 + 2 * f) +
                    0.00056 * e * sin(2 * m1 + m) -
                    0.00042 * sin(3 * m1) +
                    0.00042 * e * sin(m + 2 * f) +
                    0.00038 * e * sin(m - 2 * f) -
                    0.00024 * e * sin(2 * m1 - m) -
                    0.00017 * sin(omega) -
                    0.00007 * sin(m1 + 2 * m) +
                    0.00004 * sin(2 * m1 - 2 * f) +
                    0.00004 * sin(3 * m) +
                    0.00003 * sin(m1 + m - 2 * f) +
                    0.00003 * sin(2 * m1 + 2 * f) -
                    0.00003 * sin(m1 + m + 2 * f) +
                    0.00003 * sin(m1 - m + 2 * f) -
                    0.00002 * sin(m1 - m - 2 * f) -
                    0.00002 * sin(3 * m1 + m) +
                    0.00002 * sin(4 * m1)
            PhaseType.FIRSTQUARTER, PhaseType.LASTQUARTER -> -0.62801 * sin(m1) +
                    0.17172 * e * sin(m) -
                    0.01183 * e * sin(m1 + m) +
                    0.00862 * sin(2 * m1) +
                    0.00804 * sin(2 * f) +
                    0.00454 * e * sin(m1 - m) +
                    0.00204 * e.pow(2) * sin(2 * m) -
                    0.00180 * sin(m1 - 2 * f) -
                    0.00070 * sin(m1 + 2 * f) -
                    0.00040 * sin(3 * m1) -
                    0.00034 * e * sin(2 * m1 - m) +
                    0.00032 * e * sin(m + 2 * f) +
                    0.00032 * e * sin(m - 2 * f) -
                    0.00028 * e.pow(2) * sin(m1 + 2 * m) +
                    0.00027 * e * sin(2 * m1 + m) -
                    0.00017 * sin(omega) -
                    0.00005 * sin(m1 - m - 2 * f) +
                    0.00004 * sin(2 * m1 + 2 * f) -
                    0.00004 * sin(m1 + m + 2 * f) +
                    0.00004 * sin(m1 - 2 * m) +
                    0.00003 * sin(m1 + m - 2 * f) +
                    0.00003 * sin(3 * m) +
                    0.00002 * sin(2 * m1 - 2 * f) +
                    0.00002 * sin(m1 - m + 2 * f) -
                    0.00002 * sin(3 * m1 + m)
        }

        val w = 0.00306 - 0.00038 * e * cos(m) + 0.00026 * cos(m1) - 0.00002 * cos(m1 - m) + 0.00002 * cos(m1 + m) + 0.00002 * cos(2 * f)
        val jdeCorrected = when (phaseType) {
            PhaseType.FIRSTQUARTER -> jdeCor1 + w
            PhaseType.LASTQUARTER -> jdeCor1 - w
            PhaseType.NEWMOON -> jdeCor1
            PhaseType.FULLMOON -> jdeCor1
        }

        val jdeCor2 = 0.000325 * sin(a1) +
                0.000165 * sin(a2) +
                0.000164 * sin(a3) +
                0.000126 * sin(a4) +
                0.00011 * sin(a5) +
                0.000062 * sin(a6) +
                0.00006 * sin(a7) +
                0.000056 * sin(a8) +
                0.000047 * sin(a9) +
                0.000042 * sin(a10) +
                0.00004 * sin(a11) +
                0.000037 * sin(a12) +
                0.000035 * sin(a13) +
                0.000023 * sin(a14)

        val finalJdeMoonPhaseJM = (jdeMoonPhaseJM + jdeCorrected + jdeCor2)
        
        return finalJdeMoonPhaseJM
    }

    /**
     * function to compute moon geocentric conjunction
     */
    fun moonGeoConjunction(monthOfHijri: Int, yearOfHijri: Int, deltaT: Double, conjunctionReturn: ConjunctionReturn): Double {

        var jdGeoNewMoon = 0.0
        val jdNewMoon = moonPhase(monthOfHijri, yearOfHijri, PhaseType.NEWMOON)

        val x1 = jdNewMoon - (1.0 / 24.0)
        val x2 = jdNewMoon
        val x3 = jdNewMoon + (1.0 / 24.0)

        val y1 = SunPosition.sunApparentGeocentricLongitude(x1, deltaT) - MoonPosition.moonGeocentricLongitude(x1, deltaT, PositionType.APPARENT)
        val y2 = SunPosition.sunApparentGeocentricLongitude(x2, deltaT) - MoonPosition.moonGeocentricLongitude(x2, deltaT, PositionType.APPARENT)
        val y3 = SunPosition.sunApparentGeocentricLongitude(x3, deltaT) - MoonPosition.moonGeocentricLongitude(x3, deltaT, PositionType.APPARENT)

        val a = y2 - y1
        val b = y3 - y2
        val c = b - a

        var vN0 = 0.0

        for (i in 1..2) {
            vN0 = -2 * y2 / (a + b + c * vN0)
            jdGeoNewMoon = jdNewMoon + vN0 / 24.0
        }

        val geoLon = SunPosition.sunApparentGeocentricLongitude(jdGeoNewMoon, deltaT)

        return when (conjunctionReturn) {
            ConjunctionReturn.JDCONJUNCTION -> jdGeoNewMoon
            ConjunctionReturn.LONGITUDE -> geoLon
        }
    }

    /**
     * function to compute moon topocentric conjunction
     */
    fun moonTopoConjunction(monthOfHijri: Int, yearOfHijri: Int, deltaT: Double, lon: Double, lat: Double, elev: Double, conjunctionReturn: ConjunctionReturn): Double {

        val jdNewMoon = moonPhase(monthOfHijri, yearOfHijri, PhaseType.NEWMOON)

        val x1 = jdNewMoon - (1.0 / 24.0)
        val x2 = jdNewMoon
        val x3 = jdNewMoon + (1.0 / 24.0)

        val y1Topo = SunPosition.sunTopoLongitude(x1, lon, lat, elev, deltaT) - MoonPosition.moonTopoLongitude(x1, lon, lat, elev, deltaT)
        val y2Topo = SunPosition.sunTopoLongitude(x2, lon, lat, elev, deltaT) - MoonPosition.moonTopoLongitude(x2, lon, lat, elev, deltaT)
        val y3Topo = SunPosition.sunTopoLongitude(x3, lon, lat, elev, deltaT) - MoonPosition.moonTopoLongitude(x3, lon, lat, elev, deltaT)

        var jdTopoNewMoon = 0.0
        var vN0Topo = 0.0

        val aTopo = y2Topo - y1Topo
        val bTopo = y3Topo - y2Topo
        val cTopo = bTopo - aTopo
        
        vN0Topo = -2 * y2Topo / (aTopo + bTopo + cTopo * vN0Topo)
        
        for (i in 1..2) {
            vN0Topo = -2 * y2Topo / (aTopo + bTopo + cTopo * vN0Topo)
            jdTopoNewMoon = jdNewMoon + vN0Topo / 24.0
        }

        val topoLon = SunPosition.sunTopoLongitude(jdTopoNewMoon, lon, lat, elev, deltaT)

        return when (conjunctionReturn) {
            ConjunctionReturn.JDCONJUNCTION -> jdTopoNewMoon
            ConjunctionReturn.LONGITUDE -> topoLon
        }
    }

}
