package com.example.myprojectjavaonkotlin.ui.video

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.squareup.picasso.Picasso

class VideoListViewHolder(
    itemView: View,
    onDetailVideoListener: (VideoEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.name_text_view)
    private val yearRelease = itemView.findViewById<TextView>(R.id.year_release_text_view)
    private val coverImageView = itemView.findViewById<ImageView>(R.id.cover_image_view)

    private lateinit var video: VideoEntity

    fun bind(videoEntity: VideoEntity) {
        this.video = videoEntity

        name.text = videoEntity.name
        yearRelease.text = videoEntity.yearRelease
        if (videoEntity.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(videoEntity.imageUrl)
                .placeholder(R.drawable.uploading_images)
                .into(coverImageView)
        coverImageView.scaleType = ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент
        }
    }

    init {
        itemView.setOnClickListener {
            onDetailVideoListener.invoke(video)
        }
    }
}