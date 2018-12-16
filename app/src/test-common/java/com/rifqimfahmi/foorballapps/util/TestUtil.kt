package com.rifqimfahmi.foorballapps.util

import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.vo.Match

/*
 * Created by Rifqi Mulya Fahmi on 16/12/18.
 */

object TestUtil {

    fun createMatchDetailRes(testMatchId: String): SchedulesResponse = SchedulesResponse(createListOfMatch(testMatchId))

    private fun createListOfMatch(testMatchId: String) : List<Match> = listOf(
        createMatch(testMatchId)
    )

    private fun createMatch(testMatchId: String): Match =
        Match(
            testMatchId, null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null
        )
}