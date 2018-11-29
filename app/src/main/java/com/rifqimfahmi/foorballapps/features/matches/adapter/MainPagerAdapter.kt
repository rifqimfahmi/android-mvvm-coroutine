package com.rifqimfahmi.foorballapps.features.matches.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rifqimfahmi.foorballapps.features.matches.FavoritesFragment
import com.rifqimfahmi.foorballapps.features.matches.MatchesFragment
import com.rifqimfahmi.foorballapps.features.matches.TeamsFragment

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */
 
class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MatchesFragment.newInstance()
            1 -> TeamsFragment.newInstance()
            2 -> FavoritesFragment.newInstance()
            else -> error("Cannot place more than 3 fragments")
        }
    }

    override fun getCount(): Int = 3
}