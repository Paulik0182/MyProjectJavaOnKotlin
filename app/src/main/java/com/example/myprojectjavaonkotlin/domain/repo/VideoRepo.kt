package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

interface VideoRepo {
    fun addVideo(videoEntity: VideoEntity): List<VideoEntity>//добавить
    fun getVideo(onVideo: (MutableList<VideoEntity>) -> Unit)
}