package com.rifqimfahmi.foorballapps.features.playerdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.databinding.ActivityPlayerDetailBinding
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Player
import com.rifqimfahmi.foorballapps.vo.Resource
import com.squareup.picasso.Picasso

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerDetailBinding
    private lateinit var viewModel: PlayerViewModel
    private val playerId: String by lazy { intent.getStringExtra(ARG_KEY_PLAYER_ID) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initData()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbPlayer)
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
            Picasso.get().load(strFanart1).into(binding.ivArt)
            binding.tvWeight.text = getWeight()
            binding.tvHeight.text = getHeight()
            binding.tvRole.text = strPosition
            binding.tvDesc.text = strDescriptionEN
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
