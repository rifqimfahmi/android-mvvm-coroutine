package com.rifqimfahmi.foorballapps.features.teamdetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rifqimfahmi.foorballapps.features.teamdetail.TeamDescFragment
import com.rifqimfahmi.foorballapps.features.teamdetail.TeamPlayerFragment

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class TeamDetailPagerAdapter(fm: FragmentManager?, private val teamId: String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TeamDescFragment.newInstance()
            1 -> TeamPlayerFragment.newInstance(teamId)
            else -> error("Can't have more than 2 fragment on team detail")
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "OVERVIEW"
            1 -> "PLAYERS"
            else -> super.getPageTitle(position)
        }
    }
}