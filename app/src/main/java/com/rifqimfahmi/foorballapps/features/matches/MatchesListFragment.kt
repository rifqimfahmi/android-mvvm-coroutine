package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.matches.adapter.MatchesRVAdapter
import kotlinx.android.synthetic.main.list_items.*
import kotlinx.android.synthetic.main.list_items.view.*

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class MatchesListFragment : Fragment() {

    private lateinit var viewModel: MatchesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_items, container, false).also { view ->
            viewModel = (activity as MatchesActivity).obtainViewModel()
            viewModel.apply {
                filterLeague.observe(parentFragment as MatchesFragment, Observer {

                })
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupList()
    }

    private fun setupList() {
        rv_list.adapter = MatchesRVAdapter()
        rv_list.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance() = MatchesListFragment()
    }
}