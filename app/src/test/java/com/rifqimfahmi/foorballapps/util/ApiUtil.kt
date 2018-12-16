package com.rifqimfahmi.foorballapps.util

import androidx.lifecycle.MutableLiveData
import com.rifqimfahmi.foorballapps.data.source.remote.ApiResponse
import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.vo.Match
import retrofit2.Response

/*
 * Created by Rifqi Mulya Fahmi on 16/12/18.
 */
 
object ApiUtil {
    fun successScheduleCall(data: SchedulesResponse) = createScheduleCall(Response.success(data))

    fun createScheduleCall(response: Response<SchedulesResponse>) = MutableLiveData<ApiResponse<SchedulesResponse>>().apply {
        value = ApiResponse.create(response)
    }
}