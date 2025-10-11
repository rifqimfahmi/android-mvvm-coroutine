package com.rifqimfahmi.foorballapps.features.searchteam

import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.features.base.BaseSearchActivity
import com.rifqimfahmi.foorballapps.features.matches.adapter.TeamAdapter
import com.rifqimfahmi.foorballapps.features.teamdetail.TeamDetailActivity
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import com.rifqimfahmi.foorballapps.databinding.ListItemsBinding

class SearchTeamActivity : BaseSearchActivity<Team>() {

    private lateinit var viewModel: SearchTeamViewModel

    override fun setupAdapter() {
        rvList.adapter = TeamAdapter(this, Resource.loading(null)) {
            startActivity(TeamDetailActivity.getStartIntent(this, it.idTeam))
        }
    }

    override fun setupData() {
        viewModel = obtainViewModel()
        with (viewModel) {
            submitQuery(sQuery)
            result.observe(this@SearchTeamActivity, Observer { data -> updateData(data) })
        }
    }

    override fun submitQuery(query: String?) {
        viewModel.submitQuery(query)
    }

    override fun updateData(data: Resource<List<Team>>?) {
        if (data == null || rvList == null) return
        (rvList.adapter as TeamAdapter).submitData(data)
    }

    private fun obtainViewModel() = obtainViewModel(SearchTeamViewModel::class.java)
}
