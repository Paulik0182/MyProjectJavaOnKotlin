package com.example.myprojectjavaonkotlin.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.ItemVideoListBinding
import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto
import com.squareup.picasso.Picasso

class FavoriteListViewHolder(
    parent: ViewGroup,
    onDetailVideoListener: (FavoriteMovieDto) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_video_list, parent, false)
) {

    private val binding: ItemVideoListBinding = ItemVideoListBinding.bind(itemView)

    private lateinit var video: FavoriteMovieDto

    fun bind(favoriteMovieDto: FavoriteMovieDto) {
        this.video = favoriteMovieDto

        binding.nameTextView.text = favoriteMovieDto.title
        binding.yearReleaseTextView.text = favoriteMovieDto.yearRelease
        if (favoriteMovieDto.image.isNotBlank()) {
            //Picasso
            Picasso.get()
                .load(favoriteMovieDto.image)
                .placeholder(R.drawable.uploading_images)
                .into(binding.coverImageView)
            binding.coverImageView.scaleType =
                ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент
        }

        if (favoriteMovieDto.isFavorite) {
            binding.favoriteImageView.isVisible = favoriteMovieDto.isFavorite
        } else {
            R.drawable.favourites_icon_filled
        }
    }

    init {
        itemView.setOnClickListener {
            onDetailVideoListener.invoke(video)
        }
    }
}