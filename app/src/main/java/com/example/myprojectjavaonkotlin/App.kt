package com.example.myprojectjavaonkotlin

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import androidx.room.Room
import com.example.myprojectjavaonkotlin.data.CollectionInteractorImpl
import com.example.myprojectjavaonkotlin.data.GenreWithFavoriteMovieInteractorIml
import com.example.myprojectjavaonkotlin.data.retrofit.RetrofitMovieDtoRepoImpl
import com.example.myprojectjavaonkotlin.data.room.HistoryDao
import com.example.myprojectjavaonkotlin.data.room.HistoryDataBase
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.domain.interactor.GenreWithFavoriteMovieInteractor
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo
import com.example.myprojectjavaonkotlin.ui.utils.MyDiy

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 * Any - это базовый объект, это тип для всего. Map это ключ - значение
 */

private const val API_KEY = "k_r6gwl7te" //todo должен быть в gradle secret properties

class App : Application() {

    private val myDiy: MyDiy = MyDiy()

    val movieDtoRepo: MovieDtoRepo by lazy {
        RetrofitMovieDtoRepoImpl(myDiy.imdbApi, API_KEY, this)
    }

    val collectionInteractor: CollectionInteractor by lazy {
        CollectionInteractorImpl(
            myDiy.genreRepo,
            movieDtoRepo
        )
    }

    val genreWithFavoriteMovieInteractor: GenreWithFavoriteMovieInteractor by lazy {
        GenreWithFavoriteMovieInteractorIml(
            myDiy.genreRepo,
            movieDtoRepo,
            collectionInteractor
        )
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(myDiy.myReceiver, it)
        }

        registerReceiver(
            myDiy.myReceiver, IntentFilter(
                Intent.ACTION_BATTERY_LOW
            )
        )
    }

    companion object {
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null)
                            throw IllegalStateException("Application is null DB")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            HistoryDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()//переделать, должно быть не в основном потоке
                            .build()
                    }
                }
            }
            return db!!.historyDao()
        }
    }
}