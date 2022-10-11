package com.example.myprojectjavaonkotlin.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myprojectjavaonkotlin.R

class DetailsVideoFragment : Fragment(R.layout.fragment_details_video) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() =
            DetailsVideoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}