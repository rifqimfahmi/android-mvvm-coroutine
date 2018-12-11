package com.rifqimfahmi.foorballapps.features.searchmatch

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

/*
 * Created by Rifqi Mulya Fahmi on 11/12/18.
 */
 
class SearchMatchViewModel(ctx: Application, sportRepository: SportRepository) : AndroidViewModel(ctx) {

    val context: Context = ctx.applicationContext

    private val query = MutableLiveData<String>()
    val result: LiveData<Resource<List<Match>>> = Transformations.switchMap(query) { q ->
        if (q.isNullOrEmpty()) {
            AbsentLiveData.create()
        } else {
            sportRepository.searchMatch(q)
        }
    }

    fun submitQuery(query: String?) {
        if (!query.isNullOrEmpty() && (this.query.value != query)) {
            this.query.value = query
        }
    }
}