package com.rifqimfahmi.foorballapps.data.source.remote.json

import com.rifqimfahmi.foorballapps.vo.Team
import com.squareup.moshi.Json

data class TeamsResponse (
    @Json(name = "teams")
    val teams: List<Team?>?
)