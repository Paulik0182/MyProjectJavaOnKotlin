package com.example.myprojectjavaonkotlin.ui.favourites

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto

class FavoriteListAdapter(
    private var data: List<MovieDto> = mutableListOf(),
    private var onDetailVideoListener: (MovieDto) -> Unit = {},
) : RecyclerView.Adapter<FavoriteListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(video: List<MovieDto>) {
        data = video
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder(
            parent,
            onDetailVideoListener
        )
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): MovieDto = data[position]

    override fun getItemCount(): Int = data.size
}