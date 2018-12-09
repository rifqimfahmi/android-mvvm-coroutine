package com.rifqimfahmi.foorballapps.features.playerdetail

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.AbsentLiveData

/*
 * Created by Rifqi Mulya Fahmi on 09/12/18.
 */
 
class PlayerViewModel(ctx: Application, sportRepository: SportRepository) : AndroidViewModel(ctx) {

    val context: Context = ctx.applicationContext

    private val playerId = MutableLiveData<String>()

    val player = Transformations.switchMap(playerId) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            sportRepository.getPlayer(id)
        }
    }

    fun initData(playerId : String) {
        this.playerId.value = playerId
    }
}