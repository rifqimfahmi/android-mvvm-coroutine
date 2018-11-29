package com.rifqimfahmi.foorballapps.util

import androidx.lifecycle.LiveData

/*
 * Created by Rifqi Mulya Fahmi on 29/11/18.
 */
 
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create() : LiveData<T> {
            return AbsentLiveData()
        }
    }
}