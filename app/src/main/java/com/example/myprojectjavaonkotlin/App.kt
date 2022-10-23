package com.example.myprojectjavaonkotlin

import android.app.Application
import com.example.myprojectjavaonkotlin.data.CollectionVideoRepoImpl
import com.example.myprojectjavaonkotlin.data.VideoApiRepoImpl
import com.example.myprojectjavaonkotlin.domain.repo.CollectionVideoRepo
import com.example.myprojectjavaonkotlin.domain.repo.VideoApiRepo
import java.util.*

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 */

class App: Application() {

    val collectionVideoRepo: CollectionVideoRepo by lazy { CollectionVideoRepoImpl() }

    val VideoApiRepo: VideoApiRepo by lazy { VideoApiRepoImpl() }

    // Any - это базовый объект, это тип для всего. Map это ключ - значение
    val rotationFreeStorage: MutableMap<String, Any> = WeakHashMap()
}