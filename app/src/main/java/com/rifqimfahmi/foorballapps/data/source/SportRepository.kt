package com.rifqimfahmi.foorballapps.data.source

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.data.source.local.SportDao
import com.rifqimfahmi.foorballapps.data.source.remote.ApiResponse
import com.rifqimfahmi.foorballapps.data.source.remote.NetworkBoundResource
import com.rifqimfahmi.foorballapps.data.source.remote.SportService
import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.util.AbsentLiveData
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class SportRepository (
    val sportDb: SportDao,
    val sportService: SportService
) {

    fun nextMatches(leagueId: String) : LiveData<Resource<List<Match>>> {
        return object : NetworkBoundResource<List<Match> ,SchedulesResponse>() {
            override fun onFetchFailed() {
            }

            override fun saveCallResult(item: SchedulesResponse) {
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getNextMatch()


            override fun shouldFetch(data: List<Match>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Match>> {
                return AbsentLiveData.create()
            }

        }.asLiveData()
    }

    fun lastMatch(leagueId: String) : LiveData<Resource<List<Match>>> {
        return object : NetworkBoundResource<List<Match> ,SchedulesResponse>() {
            override fun onFetchFailed() {
            }

            override fun saveCallResult(item: SchedulesResponse) {
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getNextMatch()


            override fun shouldFetch(data: List<Match>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Match>> {
                return AbsentLiveData.create()
            }

        }.asLiveData()
    }

    companion object {
        private var INSTANCE: SportRepository? = null

        fun getInstance(sportDb: SportDao, sportService: SportService) : SportRepository =
                INSTANCE ?: synchronized(SportRepository::class.java) {
                    SportRepository(sportDb, sportService)
                        .also { INSTANCE = it }
                }
    }
}