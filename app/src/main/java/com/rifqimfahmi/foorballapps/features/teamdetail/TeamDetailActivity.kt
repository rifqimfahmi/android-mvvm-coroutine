package com.rifqimfahmi.foorballapps.features.teamdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.teamdetail.adapter.TeamDetailPagerAdapter
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamViewModel

    val teamId: String by lazy { intent.getStringExtra(ARG_KEY_MATCH_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setupToolbar()
        setupPager()
        setupData()
    }

    private fun setupData() {
        viewModel = obtainViewModel().apply {
            initData(teamId)
            team.observe(this@TeamDetailActivity, Observer { res -> updateTeamDetail(res) })
        }
    }

    private fun setupPager() {
        vpTeamDetail.adapter = TeamDetailPagerAdapter(supportFragmentManager, teamId)
        tabTeam.setupWithViewPager(vpTeamDetail)
    }

    private fun setupToolbar() {
        setSupportActionBar(tbTeam)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateTeamDetail(resource: Resource<Team>?) {
        val team = resource?.data ?: return
        with(team) {
            supportActionBar?.title = strTeam
            strTeamBadge?.let {
                Picasso.get().load(it).into(ivClub)
            }
            tvYear.text = getFormedYear()
            tvStadium.text = strStadium
        }
    }

    fun obtainViewModel(): TeamViewModel = obtainViewModel(TeamViewModel::class.java)

    companion object {

        private const val ARG_KEY_MATCH_ID = "arg_key_match_id"

        fun getStartIntent(context: Context?, teamId: String): Intent {
            Intent(context, TeamDetailActivity::class.java).apply {
                putExtra(ARG_KEY_MATCH_ID, teamId)
            }.also {
                return it
            }
        }
    }
}
