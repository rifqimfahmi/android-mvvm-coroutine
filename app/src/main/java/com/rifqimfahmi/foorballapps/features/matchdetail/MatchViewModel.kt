package com.rifqimfahmi.foorballapps.features.matchdetail

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.AbsentLiveData
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 07/12/18.
 */
 
class MatchViewModel(ctx: Application, sportRepository: SportRepository) : AndroidViewModel(ctx) {

    private val context: Context = ctx.applicationContext

    private val idHomeTeam = MutableLiveData<String>()
    private val idAwayTeam = MutableLiveData<String>()
    private val idEvent = MutableLiveData<String>()

    val homeTeam : LiveData<Resource<Team>> = Transformations.switchMap(idHomeTeam) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.getTeam(id)
        }
    }

    val awayTeam : LiveData<Resource<Team>> = Transformations.switchMap(idAwayTeam) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.getTeam(id)
        }
    }

    val matchDetail : LiveData<Resource<Match>> = Transformations.switchMap(idEvent) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.getEventDetail(id)
        }
    }

    fun initData(idEvent: String, idHomeTeam: String, idAwayTeam: String) {
        this.idEvent.value = idEvent
        this.idHomeTeam.value = idHomeTeam
        this.idAwayTeam.value = idAwayTeam
    }

}