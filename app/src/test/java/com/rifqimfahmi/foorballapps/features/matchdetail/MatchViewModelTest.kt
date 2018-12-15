package com.rifqimfahmi.foorballapps.features.matchdetail

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.data.source.SportRepository
import com.rifqimfahmi.foorballapps.util.mock
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/*
 * Created by Rifqi Mulya Fahmi on 13/12/18.
 */

@RunWith(JUnit4::class)
class MatchViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()


    @Mock
    private lateinit var repository: SportRepository
    @Mock
    private lateinit var context: Application
    private lateinit var viewModel: MatchViewModel

    private val idEvent = "testIdEvent"
    private val idHomeTeam = "testIdHomeTeam"
    private val idAwayTeam = "testIdAwayTeam"

    private val teamObs = mock<Observer<Resource<Team>>>()
    private val matchObs = mock<Observer<Resource<Match>>>()
    private val favObs = mock<Observer<Boolean>>()

    private val testFavorite = false

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setupContext()

        viewModel = MatchViewModel(context, repository)
    }

    private fun setupContext() {
        `when`<Context>(context.applicationContext).thenReturn(context)
    }

    @Test
    fun noObserver() {
        viewModel.initData(idEvent, idHomeTeam, idAwayTeam)
        verifyNoMoreInteractions(repository)

        viewModel.toggleFavorite(idEvent)
        verify(repository, never()).toggleFavoriteMatch(idEvent, true)
    }

    @Test
    fun basic() {
        viewModel.initData(idEvent, idHomeTeam, idAwayTeam)
        with (viewModel) {
            homeTeam.observeForever(teamObs)
            verify(repository).getTeam(idHomeTeam)

            awayTeam.observeForever(teamObs)
            verify(repository).getTeam(idAwayTeam)

            matchDetail.observeForever(matchObs)
            verify(repository).getEventDetail(idEvent)

            isFavorite.observeForever(favObs)
            verify(repository).isFavoriteMatch(idEvent)
        }
    }

    @Test
    fun toggleFavorite() {
        val isFavorite = MutableLiveData<Boolean>()
        isFavorite.value = testFavorite

        `when`(repository.isFavoriteMatch(idEvent)).thenReturn(isFavorite)

        viewModel.initData(idEvent, idHomeTeam, idAwayTeam)
        viewModel.isFavorite.observeForever(favObs)

        viewModel.toggleFavorite(idEvent)
        verify(repository).toggleFavoriteMatch(idEvent, testFavorite)
    }
}