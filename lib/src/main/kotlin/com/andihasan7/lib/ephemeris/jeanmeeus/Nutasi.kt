package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.pow
import kotlin.mod

object Nutasi {
    fun deltaPsiDanEpsilon(t: Double): DoubleArray {
        val d = Math.toRadians((297.85036 + 445267.111480 * t - 0.0019142 * t.pow(2) + t.pow(3) / 189474).mod(360.0))
        val m = Math.toRadians((357.52772 + 35999.05034 * t - 0.0001603 * t.pow(2) - t.pow(3) / 300000).mod(360.0))
        val m1 = Math.toRadians((134.96298 + 477198.867398 * t + 0.0086972 * t.pow(2) + t.pow(3) / 56250).mod(360.0))
        val f = Math.toRadians((93.27191 + 483202.017538 * t - 0.0036825 * t.pow(2) + t.pow(3) / 327270).mod(360.0))
        val omega = Math.toRadians(125.04452 - 1934.136261 * t + 0.0020708 * t.pow(2) + t.pow(3) / 450000).mod(360.0)
        
        var deltaPsi = 0.0
        //(koefisien1+koefisien2*t)*Math.sin(D*d+M*m+M'*m1+F*f+OMEGA*omega)
        deltaPsi += (-171996 + -174.2 * t) * Math.sin(0 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-13187 + -1.6 * t) * Math.sin(-2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-2274 + -0.2 * t) * Math.sin(0 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (2062 + 0.2 * t) * Math.sin(0 * d + 0 * m + 0 * m1 + 0 * f + 2 * omega)
        deltaPsi += (1426 + -3.4 * t) * Math.sin(0 * d + 1 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (712 + 0.1 * t) * Math.sin(0 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-517 + 1.2 * t) * Math.sin(-2 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-386 + -0.4 * t) * Math.sin(0 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (217 + -0.5 * t) * Math.sin(-2 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (129 + 0.1 * t) * Math.sin(-2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (63 + 0.1 * t) * Math.sin(0 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-58 + -0.1 * t) * Math.sin(0 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (17 + -0.1 * t) * Math.sin(0 * d + 2 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-16 + 0.1 * t) * Math.sin(-2 * d + 2 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-301 + 0 * t) * Math.sin(0 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-158 + 0 * t) * Math.sin(-2 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (123 + 0 * t) * Math.sin(0 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (63 + 0 * t) * Math.sin(2 * d + 0 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-59 + 0 * t) * Math.sin(2 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-51 + 0 * t) * Math.sin(0 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (48 + 0 * t) * Math.sin(-2 * d + 0 * m + 2 * m1 + 0 * f + 0 * omega)
        deltaPsi += (46 + 0 * t) * Math.sin(0 * d + 0 * m + -2 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-38 + 0 * t) * Math.sin(2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-31 + 0 * t) * Math.sin(0 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (29 + 0 * t) * Math.sin(0 * d + 0 * m + 2 * m1 + 0 * f + 0 * omega)
        deltaPsi += (29 + 0 * t) * Math.sin(-2 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (26 + 0 * t) * Math.sin(0 * d + 0 * m + 0 * m1 + 2 * f + 0 * omega)
        deltaPsi += (-22 + 0 * t) * Math.sin(-2 * d + 0 * m + 0 * m1 + 2 * f + 0 * omega)
        deltaPsi += (21 + 0 * t) * Math.sin(0 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (16 + 0 * t) * Math.sin(2 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-15 + 0 * t) * Math.sin(0 * d + 1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-13 + 0 * t) * Math.sin(-2 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-12 + 0 * t) * Math.sin(0 * d + -1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (11 + 0 * t) * Math.sin(0 * d + 0 * m + 2 * m1 + -2 * f + 0 * omega)
        deltaPsi += (-10 + 0 * t) * Math.sin(2 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-8 + 0 * t) * Math.sin(2 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (7 + 0 * t) * Math.sin(0 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-7 + 0 * t) * Math.sin(-2 * d + 1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-7 + 0 * t) * Math.sin(0 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-7 + 0 * t) * Math.sin(2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (6 + 0 * t) * Math.sin(2 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (6 + 0 * t) * Math.sin(-2 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (6 + 0 * t) * Math.sin(-2 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-6 + 0 * t) * Math.sin(2 * d + 0 * m + -2 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-6 + 0 * t) * Math.sin(2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (5 + 0 * t) * Math.sin(0 * d + -1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-5 + 0 * t) * Math.sin(-2 * d + -1 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (-5 + 0 * t) * Math.sin(-2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaPsi += (-5 + 0 * t) * Math.sin(0 * d + 0 * m + 2 * m1 + 2 * f + 1 * omega)
        deltaPsi += (4 + 0 * t) * Math.sin(-2 * d + 0 * m + 2 * m1 + 0 * f + 1 * omega)
        deltaPsi += (4 + 0 * t) * Math.sin(-2 * d + 1 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaPsi += (4 + 0 * t) * Math.sin(0 * d + 0 * m + 1 * m1 + -2 * f + 0 * omega)
        deltaPsi += (-4 + 0 * t) * Math.sin(-1 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-4 + 0 * t) * Math.sin(-2 * d + 1 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-4 + 0 * t) * Math.sin(1 * d + 0 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaPsi += (3 + 0 * t) * Math.sin(0 * d + 0 * m + 1 * m1 + 2 * f + 0 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(0 * d + 0 * m + -2 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(-1 * d + -1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(0 * d + 1 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(0 * d + -1 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(2 * d + -1 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(0 * d + 0 * m + 3 * m1 + 2 * f + 2 * omega)
        deltaPsi += (-3 + 0 * t) * Math.sin(2 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaPsi /= 10000.0
        val deltaPsi_d = deltaPsi / 3600
        val u = t / 100
        val epsilonZero =
            23 + 26.0 / 60 + 21.448 / 3600 + (-4680.93 * u - 1.55 * u.pow(2) + 1999.25 * u.pow(3) - 51.38 * u.pow(4) - 249.67 * u.pow(5) - 39.05 * u.pow(6) + 7.12 * u.pow(7) + 27.87 * u.pow(8) + 5.79 * u.pow(9) + 2.45 * u.pow(10)) / 3600
        var deltaEpsilon = 0.0
        deltaEpsilon += (92025 + 8.9 * t) * Math.cos(0 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (5736 + -3.1 * t) * Math.cos(-2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (977 + -0.5 * t) * Math.cos(0 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-895 + 0.5 * t) * Math.cos(0 * d + 0 * m + 0 * m1 + 0 * f + 2 * omega)
        deltaEpsilon += (54 + -0.1 * t) * Math.cos(0 * d + 1 * m + 0 * m1 + 0 * f + 0 * omega)
        deltaEpsilon += (224 + -0.6 * t) * Math.cos(-2 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (129 + -0.1 * t) * Math.cos(0 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-95 + 0.3 * t) * Math.cos(-2 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-7 + 0 * t) * Math.cos(0 * d + 0 * m + 1 * m1 + 0 * f + 0 * omega)
        deltaEpsilon += (200 + 0 * t) * Math.cos(0 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-70 + 0 * t) * Math.cos(-2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-53 + 0 * t) * Math.cos(0 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-33 + 0 * t) * Math.cos(0 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (26 + 0 * t) * Math.cos(2 * d + 0 * m + -1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (32 + 0 * t) * Math.cos(0 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (27 + 0 * t) * Math.cos(0 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-24 + 0 * t) * Math.cos(0 * d + 0 * m + -2 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (16 + 0 * t) * Math.cos(2 * d + 0 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (13 + 0 * t) * Math.cos(0 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-12 + 0 * t) * Math.cos(-2 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-10 + 0 * t) * Math.cos(0 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-8 + 0 * t) * Math.cos(2 * d + 0 * m + -1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (7 + 0 * t) * Math.cos(-2 * d + -2 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (9 + 0 * t) * Math.cos(0 * d + 1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (7 + 0 * t) * Math.cos(-2 * d + 0 * m + 1 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (6 + 0 * t) * Math.cos(0 * d + -1 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (5 + 0 * t) * Math.cos(2 * d + 0 * m + -1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(2 * d + 0 * m + 1 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-3 + 0 * t) * Math.cos(0 * d + 1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(0 * d + -1 * m + 0 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(2 * d + 0 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (-3 + 0 * t) * Math.cos(-2 * d + 0 * m + 2 * m1 + 2 * f + 2 * omega)
        deltaEpsilon += (-3 + 0 * t) * Math.cos(-2 * d + 0 * m + 1 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(2 * d + 0 * m + -2 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(-2 * d + -1 * m + 0 * m1 + 2 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(-2 * d + 0 * m + 0 * m1 + 0 * f + 1 * omega)
        deltaEpsilon += (3 + 0 * t) * Math.cos(0 * d + 0 * m + 2 * m1 + 2 * f + 1 * omega)
        deltaEpsilon /= 10000.0
        val deltaEpsilon_d = deltaEpsilon / 3600
        val epsilon = epsilonZero + deltaEpsilon_d
        return doubleArrayOf(0.0, deltaPsi, deltaPsi_d, u, epsilonZero, deltaEpsilon, epsilon)
    }
}