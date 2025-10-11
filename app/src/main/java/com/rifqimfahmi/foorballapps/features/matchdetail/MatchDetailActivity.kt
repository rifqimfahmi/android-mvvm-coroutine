package com.rifqimfahmi.foorballapps.features.matchdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.databinding.ActivityMatchDetailBinding
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import com.squareup.picasso.Picasso

class MatchDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchDetailBinding
    private lateinit var viewModel: MatchViewModel
    private var menu: Menu? = null

    val idEvent: String by lazy { intent.getStringExtra(ARG_KEY_ID_EVENT) ?: "" }
    val idHomeTeam: String by lazy { intent.getStringExtra(ARG_KEY_ID_HOME_TEAM) ?: "" }
    val idAwayTeam: String by lazy { intent.getStringExtra(ARG_KEY_ID_AWAY_TEAM) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setupToolbar()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                viewModel.toggleFavorite(idEvent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.tbDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        viewModel = obtainViewModel()
        viewModel.initData(idEvent, idHomeTeam, idAwayTeam)
        viewModel.homeTeam.observe(this, Observer { res -> setupHomeTeam(res) })
        viewModel.awayTeam.observe(this, Observer { res -> setupAwayTeam(res) })
        viewModel.matchDetail.observe(this, Observer { res -> setupMatchDetail(res) })
        viewModel.isFavorite.observe(this, Observer { isFavorite -> updateFavoriteIcon(isFavorite) })
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
        Snackbar.make(binding.contentContainer, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupMatchDetail(resource: Resource<Match>?) {
        val match = resource?.data ?: return
        with (match) {
            binding.tvDate.text = getDate()
            binding.tvHour.text = getHour()
            binding.tvClub1.text = strHomeTeam
            binding.tvScore1.text = intHomeScore
            binding.tvClub2.text = strAwayTeam
            binding.tvScore2.text = intAwayScore

            binding.tvGoalsHome.text = format(strHomeGoalDetails)
            binding.tvGoalsAway.text = format(strAwayGoalDetails)
            binding.tvShotsHome.text = intHomeShots ?: "-"
            binding.tvShotsAway.text = intAwayShots ?: "-"

            binding.tvGkHome.text = format(strHomeLineupGoalkeeper)
            binding.tvGkAway.text = format(strAwayLineupGoalkeeper)

            binding.tvDefenseHome.text = format(strHomeLineupDefense)
            binding.tvDefenseAway.text = format(strAwayLineupDefense)

            binding.tvMidfieldHome.text = format(strHomeLineupMidfield)
            binding.tvMidfieldAway.text = format(strAwayLineupMidfield)

            binding.tvForwardHome.text = format(strHomeLineupForward)
            binding.tvForwardAway.text = format(strAwayLineupForward)

            binding.tvSubstituteHome.text = format(strHomeLineupSubstitutes)
            binding.tvSubstituteAway.text = format(strAwayLineupSubstitutes)
        }

    }

    private fun setupAwayTeam(resource: Resource<Team>?) {
        resource?.data?.strTeamBadge?.let {
            Picasso.get().load(it).into(binding.ivClub2)
        }
    }

    private fun setupHomeTeam(resource: Resource<Team>?) {
        resource?.data?.strTeamBadge?.let {
            Picasso.get().load(it).into(binding.ivClub1)
        }
    }

    private fun obtainViewModel(): MatchViewModel = obtainViewModel(MatchViewModel::class.java)

    companion object {
        private const val ARG_KEY_ID_EVENT = "arg_key_id_event"
        private const val ARG_KEY_ID_HOME_TEAM = "arg_key_id_home_team"
        private const val ARG_KEY_ID_AWAY_TEAM = "arg_key_id_away_team"

        fun getStartIntent(context: Context?, idEvent: String?, idHomeTeam: String?, idAwayTeam: String?): Intent {
            val intent = Intent(context, MatchDetailActivity::class.java)
            intent.putExtra(ARG_KEY_ID_EVENT, idEvent)
            intent.putExtra(ARG_KEY_ID_HOME_TEAM, idHomeTeam)
            intent.putExtra(ARG_KEY_ID_AWAY_TEAM, idAwayTeam)
            return intent
        }
    }
}
