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
import com.rifqimfahmi.foorballapps.features.matchdetail.MatchDetailActivity
import com.rifqimfahmi.foorballapps.features.matches.adapter.MatchesAdapter
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class FavoriteMatchesFragment : Fragment() {

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
        binding.rvList.adapter = MatchesAdapter(context, Resource.loading(null)) {
            context?.startActivity(
                MatchDetailActivity.getStartIntent(context, it.idEvent, it.idHomeTeam, it.idAwayTeam)
            )
        }
    }

    private fun initData() {
        viewModel.favoriteMatches.observe(activity as MatchesActivity, Observer { res ->
            updateData(res)
        })
    }

    private fun updateData(res: Resource<List<Match>>?) {
        if (binding.rvList == null || res == null) return
        (binding.rvList.adapter as MatchesAdapter).submitData(res)
    }

    companion object {
        fun newInstance() = FavoriteMatchesFragment()
    }
}