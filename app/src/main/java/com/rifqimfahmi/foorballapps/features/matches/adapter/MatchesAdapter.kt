package com.rifqimfahmi.foorballapps.features.matches.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.base.BaseRVAdapter
import com.rifqimfahmi.foorballapps.vo.Match
import com.rifqimfahmi.foorballapps.vo.Resource
import kotlinx.android.synthetic.main.item_match.view.*

/*
 * Created by Rifqi Mulya Fahmi on 02/12/18.
 */

class MatchesAdapter(ctx: Context?, resource: Resource<List<Match>>, var clickListener: (Match) -> Unit) :
    BaseRVAdapter<Match>(ctx, resource) {

    override var errorMessage = "Failed to load matches"

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MatchItem(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MatchItem) {
            holder.bind(resource.data?.get(position), clickListener)
        }
    }

    inner class MatchItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: Match?, clickListener: (Match) -> Unit) {
            match?.let { evt ->
                with(itemView) {
                    tv_date.text = evt.getDate()
                    tv_club1.text = evt.strHomeTeam
                    tv_score1.text = evt.intHomeScore
                    tv_club2.text = evt.strAwayTeam
                    tv_score2.text = evt.intAwayScore
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        itemView.stateListAnimator =
                                AnimatorInflater.loadStateListAnimator(this.context, R.animator.lift_on_touch)
                    }
                    setOnClickListener {
                        clickListener(evt)
                    }
                }
            }
        }
    }
}
