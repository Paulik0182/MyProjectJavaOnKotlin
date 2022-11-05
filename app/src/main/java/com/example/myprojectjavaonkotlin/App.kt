package com.example.myprojectjavaonkotlin

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.example.myprojectjavaonkotlin.data.CollectionInteractorImpl
import com.example.myprojectjavaonkotlin.data.GenreRepoImpl
import com.example.myprojectjavaonkotlin.data.ImdbApiManager
import com.example.myprojectjavaonkotlin.data.MovieDtoRepoImpl
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo
import java.util.*

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 * Any - это базовый объект, это тип для всего. Map это ключ - значение
 */

class App : Application() {

    private val imdbApiManager: ImdbApiManager = ImdbApiManager()
    private val mainHandler: Handler by lazy { Handler(Looper.getMainLooper()) }

    val movieDtoRepo: MovieDtoRepo by lazy { MovieDtoRepoImpl(imdbApiManager, mainHandler) }
    private val genreRepo: GenreRepo by lazy { GenreRepoImpl() }
    val collectionInteractor: CollectionInteractor by lazy {
        CollectionInteractorImpl(
            genreRepo,
            movieDtoRepo
        )
    }

    val rotationFreeStorage: MutableMap<String, Any> = WeakHashMap()
}