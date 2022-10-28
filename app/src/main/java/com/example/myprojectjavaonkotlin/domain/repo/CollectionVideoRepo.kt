package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.CollectionVideoEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

interface CollectionVideoRepo {
    fun addCollectionVideo(collectionVideoEntity: CollectionVideoEntity)
    fun getCollectionVideos(onCollectionVideo: (List<CollectionVideoEntity>) -> Unit)

    fun getVideo(videoId: Long, onVideo: (VideoEntity?) -> Unit)
}