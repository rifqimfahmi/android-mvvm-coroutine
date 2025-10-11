package com.rifqimfahmi.foorballapps.features.base

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.vo.Resource
import android.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView

/*
 * Created by Rifqi Mulya Fahmi on 11/12/18.
 */

abstract class BaseSearchActivity<T> : AppCompatActivity(), SearchView.OnQueryTextListener {

    val sQuery: String? by lazy { intent.getStringExtra(SearchManager.QUERY) }
    private lateinit var searchView: SearchView
    private lateinit var tbSearch: androidx.appcompat.widget.Toolbar
    private lateinit var srlList: SwipeRefreshLayout
    protected lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        tbSearch = findViewById(R.id.tbSearch)
        srlList = findViewById(R.id.srl_list)
        rvList = findViewById(R.id.rv_list)

        setupToolbar()
        setupData()
        setupList()
    }

    private fun setupToolbar() {
        setSupportActionBar(tbSearch)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupList() {
        srlList.isEnabled = false
        rvList.layoutManager = LinearLayoutManager(this)
        setupAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchable_expand, menu)
        searchView = (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setOnQueryTextListener(this@BaseSearchActivity)
            onActionViewExpanded()
            clearFocus()
            setQuery(sQuery, false)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        submitQuery(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    protected abstract fun setupAdapter()
    protected abstract fun setupData()
    protected abstract fun submitQuery(query: String?)
    protected abstract fun updateData(data: Resource<List<T>>?)
}