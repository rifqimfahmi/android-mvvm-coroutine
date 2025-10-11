package com.rifqimfahmi.foorballapps.features.matches.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.base.BaseRVAdapter
import com.rifqimfahmi.foorballapps.features.matches.MatchesListFragment
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.databinding.ItemMatchBinding

/*
 * Created by Rifqi Mulya Fahmi on 02/12/18.
 */

class MatchesAdapter(ctx: Context?, resource: Resource<List<Match>>, private val clickListener: (Match) -> Unit) :
    BaseRVAdapter<Match>(ctx, resource) {

    override var errorMessage = "Failed to load events"

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchItem(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MatchItem) {
            holder.bind(resource.data?.get(position), clickListener)
        }
    }

    inner class MatchItem(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nMatch: Match?, clickListener: (Match) -> Unit) {
            nMatch?.let { match ->
                binding.tvHour.text = match.getHour()
                binding.tvDate.text = match.getDate()
                binding.tvClub1.text = match.strHomeTeam
                binding.tvScore1.text = match.intHomeScore
                binding.tvClub2.text = match.strAwayTeam
                binding.tvScore2.text = match.intAwayScore
                binding.root.stateListAnimator =
                        AnimatorInflater.loadStateListAnimator(binding.root.context, R.animator.lift_on_touch)
                if (match.matchType == MatchesListFragment.TYPE_NEXT_MATCH) {
                    binding.ivNotification.visibility = View.VISIBLE
                    binding.ivNotification.setOnClickListener {
                        ctx?.startActivity(Intent(Intent.ACTION_INSERT).apply {
                            data = CalendarContract.Events.CONTENT_URI
                            putExtra(CalendarContract.Events.TITLE, "${match.strHomeTeam} vs ${match.strAwayTeam}")
                            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, match.getStartTime())
                        })
                    }
                }
                binding.root.setOnClickListener {
                    clickListener(match)
                }
            }
        }
    }
}
