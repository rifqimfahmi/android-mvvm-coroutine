package com.rifqimfahmi.foorballapps.data.source

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.data.source.local.SportDao
import com.rifqimfahmi.foorballapps.data.source.local.SportDb
import com.rifqimfahmi.foorballapps.data.source.remote.ApiResponse
import com.rifqimfahmi.foorballapps.data.source.remote.NetworkBoundResource
import com.rifqimfahmi.foorballapps.data.source.remote.SportService
import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.data.source.remote.json.TeamsResponse
import com.rifqimfahmi.foorballapps.features.matches.MatchesListFragment
import com.rifqimfahmi.foorballapps.testing.OpenForTesting
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

@OpenForTesting
class SportRepository(
    private val db: SportDb,
    private val sportDao: SportDao,
    private val sportService: SportService
) {

    fun nextMatches(leagueId: String): LiveData<Resource<List<Match>>> {
        return object : NetworkBoundResource<List<Match>, SchedulesResponse>() {
            override fun saveCallResult(item: SchedulesResponse) {
                val matches = item.events
                matches?.let { matchesData ->
                    matchesData.forEach { match ->
                        match?.let {
                            match.matchType = MatchesListFragment.TYPE_NEXT_MATCH
                        }
                    }

                    db.runInTransaction {
                        sportDao.deleteNextMatches(leagueId)
                        sportDao.saveMatches(matchesData)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getNextMatch(leagueId)

            override fun shouldFetch(data: List<Match>?) = true

            override fun loadFromDb(): LiveData<List<Match>> = sportDao.getNextMatches(leagueId)

        }.asLiveData()
    }

    fun prevMatches(leagueId: String): LiveData<Resource<List<Match>>> {
        return object : NetworkBoundResource<List<Match>, SchedulesResponse>() {

            override fun saveCallResult(item: SchedulesResponse) {
                val matches = item.events
                matches?.let { matchesData ->
                    matchesData.forEach { match ->
                        match?.let {
                            match.matchType = MatchesListFragment.TYPE_PREV_MATCH
                        }
                    }

                    db.runInTransaction {
                        sportDao.deletePrevMatches(leagueId)
                        sportDao.saveMatches(matchesData)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getLastMatch(leagueId)

            override fun shouldFetch(data: List<Match>?): Boolean = true

            override fun loadFromDb(): LiveData<List<Match>> = sportDao.getPrevMatches(leagueId)

        }.asLiveData()
    }

    fun teams(leagueId: String): LiveData<Resource<List<Team>>> {
        return object : NetworkBoundResource<List<Team>, TeamsResponse>() {

            override fun saveCallResult(item: TeamsResponse) {
                item.teams?.let {
                    db.runInTransaction {
                        sportDao.saveTeams(it)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<TeamsResponse>> = sportService.getTeams(leagueId)

            override fun shouldFetch(data: List<Team>?): Boolean = true

            override fun loadFromDb(): LiveData<List<Team>> = sportDao.getTeams(leagueId)

        }.asLiveData()
    }

    fun getTeam(teamId: String): LiveData<Resource<Team>> {
        return object : NetworkBoundResource<Team, TeamsResponse>() {
            override fun saveCallResult(item: TeamsResponse) {
                item.teams?.let {
                    db.runInTransaction {
                        sportDao.saveTeams(it)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<TeamsResponse>> = sportService.getTeam(teamId)

            override fun shouldFetch(data: Team?): Boolean = data == null

            override fun loadFromDb(): LiveData<Team> = sportDao.getTeam(teamId)

        }.asLiveData()
    }

    fun getEventDetail(matchId: String): LiveData<Resource<Match>> {
        return object : NetworkBoundResource<Match, SchedulesResponse>() {
            override fun saveCallResult(item: SchedulesResponse) {
                item.events?.let{ matches ->
                    matches.forEach { match ->
                        match?.let {
                            if (match.isNextMatch()) {
                                match.matchType = MatchesListFragment.TYPE_NEXT_MATCH
                            } else {
                                match.matchType = MatchesListFragment.TYPE_PREV_MATCH
                            }
                        }
                    }

                    sportDao.saveMatches(matches)
                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = sportService.getMatchDetail(matchId)

            override fun shouldFetch(data: Match?): Boolean = data == null

            override fun loadFromDb(): LiveData<Match> = sportDao.getMatchDetail(matchId)

        }.asLiveData()
    }

    companion object {
        private var INSTANCE: SportRepository? = null

        fun getInstance(sportDb: SportDb, sportService: SportService): SportRepository =
            INSTANCE ?: synchronized(SportRepository::class.java) {
                SportRepository(sportDb, sportDb.sportDao(), sportService)
                    .also { INSTANCE = it }
            }
    }
}