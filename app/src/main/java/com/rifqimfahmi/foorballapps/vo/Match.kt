package com.rifqimfahmi.foorballapps.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Rifqi Mulya Fahmi on 26/11/18.
 */

@Entity(tableName = "matches")
data class Match (
    @PrimaryKey val name: String
) {

}