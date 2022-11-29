package com.example.myprojectjavaonkotlin.ui.favourites

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.domain.entity.CollectionEntity
import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto

class CollectionFavoriteAdapter(
    private var data: List<CollectionEntity> = mutableListOf(),
    private var onFavoriteClickListener: (FavoriteMovieDto) -> Unit = {},
) : RecyclerView.Adapter<CollectionFavoriteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(video: List<CollectionEntity>) {
        data = video
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionFavoriteViewHolder {
        return CollectionFavoriteViewHolder(
            parent,
            onFavoriteClickListener
        )
    }

    override fun onBindViewHolder(holder: CollectionFavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): CollectionEntity = data[position]

    override fun getItemCount(): Int = data.size
}