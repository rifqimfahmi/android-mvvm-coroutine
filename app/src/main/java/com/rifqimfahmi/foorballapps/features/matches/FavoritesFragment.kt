package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.matches.adapter.FavoritesPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        vpFavorites.adapter = FavoritesPagerAdapter(childFragmentManager)
        tabFavorites.setupWithViewPager(vpFavorites)
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}