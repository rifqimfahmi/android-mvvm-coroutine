package com.rifqimfahmi.foorballapps.features.searchteam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.AbsentLiveData
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 11/12/18.
 */
 
class SearchTeamViewModel(ctx: Application, sportRepository: SportRepository) : AndroidViewModel(ctx) {

    private val query = MutableLiveData<String>()
    val result: LiveData<Resource<List<Team>>> = Transformations.switchMap(query) { q ->
        if (q.isNullOrEmpty()) {
            AbsentLiveData.create()
        } else {
            sportRepository.searchTeam(q)
        }
    }

    fun submitQuery(query: String?) {
        if (!query.isNullOrEmpty() && (this.query.value != query)) {
            this.query.value = query
        }
    }


}