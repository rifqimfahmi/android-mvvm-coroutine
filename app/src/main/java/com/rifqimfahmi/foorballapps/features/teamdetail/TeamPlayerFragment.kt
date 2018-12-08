package com.rifqimfahmi.foorballapps.features.teamdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class TeamPlayerFragment : Fragment() {

    private lateinit var viewModel: TeamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.list_items, container, false).also {
            viewModel = (activity as TeamDetailActivity).obtainViewModel()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance() = TeamPlayerFragment()
    }
}