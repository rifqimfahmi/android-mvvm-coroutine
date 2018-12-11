package com.rifqimfahmi.foorballapps.features.searchmatch

import android.app.SearchManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.matchdetail.MatchDetailActivity
import com.rifqimfahmi.foorballapps.features.matches.adapter.MatchesAdapter
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.list_items.*

class SearchMatchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val sQuery by lazy { intent.getStringExtra(SearchManager.QUERY) }

    private lateinit var viewModel: SearchMatchViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        setupToolbar()
        setupData()
        setupList()
    }

    private fun setupList() {
        srl_list.isEnabled = false
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = MatchesAdapter(this, Resource.loading(null)) {
            startActivity(MatchDetailActivity.getStartIntent(this, it.idEvent, it.idHomeTeam, it.idAwayTeam))
        }
    }

    private fun setupData() {
        viewModel = obtainViewModel()
        with(viewModel) {
            submitQuery(sQuery)
            result.observe(this@SearchMatchActivity, Observer { data -> updateData(data) })
        }
    }

    private fun updateData(data: Resource<List<Match>>?) {
        if (data == null || rv_list == null) return
        (rv_list.adapter as MatchesAdapter).submitData(data)
    }

    private fun setupToolbar() {
        setSupportActionBar(tbSearch)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchable_expand, menu)
        searchView = (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setOnQueryTextListener(this@SearchMatchActivity)
            onActionViewExpanded()
            clearFocus()
            setQuery(sQuery, false)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.submitQuery(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun obtainViewModel() = obtainViewModel(SearchMatchViewModel::class.java)
}
