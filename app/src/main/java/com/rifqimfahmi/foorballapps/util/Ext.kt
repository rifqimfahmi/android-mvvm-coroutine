package com.rifqimfahmi.foorballapps.util

import android.content.Context
import com.rifqimfahmi.foorballapps.R

/*
 * Created by Rifqi Mulya Fahmi on 29/11/18.
 */
 
fun Context.getLeaguesName(position: Int) : String {
    return resources.getStringArray(R.array.leagues)[position]
}

fun Context.getLeaguesId(position: Int) : String {
    return resources.getStringArray(R.array.leagues_id)[position]
}