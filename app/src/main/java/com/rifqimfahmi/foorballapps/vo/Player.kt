package com.rifqimfahmi.foorballapps.vo

import androidx.room.Entity
import com.squareup.moshi.Json

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

@Entity(tableName = "players", primaryKeys = ["idPlayer", "idTeam"])
data class Player(
    @Json(name = "idPlayer")
    val idPlayer: String, // 34162106
    @Json(name = "idTeam")
    val idTeam: String, // 133604
    @Json(name = "dateBorn")
    val dateBorn: String?, // 1984-01-16
    @Json(name = "dateSigned")
    val dateSigned: String?, // null
    @Json(name = "idPlayerManager")
    val idPlayerManager: String?, // null
    @Json(name = "idSoccerXML")
    val idSoccerXML: String?, // 9
    @Json(name = "intLoved")
    val intLoved: String?, // 0
    @Json(name = "intSoccerXMLTeamID")
    val intSoccerXMLTeamID: String?, // null
    @Json(name = "strBanner")
    val strBanner: String?, // null
    @Json(name = "strBirthLocation")
    val strBirthLocation: String?, // Adligenswil, Switzerland
    @Json(name = "strCollege")
    val strCollege: String?, // null
    @Json(name = "strCutout")
    val strCutout: String?, // null
    @Json(name = "strDescriptionCN")
    val strDescriptionCN: String?, // null
    @Json(name = "strDescriptionDE")
    val strDescriptionDE: String?, // null
    @Json(name = "strDescriptionEN")
    val strDescriptionEN: String?, // Stephan Lichtsteiner (born 16 January 1984) is a Swiss professional footballer who plays for English club Arsenal and the Switzerland national team. An attacking right-back or wing-back, he is known for his energetic runs down the right wing, as well as his stamina and athleticism, which earned him the nicknames "Forrest Gump" and "The Swiss Express".He began his professional career with Grasshopper Zürich, winning a league title in 2002–03, and moved to Lille in 2005, helping the French club to Champions League qualification in his first season with the team. In 2008, he joined Italian club Lazio, and won both the Coppa Italia and Supercoppa Italiana the following year. In 2011, he signed for Juventus for a fee of €10 million. He played 257 total games for them over seven years and won 14 trophies, including Serie A in each of his seasons.A full international since 2006, Lichtsteiner has earned over 100 caps for Switzerland, making him their fourth most capped player of all time. He has represented his country at two UEFA European Championships and three FIFA World Cups.
    @Json(name = "strDescriptionES")
    val strDescriptionES: String?, // null
    @Json(name = "strDescriptionFR")
    val strDescriptionFR: String?, // null
    @Json(name = "strDescriptionHU")
    val strDescriptionHU: String?, // null
    @Json(name = "strDescriptionIL")
    val strDescriptionIL: String?, // null
    @Json(name = "strDescriptionIT")
    val strDescriptionIT: String?, // null
    @Json(name = "strDescriptionJP")
    val strDescriptionJP: String?, // null
    @Json(name = "strDescriptionNL")
    val strDescriptionNL: String?, // null
    @Json(name = "strDescriptionNO")
    val strDescriptionNO: String?, // null
    @Json(name = "strDescriptionPL")
    val strDescriptionPL: String?, // null
    @Json(name = "strDescriptionPT")
    val strDescriptionPT: String?, // null
    @Json(name = "strDescriptionRU")
    val strDescriptionRU: String?, // null
    @Json(name = "strDescriptionSE")
    val strDescriptionSE: String?, // null
    @Json(name = "strFacebook")
    val strFacebook: String?,
    @Json(name = "strFanart1")
    val strFanart1: String?, // null
    @Json(name = "strFanart2")
    val strFanart2: String?, // null
    @Json(name = "strFanart3")
    val strFanart3: String?, // null
    @Json(name = "strFanart4")
    val strFanart4: String?, // null
    @Json(name = "strGender")
    val strGender: String?, // Male
    @Json(name = "strHeight")
    val strHeight: String?, // 1.82
    @Json(name = "strInstagram")
    val strInstagram: String?,
    @Json(name = "strLocked")
    val strLocked: String?, // unlocked
    @Json(name = "strNationality")
    val strNationality: String?, // Switzerland
    @Json(name = "strPlayer")
    val strPlayer: String?, // Stephan Lichtsteiner
    @Json(name = "strPosition")
    val strPosition: String?, // Defender
    @Json(name = "strSigning")
    val strSigning: String?, // Free
    @Json(name = "strSport")
    val strSport: String?, // Soccer
    @Json(name = "strTeam")
    val strTeam: String?, // Arsenal
    @Json(name = "strThumb")
    val strThumb: String?, // https://www.thesportsdb.com/images/media/player/thumb/0c3ped1515956553.jpg
    @Json(name = "strTwitter")
    val strTwitter: String?,
    @Json(name = "strWage")
    val strWage: String?,
    @Json(name = "strWebsite")
    val strWebsite: String?,
    @Json(name = "strWeight")
    val strWeight: String?,
    @Json(name = "strYoutube")
    val strYoutube: String?
) {

    fun getWeight(): String = formatNumber(strWeight)
    fun getHeight(): String = formatNumber(strHeight)

    private fun formatNumber(number: String?) : String {
        if (number.isNullOrBlank()) return "-"
        val regex = """[\d]*\.[\d]*""".toRegex().find(number)?.value
        return regex ?: "-"
    }
}