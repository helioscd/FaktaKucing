package com.example.faktakucing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BreedDetailFragment : Fragment(R.layout.fragment_breed_detail) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BreedImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val breedName = arguments?.getString("breedName") ?: "Unknown"
        recyclerView = view.findViewById(R.id.breedRecyclerView)

        adapter = BreedImageAdapter(getDummyImages(), breedName)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
    }

    private fun getDummyImages(): List<String> {
        // Bisa diganti API, ini contoh dummy gambar
        return List(6) { "https://cataas.com/cat/says/${(100..999).random()}" }
    }
}