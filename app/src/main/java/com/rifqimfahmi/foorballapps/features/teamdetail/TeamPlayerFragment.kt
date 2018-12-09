package com.rifqimfahmi.foorballapps.features.teamdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.playerdetail.PlayerDetailActivity
import com.rifqimfahmi.foorballapps.features.teamdetail.adapter.PlayerAdapter
import com.rifqimfahmi.foorballapps.vo.Player
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import kotlinx.android.synthetic.main.list_items.*

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class TeamPlayerFragment : Fragment() {

    private lateinit var viewModel: TeamViewModel
    private val teamId: String? by lazy { arguments?.getString(ARG_KEY_TEAM_ID) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.list_items, container, false).also {
            viewModel = (activity as TeamDetailActivity).obtainViewModel()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
        setupList()
    }

    private fun initData() {
        viewModel.players.observe(this, Observer { res -> (rv_list.adapter as PlayerAdapter).submitData(res) })
    }

    private fun setupList() {
        srl_list.isEnabled = false
        rv_list.layoutManager = LinearLayoutManager(context)
        rv_list.adapter = PlayerAdapter(context, Resource.loading(null)) {
            startActivity(PlayerDetailActivity.getStartIntent(context, it.idPlayer))
        }
    }

    companion object {

        private const val ARG_KEY_TEAM_ID = "arg_key_team_id"

        fun newInstance(teamId: String) = TeamPlayerFragment().apply {
            val bundle = Bundle()
            bundle.putString(ARG_KEY_TEAM_ID, teamId)
            arguments = bundle
        }
    }
}