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

package com.andihasan7.lib.ephemeris.jeanmeeus

import kotlin.test.Test
import com.andihasan7.lib.ephemeris.jeanmeeus.convertutil.ConvertUtil
import com.andihasan7.lib.ephemeris.jeanmeeus.moonsighting.MoonSightingJM

class MoonSightingTest {
    
    
    @Test
    fun moonSightingTest() {
		
		val ms = MoonSightingJM(
            monthOfHijri = 12,
            yearOfHijri = 1447,
            longitude = 111.46, // 98.67383333, // 111.461388888889,
            latitude = -7.36, // 3.589666667, //-7.367222222222,
            elevation = 149.0, //  128.0,
            timeZone = 7.0,
            addDate = 0,
            deltaTMode = 0,
            customDeltaT = 69.184
        )

        val jdNewMoon = ms.jdGeoNewMoonCor
        val deltaT = ms.deltaT
        val dateNewMoonGeo = ms.dateNewMoonGeo
        val dateNewMoonTopo = ms.dateNewMoonTopo
        val dateNewMoonAA = ms.dateNewMoonAA
        val hourNewMoonGeo = ms.hourGeoNewMoon
        val hourNewMoonGeoHMS = ms.hourGeoNewMoonHMS
        val hourNewMoonTopo = ms.hourTopoNewMoon
        val hourNewMoonTopoHMS = ms.hourTopoNewMoonHMS
        val hourNewMoonAA = ms.hourGeoNewMoonAA
        val hourNewMoonAAHMS = ms.hourGeoNewMoonAAHMS
        val utcHourNewMoonGeo = ms.utcGeoHourNewMoon
        val utcHourNewMoonGeoHMS = ms.utcGeoHourNewMoonHMS
        val utcHourNewMoonTopo = ms.utcTopoHourNewMoon
        val utcHourNewMoonTopoHMS = ms.utcTopoHourNewMoonHMS
        val dayNewMoonGeo = ms.dayNewMoonGeo
        val pasaranNewMoonGeo = ms.pasaranNewMoonGeo
        val dateIntNM = ms.dateNMInt
        val monthIntNM = ms.monthNMInt
        val monthNMName = ms.monthNMName
        val yearIntNM = ms.yearNMInt
        val lonGeoNewMoon = ms.lonGeoNewMoon
        val lonGeoNewMoonDMS = ms.lonGeoNewMoonDMS
        val lonTopoNewMoon = ms.lonTopoNewMoon
        val lonTopoNewMoonDMS = ms.lonTopoNewMoonDMS
        val maghribNM = ms.maghribLocalDateNewMoon ?: 0.0
        val maghribNMHMS = ms.maghribLocalDateNewMoonHMS
        val moonGeoAltitudeDMS = ms.moonGeoAltitudeDMS
        val moonAirlessTopoAltUpperDMS = ms.moonAirlessTopoAltitudeUpperLimbDMS
        val moonAirlessTopoAltCenterDMS = ms.moonAirlessTopoAltitudeCenterLimbDMS
        val moonAirlessTopoAltLowerDMS = ms.moonAirlessTopoAltitudeLowerLimbDMS
        val moonAppaTopoAltUpperDMS = ms.moonAppaTopoAltUpperLimbDMS
        val moonAppaTopoAltCenterDMS = ms.moonAppaTopoAltCenterLimbDMS
        val moonAppaTopoAltLowerDMS = ms.moonAppaTopoAltLowerLimbDMS
        val moonObservTopoAltUpperDMS = ms.moonObservedTopoAltUpperLimbDMS
        val moonObservTopoAltCenterDMS = ms.moonObserrvedTopoAltCenterLimbDMS
        val moonObservTopoAltLowerDMS = ms.moonObservedTopoAltLowerLimbDMS
        val sunTopoLon = ms.sunTopoLongitude
        val sunTopoLonDMS = ms.sunTopoLongitudeDMS
        val sunTopoLat = ms.sunTopoLatitude
        val sunTopoLatDMS = ms.sunTopoLatitudeDMS
        val moonTopoLonDMS = ms.moonTopoLongitudeDMS
        val moonTopoLatDMS = ms.moonTopoLatitudeDMS
        val sunTopoRA = ms.sunTopoRightAscension
        val moonTopoRA = ms.moonTopoRightAscension
        val sunTopoRAHour = ms.sunTopoRightAscensionHMS
        val moonTopoRAHour = ms.moonTopoRightAscensionHMS
        val sunTopoDeclination = ms.sunTopoDeclination
        val moonTopoDeclination = ms.moonTopoDeclinationDMS
        val sunTopoAz = ms.sunTopoAzimuth
        val sunTopoAlt = ms.sunTopoAltitude
        val moonTopoAzDMS = ms.moonTopoAzimuthDMS
        val moonTopoIlluminatedPercent = ms.moonTopoIlluminatedPercent2
        val moonTopoSemidiameterDMS = ms.moonTopoSemidiameterDMS
        val moonSunGeoElongationDMS = ms.moonSunGeoElongationDMS
        val moonSunTopoElongationDMS = ms.moonSunTopoElongationDMS
        val moonGeoDistanceKM = ms.moonGeoDistanceKM2
        
        val diffRASunMoon = ms.diffRASunMoon
        val hilalDuration = ms.hilalDurationOld
        val hilalDurationTaqrib = ms.hilalDurationTaqribi
        val hilalDurationTaqribHMS = ms.hilalDurationTaqribiHMS
        val moonSet = ms.moonSet
        val moonAge = ms.moonAge
        val nurulHilal = ms.nurulHilal
        val mrg = ms.mrg
        val mrgString = ms.mrgString
        val isVisibleNeoMabims = ms.isVisibleIRNU
        
        val crecentWidth = ms.crecentWidthTopo
        val moonTopoPosition = ms.moonTopoPosition
        val moonTopoPositionString = ms.moonTopoPositionString
        //val moonDuration = ms.moonDuration
        val moonBestTime = ms.moonBestTime
        val qOdeh = ms.qOdeh3
        val moonHorizontalParallaxDMS = ms.moonHorizontalParallaxDMS
        val moonTopoAzimuthSetDMS = ms.moonTopoAzimuthSetDMS
        val predictionNeoMabims = ms.predictionIRNU

        val moonSetDiffAR = ms.moonSetDiffAR
        val moonSetDiffARHMS = ms.moonSetDiffARHMS

        // mencari lama hilal algoritma ephemeris kemenag
        val sunApparentGeoDeclination = ms.sunApparentGeoDeclination
        val sunApparentGeoSemidiameter = ms.sunApparentGeoSemidiameter
        val ho = ms.ho
        val to = ms.to
        val tc = ms.tc
        val nf = ms.nf
        val pnf = ms.pnf
        val sbsh = ms.sbsh
        val sbs = ms.sbs
        val hilalDurationEphe = ms.hilalDurationEphe
        val moonSetEphe = ms.moonSetEphe
        
        
        println("jd astronomical algorithm: ${ms.jdNewMoonAstronomicalAlgorithm}")
        println("New Moon/Ijtima' AA: $dateNewMoonAA, $hourNewMoonAAHMS LT")
        println("")
        println("JD New Moon/Ijtima: $jdNewMoon")
        println("JD New Moon with iteration: ${ms.jdGhurubSyamsPlus}")
        println("")
        println("day pasaran: $dayNewMoonGeo $pasaranNewMoonGeo")
        println("month name: $monthNMName")
        println("")
        println("New Moon/Ijtima' geo : $dateNewMoonGeo, ${ConvertUtil.toTimeFullRound2(hourNewMoonGeo ?: 0.0)} LT, ${ConvertUtil.toTimeFullRound2(utcHourNewMoonGeo ?: 0.0)} UTC")
        println("New Moon/Ijtima' topo: $dateNewMoonTopo, ${ConvertUtil.toTimeFullRound2(hourNewMoonTopo ?: 0.0)} LT, ${ConvertUtil.toTimeFullRound2(utcHourNewMoonTopo ?: 0.0)} UTC")
        println("deltaT: $deltaT, ${ms.deltaT2}")
        println("Lon Geo New Moon : $lonGeoNewMoon, $lonGeoNewMoonDMS")
        println("Lon Topo New Moon: $lonTopoNewMoon, $lonTopoNewMoonDMS")
        println("")
        
        println("date month year NM: $dateIntNM $monthIntNM $yearIntNM")
        println("maghrib NM: $maghribNM, $maghribNMHMS LT")
        println("")
        println("moon geo/hakiki alt: $moonGeoAltitudeDMS")
        println("sun topo alt: $sunTopoAlt, ${ms.sunTopoAltitudeDMS}")
        println("")
        println("T. moon airless topo alt upper: $moonAirlessTopoAltUpperDMS")
        println("moon airless topo alt center  : $moonAirlessTopoAltCenterDMS")
        println("moon airless topo alt lower   : $moonAirlessTopoAltLowerDMS")
        println("")
        println("moon appa topo alt upper : $moonAppaTopoAltUpperDMS")
        println("moon appa topo alt center: $moonAppaTopoAltCenterDMS")
        println("moon appa topo alt lower : $moonAppaTopoAltLowerDMS")
        println("")
        println("moon observed topo alt upper : $moonObservTopoAltUpperDMS")
        println("moon observed topo alt center: $moonObservTopoAltCenterDMS")
        println("moon observed topo alt lower : $moonObservTopoAltLowerDMS")
        println("")
        println("sun topo longitude: $sunTopoLon, $sunTopoLonDMS")
        println("sun topo latitude : $sunTopoLat, $sunTopoLatDMS")
        println("moon topo lon     : $moonTopoLonDMS")
        println("moon topo lat     : $moonTopoLatDMS")
        println("")
        println("Sun RA      : $sunTopoRA, ${ms.sunTopoRightAscensionDMS}")
        println("Moon RA     : $moonTopoRA, ${ms.moonTopoRightAscensionDMS}")
        println("Sun RA Hour : $sunTopoRAHour, ${ms.sunTopoRightAscensionHMS}")
        println("Moon RA Hour: $moonTopoRAHour, ${ms.moonTopoRightAscensionHMS}")
        println("")
        println("sun topo dec : $sunTopoDeclination, ${ms.sunTopoDeclinationDMS}")
        println("moon topo dec: $moonTopoDeclination")
        println("sun topo az  : $sunTopoAz, ${ms.sunTopoAzimuthDMS}")
        println("moon topo az : $moonTopoAzDMS")
        println("")
        println("moon topo illuminated : $moonTopoIlluminatedPercent %")
        println("moon topo semidiameter: $moonTopoSemidiameterDMS")
        println("moon geo elongation   : $moonSunGeoElongationDMS")
        println("moon topo elongation  : $moonSunTopoElongationDMS")
        println("")
        println("diff RA: $diffRASunMoon, ${ConvertUtil.toDegreeFullRound2(diffRASunMoon)}")
        println("")
        println("hilal duration taqribi (hc x 4 m / 15): $hilalDurationTaqribHMS")
        println("hilal duration diff ARm - ARs / 15    : ${ms.hilalDurationOldHMS}")
        println("hilal duration ephe kemenag           : ${ConvertUtil.toCounterHHMMSS2(hilalDurationEphe)}")
        println("")
        println("moon set taqribi (hc x 4 m / 15)      : ${ConvertUtil.toTimeFullRound2(ms.moonSetTaqribi)}")
        println("moon set As Al Meeus (AA)             : ${ms.moonSetHMS}")
        println("moon set LH = ARm - ARc / 15          : $moonSetDiffARHMS")
        println("moon set ephe kemenag                 : ${ConvertUtil.toTimeFullRound2(moonSetEphe)}")
        println("")
        println("")
        println("moon age: $moonAge, ${ms.moonAgeHMS}")
        println("")
        println("crecent width : $crecentWidth, ${ms.crecentWidthTopoDMS}")
        println("moon topo azimuth set: $moonTopoAzimuthSetDMS")
        // println("moon duration muktsul: $moonDuration, ${ConvertUtil.toDegreeFullRound2(moonDuration)}")
        println("moon best time: $moonBestTime, ${ms.moonBestTimeHMS}")
        println("range q Odeh: $qOdeh")
        println("moon position : $moonTopoPosition, ${ms.moonTopoPositionDMS}")
        println("moon position string: $moonTopoPositionString")
        println("prediction IRNU/neo mabims: $predictionNeoMabims")
        println("is visible: $isVisibleNeoMabims")
        println("${ms.dayOfIRNUPrediction} ${ms.pasaranOfIRNUPrediction}, ${ms.dateOfIRNUPrediction} ${ms.monthOfIRNUPredictionString} ${ms.yearOfIRNUPrediction}")
        println("")
        println("moon eq horizontal parallax: $moonHorizontalParallaxDMS")
        println("moon geo distance km: $moonGeoDistanceKM")
        println("nurul hilal: $nurulHilal")
        println("mrg: $mrg, $mrgString")
        println("tHilal: ${ms.tHilal} | ${ConvertUtil.toDegreeFullRound2(ms.tHilal)}")
        println("moonSunElo: ${ms.moonSunElo} | ${ConvertUtil.toDegreeFullRound2(ms.moonSunElo)}")
        println("")
        println("sunApparentGeoDeclination: ${ConvertUtil.toDegreeFullRound2(sunApparentGeoDeclination)}")
        println("sunApparentGeoSemidiameter: ${ConvertUtil.toDegreeFullRound2(sunApparentGeoSemidiameter)}")
        println("ho: ${ConvertUtil.toDegreeFullRound2(ho)}")
        println("to: ${ConvertUtil.toDegreeFullRound2(to)}")
        println("tc: ${ConvertUtil.toDegreeFullRound2(tc)}")
        println("nf: ${ConvertUtil.toDegreeFullRound2(nf)} | $nf")
        println("pnf: ${ConvertUtil.toDegreeFullRound2(pnf)}")
        println("sbsh: $sbsh | ${ConvertUtil.toDegreeFullRound2(sbsh)}")
        println("sbs: $sbs | ${ConvertUtil.toDegreeFullRound2(sbs)}")
        println("hilalDurationEphe: ${ConvertUtil.toTimeFullRound2(hilalDurationEphe)}")
        println("moonSetEphe: ${ConvertUtil.toTimeFullRound2(moonSetEphe)}")
        println("")
	}
	
}
