package com.example.myprojectjavaonkotlin.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.ItemVideoListBinding
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.squareup.picasso.Picasso

class VideoListViewHolder(
    parent: ViewGroup,
    onDetailVideoListener: (VideoEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_video_list, parent, false)
) {

    private val binding: ItemVideoListBinding = ItemVideoListBinding.bind(itemView)

    private lateinit var video: VideoEntity

    fun bind(videoEntity: VideoEntity) {
        this.video = videoEntity

        binding.nameTextView.text = videoEntity.name
        binding.yearReleaseTextView.text = videoEntity.yearRelease
        if (videoEntity.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(videoEntity.imageUrl)
                .placeholder(R.drawable.uploading_images)
                .into(binding.coverImageView)
            binding.coverImageView.scaleType =
                ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент
        }
    }

    init {
        itemView.setOnClickListener {
            onDetailVideoListener.invoke(video)
        }
    }
}