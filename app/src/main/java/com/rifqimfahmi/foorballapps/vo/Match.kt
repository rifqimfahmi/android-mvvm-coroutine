package com.rifqimfahmi.foorballapps.vo

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.text.SimpleDateFormat

/*
 * Created by Rifqi Mulya Fahmi on 26/11/18.
 */

@Entity(tableName = "matches")
data class Match (
    @Json(name = "dateEvent")
    val dateEvent: String?, // 2018-10-22
    @Json(name = "idAwayTeam")
    val idAwayTeam: String?, // 133626
    @Json(name = "idEvent")
    val idEvent: String?, // 576558
    @Json(name = "idHomeTeam")
    val idHomeTeam: String?, // 133604
    @Json(name = "idLeague")
    val idLeague: String?, // 4328
    @Json(name = "idSoccerXML")
    val idSoccerXML: String?, // 389943
    @Json(name = "intAwayScore")
    val intAwayScore: String?, // 1
    @Json(name = "intAwayShots")
    val intAwayShots: String?, // 2
    @Json(name = "intHomeScore")
    val intHomeScore: String?, // 3
    @Json(name = "intHomeShots")
    val intHomeShots: String?, // 6
    @Json(name = "intRound")
    val intRound: String?, // 9
    @Json(name = "intSpectators")
    val intSpectators: String?, // 0
    @Json(name = "strAwayFormation")
    val strAwayFormation: String?,
    @Json(name = "strAwayGoalDetails")
    val strAwayGoalDetails: String?, // 31':Hector Bellerin (Own goal);
    @Json(name = "strAwayLineupDefense")
    val strAwayLineupDefense: String?, // Daniel Amartey; Jonny Evans; Harry Maguire; Ben Chilwell;
    @Json(name = "strAwayLineupForward")
    val strAwayLineupForward: String?, // Jamie Vardy;
    @Json(name = "strAwayLineupGoalkeeper")
    val strAwayLineupGoalkeeper: String?, // Kasper Schmeichel;
    @Json(name = "strAwayLineupMidfield")
    val strAwayLineupMidfield: String?, // Onyinye Ndidi; Nampalys Mendy; Ricardo Pereira; James Maddison; Marc Albrighton;
    @Json(name = "strAwayLineupSubstitutes")
    val strAwayLineupSubstitutes: String?, // Danny Ward; Caglar Soyuncu; Christian Fuchs; Marc Albrighton; Vicente Iborra; Rachid Ghezzal; Shinji Okazaki;
    @Json(name = "strAwayRedCards")
    val strAwayRedCards: String?,
    @Json(name = "strAwayTeam")
    val strAwayTeam: String?, // Leicester
    @Json(name = "strAwayYellowCards")
    val strAwayYellowCards: String?, // 65':Marc Albrighton;65':Ricardo Pereira;
    @Json(name = "strBanner")
    val strBanner: String?, // null
    @Json(name = "strCircuit")
    val strCircuit: String?, // null
    @Json(name = "strCity")
    val strCity: String?, // null
    @Json(name = "strCountry")
    val strCountry: String?, // null
    @Json(name = "strDate")
    val strDate: String?, // 22/10/18
    @Json(name = "strDescriptionEN")
    val strDescriptionEN: String?, // null
    @Json(name = "strEvent")
    val strEvent: String?, // Arsenal vs Leicester
    @Json(name = "strFanart")
    val strFanart: String?, // null
    @Json(name = "strFilename")
    val strFilename: String?, // English Premier League 2018-10-20 Arsenal vs Leicester
    @Json(name = "strHomeFormation")
    val strHomeFormation: String?,
    @Json(name = "strHomeGoalDetails")
    val strHomeGoalDetails: String?, // 45':Mesut Oezil;63':Pierre-Emerick Aubameyang;66':Pierre-Emerick Aubameyang;
    @Json(name = "strHomeLineupDefense")
    val strHomeLineupDefense: String?, // Hector Bellerin; Shkodran Mustafi; Rob Holding; Nacho Monreal;
    @Json(name = "strHomeLineupForward")
    val strHomeLineupForward: String?, // Alexandre Lacazette;
    @Json(name = "strHomeLineupGoalkeeper")
    val strHomeLineupGoalkeeper: String?, // Bernd Leno;
    @Json(name = "strHomeLineupMidfield")
    val strHomeLineupMidfield: String?, // Lucas Torreira; Granit Xhaka; Mesut Oezil; Aaron Ramsey; Pierre-Emerick Aubameyang;
    @Json(name = "strHomeLineupSubstitutes")
    val strHomeLineupSubstitutes: String?, // Emiliano Martinez; Matteo Guendouzi; Emile Smith-Rowe; Aaron Ramsey; Danny Welbeck; Pierre-Emerick Aubameyang; Zech Medley;
    @Json(name = "strHomeRedCards")
    val strHomeRedCards: String?,
    @Json(name = "strHomeTeam")
    val strHomeTeam: String?, // Arsenal
    @Json(name = "strHomeYellowCards")
    val strHomeYellowCards: String?, // 17':Rob Holding;35':Granit Xhaka;
    @Json(name = "strLeague")
    val strLeague: String?, // English Premier League
    @Json(name = "strLocked")
    val strLocked: String?, // unlocked
    @Json(name = "strMap")
    val strMap: String?, // null
    @Json(name = "strPoster")
    val strPoster: String?, // null
    @Json(name = "strResult")
    val strResult: String?, // null
    @Json(name = "strSeason")
    val strSeason: String?, // 1819
    @Json(name = "strSport")
    val strSport: String?, // Soccer
    @Json(name = "strTVStation")
    val strTVStation: String?, // null
    @Json(name = "strThumb")
    val strThumb: String?, // null
    @Json(name = "strTime")
    val strTime: String? // 19:00:00+00:00
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var matchType: String? = null

    @SuppressLint("SimpleDateFormat")
    fun getDate() : String {
        dateEvent?.let {
            val pattern = "EEE, d MMM yyyy"
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = dateFormat.parse(it)
            dateFormat.applyPattern(pattern)
            return dateFormat.format(date)
        }
        return ""
    }

}