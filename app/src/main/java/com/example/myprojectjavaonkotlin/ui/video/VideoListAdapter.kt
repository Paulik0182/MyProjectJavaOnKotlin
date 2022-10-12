package com.example.myprojectjavaonkotlin.ui.video

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

class VideoListAdapter(
    private var data: List<VideoEntity> = mutableListOf(),
    private var listener: (VideoEntity) -> Unit = {}
) : RecyclerView.Adapter<VideoListViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setData(video: List<VideoEntity>){
        data = video
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (VideoEntity) -> Unit){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        return VideoListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_video_list, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): VideoEntity = data[position]

    override fun getItemCount(): Int = data.size
}