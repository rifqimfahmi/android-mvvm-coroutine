package com.rifqimfahmi.foorballapps.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifqimfahmi.foorballapps.ViewModelFactory

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

fun <T : ViewModel> AppCompatActivity.obtainViewModel(
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
) : T {
    return if (viewModelFactory == null) {
        ViewModelProvider(this, ViewModelFactory.getInstance(application))[viewModelClass]
    } else {
        ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }
}