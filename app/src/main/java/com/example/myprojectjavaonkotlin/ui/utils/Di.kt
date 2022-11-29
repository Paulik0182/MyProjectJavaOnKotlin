package com.example.myprojectjavaonkotlin.ui.utils

import android.content.Context
import com.example.myprojectjavaonkotlin.MyReceiver
import com.example.myprojectjavaonkotlin.data.CollectionInteractorImpl
import com.example.myprojectjavaonkotlin.data.FavoriteMovieRepoImpl
import com.example.myprojectjavaonkotlin.data.GenreRepoImpl
import com.example.myprojectjavaonkotlin.data.MovieWithFavoriteWithRepoImpl
import com.example.myprojectjavaonkotlin.data.retrofit.ImdbApi
import com.example.myprojectjavaonkotlin.data.retrofit.RetrofitMovieDtoRepoImpl
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.domain.repo.FavoriteMovieRepo
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieWithFavoriteRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://imdb-api.com/ru/API/"
private const val API_KEY = "k_r6gwl7te" //todo должен быть в gradle secret properties

class Di(
    private val context: Context
) {

    val myReceiver: MyReceiver by lazy { MyReceiver() }

    // todo переделать
    val genreRepo: GenreRepo by lazy {
        GenreRepoImpl()
    }

    val collectionInteractor: CollectionInteractor by lazy {
        CollectionInteractorImpl(genreRepo, movieWithFavoriteRepo)
    }

    val movieDtoRepo: MovieDtoRepo by lazy {
        RetrofitMovieDtoRepoImpl(imdbApi, API_KEY, context)
    }

    val favoriteMovieRepo: FavoriteMovieRepo = FavoriteMovieRepoImpl()

    val movieWithFavoriteRepo: MovieWithFavoriteRepo by lazy {
        MovieWithFavoriteWithRepoImpl(movieDtoRepo, favoriteMovieRepo)
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val imdbApi: ImdbApi by lazy { retrofit.create(ImdbApi::class.java) }
}