package com.example.myprojectjavaonkotlin.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.ItemCollectionVideoBinding
import com.example.myprojectjavaonkotlin.domain.entity.CollectionVideoEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

class CollectionVideoViewHolder(
    parent: ViewGroup,
    onVideoClick: (VideoEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_collection_video, parent, false)
) {

    private val binding: ItemCollectionVideoBinding = ItemCollectionVideoBinding.bind(itemView)

    private lateinit var collectionVideoEntity: CollectionVideoEntity

    private val videoListAdapter: VideoListAdapter = VideoListAdapter {
        onVideoClick.invoke(it)
    }

    //вложенный recyclerView (горизонтальный)
    private val recyclerView = itemView.findViewById<RecyclerView>(
        R.id.video_list_recycler_view
    ).apply {
        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = videoListAdapter

        //Для лучшего эффекта скролинга по горизонтали
        //чтобы во view скрол элементы останавливались по середине экрана
        val helper: SnapHelper = LinearSnapHelper()
        helper.attachToRecyclerView(this)
    }

    fun bind(collectionVideoEntity: CollectionVideoEntity) {
        this.collectionVideoEntity = collectionVideoEntity
        binding.nameTextView.text = collectionVideoEntity.name

        //привязываем адаптер к данным
        videoListAdapter.setData(collectionVideoEntity.video)
    }
}