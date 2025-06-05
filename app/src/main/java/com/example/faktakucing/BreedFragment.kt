package com.example.faktakucing

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.faktakucing.BreedFragmentDirections
import com.example.faktakucing.R

class BreedFragment : Fragment(R.layout.fragment_breed) {

    private val breedList = listOf("Persian", "Siamese", "Maine Coon", "Bengal", "Sphynx", "British Shorthair")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layout = view.findViewById<LinearLayout>(R.id.breedListLayout)
        val navController = findNavController()

        for (breed in breedList) {
            val button = Button(requireContext()).apply {
                text = breed
                setOnClickListener {
                    val action = BreedFragmentDirections.actionBreedFragmentToBreedDetailFragment(breedName = breed)
                    navController.navigate(action)
                }
            }
            layout.addView(button)
        }
    }
}