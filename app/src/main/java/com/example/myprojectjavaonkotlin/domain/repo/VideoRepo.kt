package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

interface VideoRepo {
    fun addVideo(videoEntity: VideoEntity): List<VideoEntity>//добавить
    fun getVideos(onVideo: (MutableList<VideoEntity>) -> Unit)
    fun getVideo(id: Long, onVideo: (VideoEntity?) -> Unit)
}