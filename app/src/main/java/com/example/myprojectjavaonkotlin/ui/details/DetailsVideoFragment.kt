package com.example.myprojectjavaonkotlin.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

private const val DETAILS_VIDEO_KEY = "DETAILS_VIDEO_KEY"

class DetailsVideoFragment : Fragment(R.layout.fragment_details_video) {

    private lateinit var videoEntity: VideoEntity

    private lateinit var nameTv: TextView
    private lateinit var genreTv: TextView
    private lateinit var yearReleaseTv: TextView
    private lateinit var descriptionTv: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        videoEntity = requireArguments().getParcelable(DETAILS_VIDEO_KEY)!!
        setVideoEntity(videoEntity)
    }

    private fun initView(view: View) {
        nameTv = view.findViewById(R.id.name_details_text_view)
        genreTv = view.findViewById(R.id.genre_details_text_view)
        yearReleaseTv = view.findViewById(R.id.year_release_details_text_view)
        descriptionTv = view.findViewById(R.id.description_details_text_view)
    }

    private fun setVideoEntity(videoEntity: VideoEntity) {
        nameTv.text = videoEntity.name
        genreTv.text = videoEntity.genre
        yearReleaseTv.text = videoEntity.yearRelease
        descriptionTv.text = videoEntity.description
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