package com.rifqimfahmi.foorballapps.data.source.remote.json

import com.rifqimfahmi.foorballapps.vo.Match
import com.squareup.moshi.Json

/*
 * Created by Rifqi Mulya Fahmi on 01/12/18.
 */

data class SchedulesResponse(
    @Json(name = "events")
    val events: List<Match?>?
)