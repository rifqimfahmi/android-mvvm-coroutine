package com.rifqimfahmi.foorballapps.util

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.data.source.remote.ApiResponse
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/*
 * Created by Rifqi Mulya Fahmi on 01/12/18.
 */
 
class LiveDataCallAdapterFactory : Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = Factory.getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}