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
import com.rifqimfahmi.foorballapps.databinding.ListItemsBinding

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class TeamPlayerFragment : Fragment() {

    private lateinit var binding: ListItemsBinding
    private lateinit var viewModel: TeamViewModel
    private val teamId: String? by lazy { arguments?.getString(ARG_KEY_TEAM_ID) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListItemsBinding.inflate(inflater, container, false)
        viewModel = (activity as TeamDetailActivity).obtainViewModel()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()
        setupList()
    }

    private fun initData() {
        viewModel.players.observe(this, Observer { res -> (binding.rvList.adapter as PlayerAdapter).submitData(res) })
    }

    private fun setupList() {
        binding.srlList.isEnabled = false
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = PlayerAdapter(context, Resource.loading(null)) {
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