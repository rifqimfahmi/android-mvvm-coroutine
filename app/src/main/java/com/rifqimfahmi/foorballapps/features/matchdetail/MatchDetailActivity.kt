package com.rifqimfahmi.foorballapps.features.matchdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MatchViewModel

    val idEvent: String by lazy { intent.getStringExtra(ARG_KEY_ID_EVENT) }
    val idHomeTeam: String by lazy { intent.getStringExtra(ARG_KEY_ID_HOME_TEAM) }
    val idAwayTeam: String by lazy { intent.getStringExtra(ARG_KEY_ID_AWAY_TEAM) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        viewModel = obtainViewModel()
        viewModel.initData(idEvent, idHomeTeam, idAwayTeam)
        viewModel.homeTeam.observe(this, Observer { res -> setupHomeTeam(res) })
        viewModel.awayTeam.observe(this, Observer { res -> setupAwayTeam(res) })
        viewModel.matchDetail.observe(this, Observer { res -> setupMatchDetail(res) })
    }

    private fun setupMatchDetail(resource: Resource<Match>?) {
        val match = resource?.data ?: return
        with (match) {
            tv_date.text = getDate()
            tv_date.text = getDate()
            tv_club1.text = strHomeTeam
            tv_score1.text = intHomeScore
            tv_club2.text = strAwayTeam
            tv_score2.text = intAwayScore

            tv_goals_home.text = format(strHomeGoalDetails)
            tv_goals_away.text = format(strAwayGoalDetails)
            tv_shots_home.text = intHomeShots ?: "-"
            tv_shots_away.text = intAwayShots ?: "-"

            tv_gk_home.text = format(strHomeLineupGoalkeeper)
            tv_gk_away.text = format(strAwayLineupGoalkeeper)

            tv_defense_home.text = format(strHomeLineupDefense)
            tv_defense_away.text = format(strAwayLineupDefense)

            tv_midfield_home.text = format(strHomeLineupMidfield)
            tv_midfield_away.text = format(strAwayLineupMidfield)

            tv_forward_home.text = format(strHomeLineupForward)
            tv_forward_away.text = format(strAwayLineupForward)

            tv_substitute_home.text = format(strHomeLineupSubstitutes)
            tv_substitute_away.text = format(strAwayLineupSubstitutes)
        }

    }

    private fun setupAwayTeam(resource: Resource<Team>?) {
        resource?.data?.strTeamBadge?.let {
            Picasso.get().load(it).into(iv_club1)
        }
    }

    private fun setupHomeTeam(resource: Resource<Team>?) {
        resource?.data?.strTeamBadge?.let {
            Picasso.get().load(it).into(iv_club2)
        }
    }

    private fun obtainViewModel(): MatchViewModel = obtainViewModel(MatchViewModel::class.java)

    companion object {
        private const val ARG_KEY_ID_EVENT = "arg_key_id_event"
        private const val ARG_KEY_ID_HOME_TEAM = "arg_key_id_home_team"
        private const val ARG_KEY_ID_AWAY_TEAM = "arg_key_id_away_team"

        fun getStartIntent(context: Context, idEvent: String?, idHomeTeam: String?, idAwayTeam: String?): Intent {
            val intent = Intent(context, MatchDetailActivity::class.java)
            intent.putExtra(ARG_KEY_ID_EVENT, idEvent)
            intent.putExtra(ARG_KEY_ID_HOME_TEAM, idHomeTeam)
            intent.putExtra(ARG_KEY_ID_AWAY_TEAM, idAwayTeam)
            return intent
        }
    }
}
