package com.rifqimfahmi.foorballapps.features.matches.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.base.BaseRVAdapter
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Team
import com.squareup.picasso.Picasso
import com.rifqimfahmi.foorballapps.databinding.IteamTeamBinding

/*
 * Created by Rifqi Mulya Fahmi on 07/12/18.
 */

class TeamAdapter(ctx: Context?, resource: Resource<List<Team>>, private val clickListener: (Team) -> Unit)
    : BaseRVAdapter<Team>(ctx, resource) {

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = IteamTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamItem(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TeamItem) {
            holder.bind(resource.data?.get(position), clickListener)
        }
    }

    inner class TeamItem(private val binding: IteamTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nTeam: Team?, clickListener: (Team) -> Unit) {
            nTeam?.let { team ->
                if (!team.strTeamBadge.isNullOrEmpty()) { Picasso.get().load(team.strTeamBadge).into(binding.ivClub) }
                binding.tvClub.text = team.strTeam
                binding.root.stateListAnimator =
                        AnimatorInflater.loadStateListAnimator(binding.root.context, R.animator.lift_on_touch)
                binding.root.setOnClickListener {
                    clickListener(team)
                }
            }
        }
    }
}