package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.matches.adapter.MatchesRVAdapter
import kotlinx.android.synthetic.main.list_items.*

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class MatchesListFragment : Fragment() {

    private lateinit var viewModel: MatchesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_items, container, false).also { view ->
            viewModel = (activity as MatchesActivity).obtainViewModel()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewModel.nextMatches.observe(activity as MatchesActivity, Observer { data ->
            Log.d("SOME_DATA", data.toString())
        })
    }

    private fun setupList() {
        rv_list.adapter = MatchesRVAdapter()
        rv_list.layoutManager = LinearLayoutManager(context)
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