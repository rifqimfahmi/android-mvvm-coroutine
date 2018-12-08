package com.rifqimfahmi.foorballapps.data.source.remote.json

import com.rifqimfahmi.foorballapps.vo.Player
import com.squareup.moshi.Json

data class PlayersResponse(
    @Json(name = "player")
    val player: List<Player?>?
)