package com.example.replicareddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class CommunityItem(val communityName: String)

class CommunitiesAdapter(private val items: List<CommunityItem>) :
    RecyclerView.Adapter<CommunitiesAdapter.CommunityViewHolder>() {

    class CommunityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val communityName: TextView = itemView.findViewById(R.id.communityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_community, parent, false)
        return CommunityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val item = items[position]
        holder.communityName.text = item.communityName
    }

    override fun getItemCount(): Int = items.size
}