package com.rifqimfahmi.foorballapps.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.data.source.local.SportDao
import com.rifqimfahmi.foorballapps.data.source.local.SportDb
import com.rifqimfahmi.foorballapps.data.source.remote.SportService
import com.rifqimfahmi.foorballapps.util.ApiUtil.successScheduleCall
import com.rifqimfahmi.foorballapps.util.TestContextProvider
import com.rifqimfahmi.foorballapps.util.TestUtil
import com.rifqimfahmi.foorballapps.util.mock
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/*
 * Created by Rifqi Mulya Fahmi on 15/12/18.
 */

@RunWith(JUnit4::class)
class SportRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dao: SportDao
    @Mock
    private lateinit var service: SportService
    private lateinit var repository: SportRepository

    private val testMatchId = "576558"

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        val db = mock(SportDb::class.java)
        `when`(db.sportDao()).thenReturn(dao)
        `when`(db.runInTransaction(any())).thenCallRealMethod()

        repository = SportRepository(db, dao, service, TestContextProvider())
    }

    @Test
    fun loadMatchDetail() {
        val dbData = MutableLiveData<Match>()
        `when`(dao.getMatchDetail(testMatchId)).thenReturn(dbData)

        val match = TestUtil.createMatchDetailRes(testMatchId)
        val call = successScheduleCall(match)
        `when`(service.getMatchDetail(testMatchId)).thenReturn(call)

        val data = repository.getEventDetail(testMatchId)
        verify(dao).getMatchDetail(testMatchId)
        verifyNoMoreInteractions(service)

        val obs = mock<Observer<Resource<Match>>>()
        data.observeForever(obs)
        verifyNoMoreInteractions(service)
        verify(obs).onChanged(Resource.loading(null))

        val updateDbData = MutableLiveData<Match>()
        `when`(dao.getMatchDetail(testMatchId)).thenReturn(updateDbData)

        dbData.postValue(null)
        verify(service).getMatchDetail(testMatchId)
        verify(dao).saveMatches(match.events!!)

        updateDbData.postValue(match.events!![0])
        verify(obs).onChanged(Resource.success(match.events!![0]))
    }

}