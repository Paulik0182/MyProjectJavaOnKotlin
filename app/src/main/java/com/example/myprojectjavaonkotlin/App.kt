package com.example.myprojectjavaonkotlin

import android.app.Application
import com.example.myprojectjavaonkotlin.data.VideoRepoImpl
import com.example.myprojectjavaonkotlin.domain.repo.VideoRepo

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 */

class App: Application() {

    val videoRepo: VideoRepo by lazy {
        VideoRepoImpl()
    }
}