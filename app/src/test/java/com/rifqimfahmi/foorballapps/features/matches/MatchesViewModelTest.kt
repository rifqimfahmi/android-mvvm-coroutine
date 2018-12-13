package com.rifqimfahmi.foorballapps.features.matches

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.AbsentLiveData
import com.rifqimfahmi.foorballapps.util.mock
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


/*
 * Created by Rifqi Mulya Fahmi on 03/12/18.
 */

@RunWith(JUnit4::class)
class MatchesViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: SportRepository
    @Mock
    private lateinit var context: Application
    private lateinit var viewModel: MatchesViewModel

    private val testArrayLeagueId = arrayOf("5341", "4329")
    private val testLeagueId = testArrayLeagueId[0]

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setupContext()

        viewModel = MatchesViewModel(context, repository)
        verify(repository).getFavoriteMatches()
        verify(repository).getFavoriteTeams()
    }

    private fun setupContext() {
        `when`<Context>(context.applicationContext).thenReturn(context)
        `when`(context.resources).thenReturn(mock(Resources::class.java))
        `when`(context.resources.getStringArray(R.array.leagues_id)).thenReturn(testArrayLeagueId)
    }

    @Test
    fun basic() {
        val matchesObs = mock<Observer<Resource<List<Match>>>>()
        viewModel.nextMatches.observeForever(matchesObs)
        viewModel.prevMatch.observeForever(matchesObs)

        viewModel.setMatchesFilterBy(0)
        verify(repository).nextMatches(testLeagueId)
        verify(repository).prevMatches(testLeagueId)
    }

    @Test
    fun refreshMatches() {
        viewModel.refreshMatches()
        verifyNoMoreInteractions(repository)

        viewModel.setMatchesFilterBy(0)
        assertThat(viewModel.matchFilterId.value, `is`(equalTo(testLeagueId)))

        viewModel.refreshMatches()
        assertThat(viewModel.matchFilterId.value, `is`(equalTo(viewModel.matchFilterId.value)))
        verifyNoMoreInteractions(repository)

        val matchesObs = mock<Observer<Resource<List<Match>>>>()
        viewModel.nextMatches.observeForever(matchesObs)
        viewModel.prevMatch.observeForever(matchesObs)

        verify(repository).nextMatches(testLeagueId)
        verify(repository).prevMatches(testLeagueId)
    }

    @Test
    fun refreshTeams() {
        viewModel.refreshTeams()
        verifyNoMoreInteractions(repository)

        viewModel.setTeamFilterBy(0)
        assertThat(viewModel.teamFilterId.value, `is`(equalTo(testLeagueId)))

        viewModel.refreshTeams()
        verifyNoMoreInteractions(repository)

        val teamsObs = mock<Observer<Resource<List<Team>>>>()
        viewModel.teams.observeForever(teamsObs)

        verify(repository).teams(testLeagueId)
    }

    @Test
    fun noObserver() {
        viewModel.setTeamFilterBy(0)
        verify(repository, never()).teams(testLeagueId)

        viewModel.setMatchesFilterBy(0)
        verify(repository, never()).nextMatches(testLeagueId)
        verify(repository, never()).prevMatches(testLeagueId)
    }
}