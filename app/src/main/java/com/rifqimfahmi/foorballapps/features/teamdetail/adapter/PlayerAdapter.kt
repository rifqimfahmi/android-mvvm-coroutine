package com.rifqimfahmi.foorballapps.features.teamdetail.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.base.BaseRVAdapter
import com.rifqimfahmi.foorballapps.vo.Player
import com.rifqimfahmi.foorballapps.vo.Resource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class PlayerAdapter(ctx: Context?, resource: Resource<List<Player>>, val clickListener: (Player) -> Unit) :
    BaseRVAdapter<Player>(ctx, resource) {
    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PlayerItem(LayoutInflater.from(ctx).inflate(R.layout.item_player, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerItem) {
            holder.bind(resource.data?.get(position), clickListener)
        }
    }

    inner class PlayerItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(res: Player?, clickListener: (Player) -> Unit) {
            res ?: return
            with(itemView) {
                if (!res.strCutout.isNullOrBlank()) {
                    Picasso.get().load(res.strCutout).into(ivPhoto)
                } else {
                    Picasso.get().load(res.strThumb).into(ivPhoto)
                }
                tvName.text = res.strPlayer
                tvRole.text = res.strPosition

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    stateListAnimator = AnimatorInflater.loadStateListAnimator(this.context, R.animator.lift_on_touch)
                }

                setOnClickListener { clickListener(res) }
            }
        }
    }
}