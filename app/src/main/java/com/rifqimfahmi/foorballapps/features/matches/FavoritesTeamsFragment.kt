package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rifqimfahmi.foorballapps.R

/*
 * Created by Rifqi Mulya Fahmi on 09/12/18.
 */
 
class FavoritesTeamsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_items, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance() = FavoritesTeamsFragment()
    }
}