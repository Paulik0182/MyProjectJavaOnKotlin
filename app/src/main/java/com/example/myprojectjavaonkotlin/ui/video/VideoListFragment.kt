package com.example.myprojectjavaonkotlin.ui.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myprojectjavaonkotlin.R

class VideoListFragment : Fragment(R.layout.fragment_video_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance() = VideoListFragment()
    }
}