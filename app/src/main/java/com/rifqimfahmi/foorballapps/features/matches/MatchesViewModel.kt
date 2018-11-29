package com.rifqimfahmi.foorballapps.features.matches

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.data.source.SportRepository

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

class MatchesViewModel(context: Application, sportRepository: SportRepository) : AndroidViewModel(context) {

    // LiveData for league categories
    val filterLeague = MutableLiveData<String>()

    val context: Context = context.applicationContext // application Context to avoid leaks

    fun setFilterBy(position: Int) {
        filterLeague.value = context.resources.getStringArray(R.array.leagues)[position]
    }

}