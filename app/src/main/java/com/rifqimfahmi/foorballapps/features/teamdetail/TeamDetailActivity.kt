package com.rifqimfahmi.foorballapps.features.teamdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.teamdetail.adapter.TeamDetailPagerAdapter
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamViewModel
    private var menu: Menu? = null

    val teamId: String by lazy { intent.getStringExtra(ARG_KEY_MATCH_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setupToolbar()
        setupPager()
        setupData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.match_detail, menu)
        updateIconIfFavorite(viewModel.isFavorite.value)
        return super.onCreateOptionsMenu(menu)
    }

    private fun updateIconIfFavorite(favorite: Boolean?) {
        if (favorite == null) return
        if (favorite) {
            menu?.findItem(R.id.favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorites)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                viewModel.toggleFavorite(teamId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupData() {
        viewModel = obtainViewModel().apply {
            initData(teamId)
            team.observe(this@TeamDetailActivity, Observer { res -> updateTeamDetail(res) })
            isFavorite.observe(this@TeamDetailActivity, Observer { isFavorite -> updateFavoriteIcon(isFavorite) })
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean?) {
        val menuItem = menu?.findItem(R.id.favorite)
        if (isFavorite == null || menuItem == null) return
        if (isFavorite) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorites)
            showMessage("Added to favorites")
        } else {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
            showMessage("Removed from favorites")
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(content_container, message, Snackbar.LENGTH_SHORT).show()
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
