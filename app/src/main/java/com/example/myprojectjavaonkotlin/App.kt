package com.example.myprojectjavaonkotlin

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import com.example.myprojectjavaonkotlin.data.CollectionInteractorImpl
import com.example.myprojectjavaonkotlin.data.GenreRepoImpl
import com.example.myprojectjavaonkotlin.data.ImdbApiManager
import com.example.myprojectjavaonkotlin.data.MovieDtoRepoImpl
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 * Any - это базовый объект, это тип для всего. Map это ключ - значение
 */

class App : Application() {

    private val myReceiver: MyReceiver by lazy {
        MyReceiver()
    }

    private val imdbApiManager: ImdbApiManager = ImdbApiManager()
    private val mainHandler: Handler by lazy { Handler(Looper.getMainLooper()) }
    private val genreRepo: GenreRepo by lazy { GenreRepoImpl() }

    val movieDtoRepo: MovieDtoRepo by lazy {
        MovieDtoRepoImpl(
            imdbApiManager,
            mainHandler,
            genreRepo
        )
    }
    val collectionInteractor: CollectionInteractor by lazy {
        CollectionInteractorImpl(
            genreRepo,
            movieDtoRepo
        )
    }

    override fun onCreate() {
        super.onCreate()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(myReceiver, it)
        }

        registerReceiver(
            myReceiver, IntentFilter(
                Intent.ACTION_BATTERY_LOW
            )
        )
    }
}