package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.databinding.FragmentFavoritesBinding
import com.rifqimfahmi.foorballapps.features.matches.adapter.FavoritesPagerAdapter

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.vpFavorites.adapter = FavoritesPagerAdapter(childFragmentManager)
        binding.tabFavorites.setupWithViewPager(binding.vpFavorites)
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}