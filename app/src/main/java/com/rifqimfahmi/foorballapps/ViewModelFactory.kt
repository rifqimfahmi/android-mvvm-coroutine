package com.rifqimfahmi.foorballapps

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.data.source.local.SportDb
import com.rifqimfahmi.foorballapps.data.source.remote.SportServiceFactory
import com.rifqimfahmi.foorballapps.features.matchdetail.MatchViewModel
import com.rifqimfahmi.foorballapps.features.matches.MatchesViewModel

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

class ViewModelFactory private constructor(
    private val application: Application,
    private val sportRepository: SportRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(MatchesViewModel::class.java) ->
                    MatchesViewModel(application, sportRepository)
                isAssignableFrom(MatchViewModel::class.java) ->
                    MatchViewModel(application, sportRepository)
                else ->
                    error("Invalid View Model class")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            return INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                ViewModelFactory(
                    application,
                    SportRepository.getInstance(
                        SportDb.getDatabase(application.applicationContext),
                        SportServiceFactory.getService()
                    )
                ).also { INSTANCE = it }
            }
        }
    }
}