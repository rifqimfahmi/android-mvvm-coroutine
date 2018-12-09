package com.rifqimfahmi.foorballapps.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Rifqi Mulya Fahmi on 09/12/18.
 */

@Entity(tableName = "favorite_teams")
data class FavoriteTeam(
    @PrimaryKey
    val idTeam: String
)