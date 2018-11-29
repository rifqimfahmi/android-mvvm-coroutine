package com.rifqimfahmi.foorballapps.data.source

import com.rifqimfahmi.foorballapps.data.source.local.SportDao
import com.rifqimfahmi.foorballapps.data.source.remote.SportService

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class SportRepository (
    val sportDb: SportDao,
    val sportService: SportService
) {

    companion object {
        private var INSTANCE: SportRepository? = null

        fun getInstance(sportDb: SportDao, sportService: SportService) : SportRepository =
                INSTANCE ?: synchronized(SportRepository::class.java) {
                    SportRepository(sportDb, sportService)
                        .also { INSTANCE = it }
                }
    }
}