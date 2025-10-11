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
import com.rifqimfahmi.foorballapps.databinding.ActivityMatchesBinding
import com.rifqimfahmi.foorballapps.features.matches.adapter.MainPagerAdapter
import com.rifqimfahmi.foorballapps.util.obtainViewModel

class MatchesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchesBinding
    lateinit var viewModel: MatchesViewModel
    var viewModelFactory: ViewModelProvider.Factory? = null // for testing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbMain)

        binding.tbMain.setTitle(R.string.app_name)
        binding.vpMain.adapter = MainPagerAdapter(supportFragmentManager)
        binding.btmNavMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.matches -> binding.vpMain.setCurrentItem(0, false)
                R.id.teams -> binding.vpMain.setCurrentItem(1, false)
                R.id.favorites -> binding.vpMain.setCurrentItem(2, false)
            }
            true
        }

        viewModel = obtainViewModel()
    }

    fun obtainViewModel(): MatchesViewModel = obtainViewModel(MatchesViewModel::class.java, viewModelFactory)
}
