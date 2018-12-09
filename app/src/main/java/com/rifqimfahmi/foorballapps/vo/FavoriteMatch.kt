package com.rifqimfahmi.foorballapps.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

@Entity(tableName = "favorite_matches")
data class FavoriteMatch (
    @PrimaryKey
    val idMatch: String
) {

}