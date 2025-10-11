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
import com.rifqimfahmi.foorballapps.databinding.FragmentTeamDetailBinding

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class TeamDescFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailBinding
    private lateinit var viewModel: TeamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        viewModel = (activity as TeamDetailActivity).obtainViewModel()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.team.observe(this, Observer { res ->
            updateTeamDetail(res)
        })
    }

    private fun updateTeamDetail(resource: Resource<Team>?) {
        val team = resource?.data ?: return
        binding.tvDesc.text = team.strDescriptionEN
    }

    companion object {
        fun newInstance() = TeamDescFragment()
    }
}