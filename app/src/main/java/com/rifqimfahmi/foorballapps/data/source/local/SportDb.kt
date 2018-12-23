package com.rifqimfahmi.foorballapps.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rifqimfahmi.foorballapps.vo.*

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */
@Database(entities = [Match::class, Team::class, Player::class, FavoriteMatch::class, FavoriteTeam::class], version = 1)
abstract class SportDb : RoomDatabase() {

    abstract fun sportDao(): SportDao

    companion object {

        @Volatile
        private var INSTANCE: SportDb? = null

        fun getDatabase(context: Context): SportDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, SportDb::class.java, "sport_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}