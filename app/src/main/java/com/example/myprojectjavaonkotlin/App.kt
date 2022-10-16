package com.example.myprojectjavaonkotlin

import android.app.Application
import com.example.myprojectjavaonkotlin.data.CollectionVideoRepoImpl
import com.example.myprojectjavaonkotlin.domain.repo.CollectionVideoRepo
import java.util.*

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 */

class App: Application() {

    val collectionVideoRepo: CollectionVideoRepo by lazy { CollectionVideoRepoImpl() }

    // Any - это базовый объект, это тип для всего. Map это ключ - значение
    val rotationFreeStorage: MutableMap<String, Any> = WeakHashMap()
}