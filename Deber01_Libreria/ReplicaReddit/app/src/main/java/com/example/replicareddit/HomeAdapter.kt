package com.example.replicareddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class HomeItem(val communityName: String, val description: String, val imageResId: Int?)

class HomeAdapter(private val items: List<HomeItem>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val communityName: TextView = itemView.findViewById(R.id.communityName)
        val description: TextView = itemView.findViewById(R.id.description)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val upVoteButton: ImageView = itemView.findViewById(R.id.upVoteButton)
        val downVoteButton: ImageView = itemView.findViewById(R.id.downVoteButton)
        val commentButton: ImageView = itemView.findViewById(R.id.commentButton)
        val shareButton: ImageView = itemView.findViewById(R.id.shareButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]
        holder.communityName.text = item.communityName
        holder.description.text = item.description
        item.imageResId?.let {
            holder.imageView.setImageResource(it)
            holder.imageView.visibility = View.VISIBLE
        } ?: run {
            holder.imageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
}