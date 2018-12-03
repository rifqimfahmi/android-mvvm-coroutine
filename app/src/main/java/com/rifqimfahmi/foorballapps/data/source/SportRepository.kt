package com.rifqimfahmi.foorballapps.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rifqimfahmi.foorballapps.data.source.local.SportDao
import com.rifqimfahmi.foorballapps.data.source.local.SportDb
import com.rifqimfahmi.foorballapps.data.source.remote.ApiResponse
import com.rifqimfahmi.foorballapps.data.source.remote.NetworkBoundResource
import com.rifqimfahmi.foorballapps.data.source.remote.SportService
import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.features.matches.MatchesListFragment
import com.rifqimfahmi.foorballapps.util.AbsentLiveData
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class SportRepository (
    val db: SportDb,
    val sportDao: SportDao,
    val sportService: SportService
) {

    fun nextMatches(leagueId: String) : LiveData<Resource<List<Match>>> {
        return object : NetworkBoundResource<List<Match> ,SchedulesResponse>() {
            override fun saveCallResult(item: SchedulesResponse) {
                val matches = item.events
                matches?.forEach { match ->
                    match?.let {
                        match.matchType = MatchesListFragment.TYPE_NEXT_MATCH
                    }
                }

                db.runInTransaction {
                    sportDao.deleteNextMatches(leagueId)
                    sportDao.saveMatches(matches)
                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getNextMatch(leagueId)

            override fun shouldFetch(data: List<Match>?) = true

            override fun loadFromDb(): LiveData<List<Match>> = sportDao.getNextMatches(leagueId)

        }.asLiveData()
    }

    fun lastMatch(leagueId: String) : LiveData<Resource<List<Match>>> {
        return object : NetworkBoundResource<List<Match> ,SchedulesResponse>() {

            override fun saveCallResult(item: SchedulesResponse) {
                val matches = item.events
                matches?.forEach { match ->
                    match?.let {
                        match.matchType = MatchesListFragment.TYPE_PREV_MATCH
                    }
                }

                db.runInTransaction {
                    sportDao.deletePrevMatches(leagueId)
                    sportDao.saveMatches(matches)
                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getLastMatch(leagueId)

            override fun shouldFetch(data: List<Match>?): Boolean = true

            override fun loadFromDb(): LiveData<List<Match>> = sportDao.getPrevMatches(leagueId)

        }.asLiveData()
    }

    companion object {
        private var INSTANCE: SportRepository? = null

        fun getInstance(sportDb: SportDb, sportService: SportService) : SportRepository =
                INSTANCE ?: synchronized(SportRepository::class.java) {
                    SportRepository(sportDb, sportDb.sportDao(), sportService)
                        .also { INSTANCE = it }
                }
    }
}