package com.rifqimfahmi.foorballapps.features.matches

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.mock
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
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
    fun refresh() {
        viewModel.refreshMatches()
        verifyNoMoreInteractions(repository)

        viewModel.setMatchesFilterBy(0)
        viewModel.refreshMatches()
        verifyNoMoreInteractions(repository)

        val matchesObs = mock<Observer<Resource<List<Match>>>>()
        viewModel.nextMatches.observeForever(matchesObs)
        viewModel.prevMatch.observeForever(matchesObs)

        verify(repository).nextMatches(testLeagueId)
        verify(repository).prevMatches(testLeagueId)
    }
}