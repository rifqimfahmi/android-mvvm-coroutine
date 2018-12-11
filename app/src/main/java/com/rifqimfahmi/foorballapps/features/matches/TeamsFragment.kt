package com.rifqimfahmi.foorballapps.features.matches

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.matches.adapter.TeamAdapter
import com.rifqimfahmi.foorballapps.features.searchmatch.SearchMatchActivity
import com.rifqimfahmi.foorballapps.features.searchteam.SearchTeamActivity
import com.rifqimfahmi.foorballapps.features.teamdetail.TeamDetailActivity
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Status
import kotlinx.android.synthetic.main.fragment_teams.view.*
import kotlinx.android.synthetic.main.list_items.*

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */
 
class TeamsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: MatchesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false).also {
            viewModel = (activity as MatchesActivity).obtainViewModel()
            it.sp_leagues.onItemSelectedListener = this
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupList()
    }

    private fun setupList() {
        srl_list.setOnRefreshListener { viewModel.refreshTeams() }
        rv_list.layoutManager = LinearLayoutManager(context)
        rv_list.adapter = TeamAdapter(context, Resource.loading(null)) {
            startActivity(TeamDetailActivity.getStartIntent(context, it.idTeam))
        }
        viewModel.teams.observe(this, Observer { data ->
            (rv_list.adapter as TeamAdapter).submitData(data)
            updateRefreshIndicator(data)
        })
    }

    private fun <T> updateRefreshIndicator(data: Resource<List<T>>) {
        srl_list.isRefreshing = data.status == Status.LOADING
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(ComponentName(context, SearchTeamActivity::class.java)))
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setTeamFilterBy(position)
    }

    companion object {
        fun newInstance() = TeamsFragment()
    }
}