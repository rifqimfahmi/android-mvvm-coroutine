package com.rifqimfahmi.foorballapps.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.rifqimfahmi.foorballapps.vo.Match

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

@Dao
interface SportDao {

    @Query("SELECT * FROM matches")
    fun getMatches(): List<Match>

}