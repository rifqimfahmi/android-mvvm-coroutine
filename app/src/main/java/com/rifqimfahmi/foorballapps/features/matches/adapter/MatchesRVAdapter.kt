package com.rifqimfahmi.foorballapps.features.matches.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R

/*
 * Created by Rifqi Mulya Fahmi on 19/11/18.
 */
 
class MatchesRVAdapter(

) : RecyclerView.Adapter<MatchesRVAdapter.MatchItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchItemHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MatchItemHolder, position: Int) {
    }

    inner class MatchItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}