package com.rifqimfahmi.foorballapps.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.rifqimfahmi.foorballapps.ViewModelFactory

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */
 
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)