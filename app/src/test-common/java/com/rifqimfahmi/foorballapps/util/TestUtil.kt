package com.rifqimfahmi.foorballapps.util

import com.rifqimfahmi.foorballapps.data.source.remote.json.SchedulesResponse
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 16/12/18.
 */

object TestUtil {

    private var idLeagueTest = "4328"
    private var matchTestIds = arrayOf("576647", "576650")
    private var teamTestIds = arrayOf("133604", "134301")

    fun createMatchDetailRes(testMatchId: String): SchedulesResponse = SchedulesResponse(createListOfMatch(testMatchId))

    fun <T> createLoadingNullResource(): Resource<List<T>> = Resource.loading(null)

    fun <T> createErrorNullResource(): Resource<List<T>> = Resource.error("Some error message", null)

    fun createSuccessResourceMatch(): Resource<List<Match>> = Resource.success(createMatches())

    fun createSuccessResourceTeam(): Resource<List<Team>> = Resource.success(createTeams())

    private fun createTeams(): List<Team> {
        val teams = mutableListOf<Team>()
        teamTestIds.forEach {
            teams.add(createTeam(it))
        }
        return teams
    }

    private fun createTeam(idTeam: String): Team =
        Team(
            idLeagueTest, idTeam, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null
        )

    private fun createMatches(): List<Match> {
        val matches = mutableListOf<Match>()
        matchTestIds.forEach {
            matches.add(createMatch(it))
        }
        return matches
    }

    private fun createListOfMatch(testMatchId: String): List<Match> = listOf(
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