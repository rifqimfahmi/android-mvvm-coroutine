package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.matchdetail.MatchDetailActivity
import com.rifqimfahmi.foorballapps.features.matches.adapter.MatchesAdapter
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Status
import com.rifqimfahmi.foorballapps.databinding.ListItemsBinding

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class MatchesListFragment : Fragment() {

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
    }

    private fun setupList() {
        binding.srlList.setOnRefreshListener { viewModel.refreshMatches() }
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = MatchesAdapter(context, Resource.loading(null)) {
            context?.startActivity(
                MatchDetailActivity.getStartIntent(context, it.idEvent, it.idHomeTeam, it.idAwayTeam)
            )
        }

        when (getType()) {
            TYPE_NEXT_MATCH -> {
                viewModel.nextMatches.observe(activity as MatchesActivity, Observer { data ->
                    updateData(data)
                })
            }
            TYPE_PREV_MATCH -> {
                viewModel.prevMatch.observe(activity as MatchesActivity, Observer { data ->
                    updateData(data)
                })
            }
        }
    }

    private fun updateData(data: Resource<List<Match>>?) {
        if (data == null || binding.rvList == null) return
        (binding.rvList.adapter as MatchesAdapter).submitData(data)
        updateRefreshIndicator(data)
    }

    private fun <T> updateRefreshIndicator(data: Resource<List<T>>) {
        binding.srlList.isRefreshing = data.status == Status.LOADING
    }

    private fun getType(): String? {
        return arguments?.getString(KEY_MATCH)
    }

    companion object {

        private const val KEY_MATCH = "key_match"
        const val TYPE_NEXT_MATCH = "type_next_match"
        const val TYPE_PREV_MATCH = "type_prev_match"

        fun newInstance(type: String): MatchesListFragment {
            val fragment = MatchesListFragment()
            fragment.arguments = Bundle().apply {
                putString(KEY_MATCH, type)
            }
            return fragment
        }
    }
}