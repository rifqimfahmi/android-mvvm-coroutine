package com.rifqimfahmi.foorballapps.features.matches

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.AbsentLiveData
import com.rifqimfahmi.foorballapps.util.getLeaguesId
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

class MatchesViewModel(context: Application, sportRepository: SportRepository) : AndroidViewModel(context) {

    // LiveData for league categories
    private val matchFilterId = MutableLiveData<String>()
    private val teamFilterId = MutableLiveData<String>()

    val context: Context = context.applicationContext // application Context to avoid leaks

    val nextMatches: LiveData<Resource<List<Match>>> = Transformations.switchMap(matchFilterId) { leagueId ->
        if (leagueId.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.nextMatches(leagueId)
        }
    }

    val prevMatch: LiveData<Resource<List<Match>>> = Transformations.switchMap(matchFilterId) { leagueId ->
        if (leagueId.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.prevMatches(leagueId)
        }
    }

    val teams: LiveData<Resource<List<Team>>> = Transformations.switchMap(teamFilterId) { leagueId ->
        if (leagueId.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.teams(leagueId)
        }
    }

    fun setMatchesFilterBy(position: Int) {
        matchFilterId.value = context.getLeaguesId(position)
    }

    fun setTeamFilterBy(position: Int) {
        teamFilterId.value = context.getLeaguesId(position)
    }

    fun refreshMatches() {
        matchFilterId.value?.let {
            matchFilterId.value = it
        }
    }

    fun refreshTeams() {
        teamFilterId.value?.let {
            teamFilterId.value = it
        }
    }
}