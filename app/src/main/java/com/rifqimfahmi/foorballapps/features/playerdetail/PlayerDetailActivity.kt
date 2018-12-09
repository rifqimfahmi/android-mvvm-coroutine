package com.rifqimfahmi.foorballapps.features.playerdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Player
import com.rifqimfahmi.foorballapps.vo.Resource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PlayerViewModel
    private val playerId: String by lazy { intent.getStringExtra(ARG_KEY_PLAYER_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        initToolbar()
        initData()
    }

    private fun initToolbar() {
        setSupportActionBar(tbPlayer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        viewModel = obtainViewModel()
        viewModel.initData(playerId)
        viewModel.player.observe(this, Observer { res -> updateData(res) })
    }

    private fun updateData(res: Resource<Player>?) {
        if (res?.data == null) return
        val player = res.data

        supportActionBar?.title = player.strPlayer
        with(player) {
            Picasso.get().load(strFanart1).into(ivArt)
            tvWeight.text = getWeight()
            tvHeight.text = getHeight()
            tvRole.text = strPosition
            tvDesc.text = strDescriptionEN
        }
    }

    private fun obtainViewModel(): PlayerViewModel = obtainViewModel(PlayerViewModel::class.java)

    companion object {

        private const val ARG_KEY_PLAYER_ID = "arg_key_player_id"

        fun getStartIntent(context: Context?, playerId: String): Intent =
            Intent(context, PlayerDetailActivity::class.java).apply {
                putExtra(ARG_KEY_PLAYER_ID, playerId)
            }
    }
}
