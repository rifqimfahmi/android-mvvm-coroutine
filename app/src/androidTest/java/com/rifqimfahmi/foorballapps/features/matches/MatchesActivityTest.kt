package com.rifqimfahmi.foorballapps.features.matches

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.intercepting.SingleActivityFactory
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.util.TaskExecutorWithIdlingResourceRule
import com.rifqimfahmi.foorballapps.util.TestUtil
import com.rifqimfahmi.foorballapps.util.ViewModelUtil
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.ref.WeakReference

/*
 * Created by Rifqi Mulya Fahmi on 22/12/18.
 */

@RunWith(AndroidJUnit4::class)
class MatchesActivityTest {

    private var viewModel = mock(MatchesViewModel::class.java)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    val activityFactory = object : SingleActivityFactory<MatchesActivity>(MatchesActivity::class.java) {
        override fun create(intent: Intent?): MatchesActivity {
            val activity = MatchesActivity()
            activity.viewModelFactory = ViewModelUtil.createFor(viewModel)
            return activity
        }
    }

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MatchesActivity>(activityFactory, true, false)

    private val teams = MutableLiveData<Resource<List<Team>>>()
    private val favTeams = MutableLiveData<Resource<List<Team>>>()
    private val nextMatches = MutableLiveData<Resource<List<Match>>>()
    private val prevMatches = MutableLiveData<Resource<List<Match>>>()
    private val favMatches = MutableLiveData<Resource<List<Match>>>()

    private val loadingNullTeams = TestUtil.createLoadingNullResource<Team>()
    private val errorNullTeams = TestUtil.createErrorNullResource<Team>()
    private val loadingNullMatches = TestUtil.createLoadingNullResource<Match>()
    private val errorNullMatches = TestUtil.createErrorNullResource<Match>()
    private val successMatches = TestUtil.createSuccessResourceMatch()
    private val successTeams = TestUtil.createSuccessResourceTeam()

    @Before
    fun setUp() {
        `when`(viewModel.teams).thenReturn(teams)
        `when`(viewModel.nextMatches).thenReturn(nextMatches)
        `when`(viewModel.prevMatch).thenReturn(prevMatches)
        `when`(viewModel.favoriteMatches).thenReturn(favMatches)
        `when`(viewModel.favoriteTeams).thenReturn(favTeams)

        activityRule.launchActivity(null)
    }

    @Test
    fun have_3_tab() {
        onView(withId(R.id.btmNavMain)).check(matches(isDisplayed()))

        onView(withId(R.id.matches)).check(matches(isDisplayed()))
        onView(withId(R.id.matches)).perform(click())

        onView(withId(R.id.teams)).check(matches(isDisplayed()))
        onView(withId(R.id.teams)).perform(click())

        onView(withId(R.id.favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())
    }

    @Test
    fun favorite_no_search() {
        onView(withId(R.id.matches)).perform(click())
        onView(withId(R.id.menu_search)).check(matches(isDisplayed()))

        onView(withId(R.id.teams)).perform(click())
        onView(withId(R.id.menu_search)).check(matches(isDisplayed()))

        onView(withId(R.id.favorites)).perform(click())
        onView(withId(R.id.menu_search)).check(doesNotExist())
    }

    @Test
    fun next_matches_load_state() {
        prevMatches.postValue(successMatches)
        teams.postValue(successTeams)

        nextMatches.postValue(loadingNullMatches)
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

        nextMatches.postValue(errorNullMatches)
        onView(withId(R.id.iv_error)).check(matches(isDisplayed()))

        prevMatches.postValue(errorNullMatches)
        teams.postValue(errorNullTeams)

        nextMatches.postValue(successMatches)
        onView(withId(R.id.progressBar)).check(doesNotExist())
    }


}