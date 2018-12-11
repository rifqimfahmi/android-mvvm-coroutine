package com.rifqimfahmi.foorballapps.data.source.remote.json

import com.rifqimfahmi.foorballapps.vo.Match
import com.squareup.moshi.Json

/*
 * Created by Rifqi Mulya Fahmi on 11/12/18.
 */

data class SearchSchedulesResponse (
    @Json(name = "event")
    val event: List<Match?>?
)