package com.example.replicareddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class fragment_home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.homeRecyclerView)

        // Set up the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = HomeAdapter(getHomeItems())

        return view
    }

    private fun getHomeItems(): List<HomeItem> {
        // Populate this with your data
        return listOf(
            HomeItem("Community 1", "Description 1", null),
            HomeItem("Community 2", "Description 2", R.drawable.rome),
            // Add more items as needed
        )
    }
}