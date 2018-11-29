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

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

class MatchesViewModel(context: Application, sportRepository: SportRepository) : AndroidViewModel(context) {

    // LiveData for league categories
    val filterLeagueId = MutableLiveData<String>()
    val matches: LiveData<List<Match>> = Transformations.switchMap(filterLeagueId) {

        AbsentLiveData.create<List<Match>>()
    }

    val context: Context = context.applicationContext // application Context to avoid leaks

    fun setFilterBy(position: Int) {
        filterLeagueId.value = context.getLeaguesId(position)
    }

    fun loadMatch(leaguesId: String, type: String?) {

    }
}