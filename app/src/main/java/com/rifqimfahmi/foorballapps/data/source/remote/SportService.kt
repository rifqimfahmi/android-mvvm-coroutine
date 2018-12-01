package com.rifqimfahmi.foorballapps.data.source.remote

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

interface SportService {

    @GET("eventspastleague.php?id=4328/")
    fun getLastMatch() : LiveData<ApiResponse<SchedulesResponse>>

    @GET("eventsnextleague.php?id=4328/")
    fun getNextMatch() : LiveData<ApiResponse<SchedulesResponse>>

    @GET("lookupevent.php/")
    fun getEventDetail(@Query("id") id: String) : LiveData<ApiResponse<SchedulesResponse>>

//    @GET("lookupteam.php/")
//    fun lookupTeam(@Query("id") id: String) : LiveData<ApiResponse<TeamLookupResponse>>
}