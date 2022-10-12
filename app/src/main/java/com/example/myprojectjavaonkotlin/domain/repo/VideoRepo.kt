package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

interface VideoRepo {
    fun addVideo(videoEntity: VideoEntity)
    fun getVideos(onVideo: (List<VideoEntity>) -> Unit)
    fun getVideo(id: Long, onVideo: (VideoEntity?) -> Unit)
}