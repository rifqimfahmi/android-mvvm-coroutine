package com.rifqimfahmi.foorballapps.data.source.remote

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.data.source.remote.json.PlayersResponse
import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.data.source.remote.json.SearchSchedulesResponse
import com.rifqimfahmi.foorballapps.data.source.remote.json.TeamsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

interface SportService {

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String) : LiveData<ApiResponse<SchedulesResponse>>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: String) : LiveData<ApiResponse<SchedulesResponse>>

    @GET("lookup_all_teams.php")
    fun getTeams(@Query("id") leagueId: String) : LiveData<ApiResponse<TeamsResponse>>

    @GET("lookup_all_players.php")
    fun getPlayers(@Query("id") teamId: String) : LiveData<ApiResponse<PlayersResponse>>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") teamId: String) : LiveData<ApiResponse<TeamsResponse>>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String) : LiveData<ApiResponse<SchedulesResponse>>

    @GET("lookupplayer.php")
    fun getPlayer(@Query("id") playerId: String): LiveData<ApiResponse<PlayersResponse>>

    @GET("searchevents.php")
    fun searchMatch(@Query("e") query: String): LiveData<ApiResponse<SearchSchedulesResponse>>

//    @GET("lookupteam.php/")
//    fun lookupTeam(@Query("id") id: String) : LiveData<ApiResponse<TeamLookupResponse>>
}