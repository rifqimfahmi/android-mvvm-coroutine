package com.rifqimfahmi.foorballapps.vo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/*
 * Created by Rifqi Mulya Fahmi on 06/12/18.
 */

@Entity(tableName = "teams", primaryKeys = ["idLeague", "idTeam"])
data class Team (
    @Json(name = "idLeague")
    var idLeague: String, // 4328
    @Json(name = "idSoccerXML")
    val idSoccerXML: String?, // 3
    @Json(name = "idTeam")
    val idTeam: String, // 133599
    @Json(name = "intFormedYear")
    val intFormedYear: String?, // 1877
    @Json(name = "intLoved")
    val intLoved: String?, // null
    @Json(name = "intStadiumCapacity")
    val intStadiumCapacity: String?, // 31700
    @Json(name = "strAlternate")
    val strAlternate: String?, // Wolverhampton Wanderers
    @Json(name = "strCountry")
    val strCountry: String?, // England
    @Json(name = "strDescriptionCN")
    val strDescriptionCN: String?, // null
    @Json(name = "strDescriptionDE")
    val strDescriptionDE: String?, // null
    @Json(name = "strDescriptionEN")
    val strDescriptionEN: String?, // Wolverhampton Wanderers Football Club Listeni/ˌwʊlvərˈhæmptən/ (commonly referred to as Wolves) is an English professional football club that represents the city of Wolverhampton in the West Midlands. The club was originally known as St. Luke's FC and was founded in 1877 and since 1889 has played at Molineux. They currently compete in the Football League Championship, the second highest tier of English football, having been promoted from League One in 2014 after a solitary season at that level.Historically, Wolves have been highly influential, most notably as being founder members of the Football League, as well as having played an instrumental role in the establishment of the European Cup, later to become the UEFA Champions League. Having won the FA Cup twice before the outbreak of the First World War, they developed into one of England's leading clubs under the management of ex-player Stan Cullis after the Second World War, going on to win the league three times and the FA Cup twice more between 1949 and 1960. It was during this time that the European Cup competition was established, after the English press declared Wolves "Champions of the World" following their victories against numerous top European and World sides in some of British football's first live televised games.Wolves have yet to match the successes of the Stan Cullis era, although, under Bill McGarry, they contested the first-ever UEFA Cup final in 1972 and won the 1974 League Cup, a trophy they lifted again six years later under John Barnwell. However, financial mismanagement in the 1980s led to the club's very existence being under threat as well as three consecutive relegations, before a revival and back-to-back promotions under manager Graham Turner and record goalscorer Steve Bull saw them finish the decade in the Second Division, winning the Football League Trophy along the way.
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
    @Json(name = "strDivision")
    val strDivision: String?, // null
    @Json(name = "strFacebook")
    val strFacebook: String?,
    @Json(name = "strGender")
    val strGender: String?, // Male
    @Json(name = "strInstagram")
    val strInstagram: String?,
    @Json(name = "strKeywords")
    val strKeywords: String?,
    @Json(name = "strLeague")
    val strLeague: String?, // English Premier League
    @Json(name = "strLocked")
    val strLocked: String?, // unlocked
    @Json(name = "strManager")
    val strManager: String?, // Nuno Espírito Santo
    @Json(name = "strRSS")
    val strRSS: String?,
    @Json(name = "strSport")
    val strSport: String?, // Soccer
    @Json(name = "strStadium")
    val strStadium: String?, // Molineux Stadium
    @Json(name = "strStadiumDescription")
    val strStadiumDescription: String?, // Molineux Stadium (/ˈmɒlɨnjuː/ mol-i-new) is a Championship football stadium situated in Whitmore Reans, Wolverhampton, England. It has been the home ground of Wolverhampton Wanderers Football Club since 1889, and has a long and illustrious history as the first 'new build' stadium in Football League history, one of the first grounds in the country to install floodlights, as well as hosting some of the first European club games in the 1950s.At the time of its multi-million pound renovation in the early 1990s, Molineux was one of the biggest and most modern stadia in England, though it has since been eclipsed by many other ground developments. The stadium has however hosted England internationals and, more recently, England under-21 internationals, as well as the first UEFA Cup Final in 1972. Although currently a 31,000 seater stadium, the record attendance at Molineux stands at 61,315.Initial plans were announced in May 2010 to rebuild two sides of the stadium by the 2014–15 season to increase capacity to around 36,000. The first stage of this project began in Summer 2011 and was completed on course for the start of the 2012–13 season. There are also provisional future plans for a longer term redevelopment of every stand that could potentially create a 50,000 capacity.
    @Json(name = "strStadiumLocation")
    val strStadiumLocation: String?, // Waterloo Road, Wolverhampton
    @Json(name = "strStadiumThumb")
    val strStadiumThumb: String?, // https://www.thesportsdb.com/images/media/team/stadium/xtvqvs1420753628.jpg
    @Json(name = "strTeam")
    val strTeam: String?, // Wolves
    @Json(name = "strTeamBadge")
    val strTeamBadge: String?, // https://www.thesportsdb.com/images/media/team/badge/qwvuqy1448811552.png
    @Json(name = "strTeamBanner")
    val strTeamBanner: String?, // https://www.thesportsdb.com/images/media/team/banner/vyxrss1462380950.jpg
    @Json(name = "strTeamFanart1")
    val strTeamFanart1: String?, // https://www.thesportsdb.com/images/media/team/fanart/p2xn8q1532295468.jpg
    @Json(name = "strTeamFanart2")
    val strTeamFanart2: String?, // https://www.thesportsdb.com/images/media/team/fanart/kzx8521532295383.jpg
    @Json(name = "strTeamFanart3")
    val strTeamFanart3: String?, // https://www.thesportsdb.com/images/media/team/fanart/3zzmow1532295430.jpg
    @Json(name = "strTeamFanart4")
    val strTeamFanart4: String?, // https://www.thesportsdb.com/images/media/team/fanart/co1ppu1532295499.jpg
    @Json(name = "strTeamJersey")
    val strTeamJersey: String?, // https://www.thesportsdb.com/images/media/team/jersey/lrq52v1508938445.png
    @Json(name = "strTeamLogo")
    val strTeamLogo: String?, // https://www.thesportsdb.com/images/media/team/logo/wdes121532295945.png
    @Json(name = "strTeamShort")
    val strTeamShort: String?, // null
    @Json(name = "strTwitter")
    val strTwitter: String?,
    @Json(name = "strWebsite")
    val strWebsite: String?, // www.wolves.co.uk/page/Welcome
    @Json(name = "strYoutube")
    val strYoutube: String?
) {

}