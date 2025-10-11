package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.databinding.ListItemsBinding
import com.rifqimfahmi.foorballapps.features.matches.adapter.TeamAdapter
import com.rifqimfahmi.foorballapps.features.teamdetail.TeamDetailActivity
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 09/12/18.
 */

class FavoritesTeamsFragment : Fragment() {

    private lateinit var binding: ListItemsBinding
    private lateinit var viewModel: MatchesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListItemsBinding.inflate(inflater, container, false)
        viewModel = (activity as MatchesActivity).obtainViewModel()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        initData()
    }


    private fun setupList() {
        binding.srlList.isEnabled = false
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = TeamAdapter(context, Resource.loading(null)) {
            startActivity(TeamDetailActivity.getStartIntent(context, it.idTeam))
        }
    }

    private fun initData() {
        viewModel.favoriteTeams.observe(activity as MatchesActivity, Observer { res -> updateData(res) })
    }

    private fun updateData(res: Resource<List<Team>>?) {
        if (binding.rvList == null || res == null) return
        (binding.rvList.adapter as TeamAdapter).submitData(res)
    }

    companion object {
        fun newInstance() = FavoritesTeamsFragment()
    }
}