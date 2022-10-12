package com.example.myprojectjavaonkotlin.ui.video

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

class VideoListViewHolder(
    itemView: View,
    onDetailVideoListener: (VideoEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.name_text_view)
    private val genre = itemView.findViewById<TextView>(R.id.genre_text_view)
    private val yearRelease = itemView.findViewById<TextView>(R.id.year_release_text_view)


    private lateinit var video: VideoEntity

    fun bind(videoEntity: VideoEntity) {
        this.video = videoEntity

        name.text = videoEntity.name
        genre.text = videoEntity.genre
        yearRelease.text = videoEntity.yearRelease
    }

    init {
        itemView.setOnClickListener {
            onDetailVideoListener.invoke(video)
        }
    }
}