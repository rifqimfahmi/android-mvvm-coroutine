package com.rifqimfahmi.foorballapps.data.source.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rifqimfahmi.foorballapps.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                    .client(provideOkHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(SportService::class.java)
                INSTANCE = instance
                instance
            }
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
            client.addInterceptor(interceptor)
            return client.build()
        }
    }
}