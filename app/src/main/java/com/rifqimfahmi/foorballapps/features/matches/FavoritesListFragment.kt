package com.rifqimfahmi.foorballapps.features.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqimfahmi.foorballapps.R
import kotlinx.android.synthetic.main.list_items.*

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */
 
class FavoritesListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_items, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance() = FavoritesListFragment()
    }
}