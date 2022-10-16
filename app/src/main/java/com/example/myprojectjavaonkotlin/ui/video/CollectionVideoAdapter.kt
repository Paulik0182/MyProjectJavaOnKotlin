package com.example.myprojectjavaonkotlin.ui.video

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.CollectionVideoEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

class CollectionVideoAdapter(
    private var data: List<CollectionVideoEntity> = mutableListOf(),
    private var onVideoClickListener: (VideoEntity) -> Unit ={},
) : RecyclerView.Adapter<CollectionVideoViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setData(video: List<CollectionVideoEntity>){
        data = video
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionVideoViewHolder {
        return CollectionVideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_collection_video, parent, false),
            onVideoClickListener
        )
    }

    override fun onBindViewHolder(holder: CollectionVideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): CollectionVideoEntity = data[position]

    override fun getItemCount(): Int = data.size
}