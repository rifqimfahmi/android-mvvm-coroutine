package com.rifqimfahmi.foorballapps.data.source.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */
 
class SportServiceFactory {
    companion object {

        @Volatile
        private var INSTANCE: SportService? = null

        private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

        fun getService() : SportService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(SportService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}