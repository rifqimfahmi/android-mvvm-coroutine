package com.rifqimfahmi.foorballapps.features.searchmatch

import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.features.base.BaseSearchActivity
import com.rifqimfahmi.foorballapps.features.matchdetail.MatchDetailActivity
import com.rifqimfahmi.foorballapps.features.matches.adapter.MatchesAdapter
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.databinding.ListItemsBinding

class SearchMatchActivity : BaseSearchActivity<Match>() {

    private lateinit var viewModel: SearchMatchViewModel

    override fun setupAdapter() {
        rvList.adapter = MatchesAdapter(this, Resource.loading(null)) {
            startActivity(MatchDetailActivity.getStartIntent(this, it.idEvent, it.idHomeTeam, it.idAwayTeam))
        }
    }

    override fun setupData() {
        viewModel = obtainViewModel()
        with(viewModel) {
            submitQuery(sQuery)
            result.observe(this@SearchMatchActivity, Observer { data -> updateData(data) })
        }
    }

    override fun submitQuery(query: String?) {
        viewModel.submitQuery(query)
    }

    override fun updateData(data: Resource<List<Match>>?) {
        if (data == null || rvList == null) return
        (rvList.adapter as MatchesAdapter).submitData(data)
    }

    private fun obtainViewModel() = obtainViewModel(SearchMatchViewModel::class.java)
}
