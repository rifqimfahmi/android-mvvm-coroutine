package com.rifqimfahmi.foorballapps

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/*
 * Created by Rifqi Mulya Fahmi on 16/12/18.
 */

open class ContextProviders {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO

    companion object {
        @Volatile
        private var INSTANCE: ContextProviders? = null

        fun getInstance() : ContextProviders {
            return INSTANCE?: synchronized(this) {
                ContextProviders()
            }.also {
                INSTANCE = it
            }
        }
    }
}