package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity

interface VideoRepo {
    fun addVideo(videoEntity: VideoEntity)//добавить
    fun getVideo(): List<VideoEntity>//получить
    fun removeVideo(videoEntity: VideoEntity)//удалить
}