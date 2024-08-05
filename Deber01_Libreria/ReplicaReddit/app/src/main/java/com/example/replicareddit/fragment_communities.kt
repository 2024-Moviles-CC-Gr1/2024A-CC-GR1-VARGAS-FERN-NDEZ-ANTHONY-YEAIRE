package com.example.replicareddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class fragment_communities : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment_communities, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.communitiesRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CommunitiesAdapter(getCommunityItems())

        return view
    }

    private fun getCommunityItems(): List<CommunityItem> {
        return listOf(
            CommunityItem("Community A"),
            CommunityItem("Community B"),

        )
    }
}