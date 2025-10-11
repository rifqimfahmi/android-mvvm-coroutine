package com.rifqimfahmi.foorballapps.features.teamdetail.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.features.base.BaseRVAdapter
import com.rifqimfahmi.foorballapps.vo.Player
import com.rifqimfahmi.foorballapps.vo.Resource
import com.squareup.picasso.Picasso
import com.rifqimfahmi.foorballapps.databinding.ItemPlayerBinding

/*
 * Created by Rifqi Mulya Fahmi on 08/12/18.
 */

class PlayerAdapter(ctx: Context?, resource: Resource<List<Player>>, val clickListener: (Player) -> Unit) :
    BaseRVAdapter<Player>(ctx, resource) {
    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerItem(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerItem) {
            holder.bind(resource.data?.get(position), clickListener)
        }
    }

    inner class PlayerItem(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(res: Player?, clickListener: (Player) -> Unit) {
            res ?: return
            if (!res.strCutout.isNullOrBlank()) {
                Picasso.get().load(res.strCutout).into(binding.ivPhoto)
            } else {
                Picasso.get().load(res.strThumb).into(binding.ivPhoto)
            }
            binding.tvName.text = res.strPlayer
            binding.tvRole.text = res.strPosition

            binding.root.stateListAnimator = AnimatorInflater.loadStateListAnimator(binding.root.context, R.animator.lift_on_touch)

            binding.root.setOnClickListener { clickListener(res) }
        }
    }
}