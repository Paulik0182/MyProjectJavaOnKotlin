package com.example.myprojectjavaonkotlin.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.App
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.VideoRepo

private const val DETAILS_VIDEO_KEY = "DETAILS_VIDEO_KEY"

class DetailsVideoFragment : Fragment(R.layout.fragment_details_video) {

    private val app: App by lazy { requireActivity().application as App }
    private lateinit var videoRepo: VideoRepo
    private lateinit var videoList: MutableList<VideoEntity>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initView(view)
    }

    private fun initView(view: View) {
        TODO("Not yet implemented")
    }


    interface Controller {
        // TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        fun newInstance(videoEntity: VideoEntity) =
            DetailsVideoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_VIDEO_KEY, videoEntity)
                }
            }
    }
}