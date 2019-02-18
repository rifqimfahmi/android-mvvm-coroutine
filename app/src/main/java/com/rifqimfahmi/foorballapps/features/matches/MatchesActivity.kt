package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.data.source.local.SportDb
import com.rifqimfahmi.foorballapps.features.matches.adapter.MainPagerAdapter
import com.rifqimfahmi.foorballapps.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesActivity : AppCompatActivity() {

    lateinit var viewModel: MatchesViewModel
    var viewModelFactory: ViewModelProvider.Factory? = null // for testing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)

        setSupportActionBar(tb_main)

        tb_main.setTitle(R.string.app_name)
        vpMain.adapter = MainPagerAdapter(supportFragmentManager)
        btmNavMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.matches -> vpMain.setCurrentItem(0, false)
                R.id.teams -> vpMain.setCurrentItem(1, false)
                R.id.favorites -> vpMain.setCurrentItem(2, false)
            }
            true
        }

        viewModel = obtainViewModel()
    }

    fun obtainViewModel(): MatchesViewModel = obtainViewModel(MatchesViewModel::class.java, viewModelFactory)
}
