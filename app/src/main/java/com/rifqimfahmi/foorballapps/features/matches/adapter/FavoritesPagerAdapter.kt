package com.rifqimfahmi.foorballapps.features.matches.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rifqimfahmi.foorballapps.features.matches.FavoritesListFragment

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class FavoritesPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavoritesListFragment.newInstance()
            1 -> FavoritesListFragment.newInstance()
            else -> error("Cannot create more than two fragment")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "MATCHES"
            1 -> "TEAMS"
            else -> super.getPageTitle(position)
        }
    }

    override fun getCount(): Int = 2

}