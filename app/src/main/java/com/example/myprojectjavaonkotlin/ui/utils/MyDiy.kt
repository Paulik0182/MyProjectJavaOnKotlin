package com.example.myprojectjavaonkotlin.ui.utils

import com.example.myprojectjavaonkotlin.MyReceiver
import com.example.myprojectjavaonkotlin.data.GenreRepoImpl
import com.example.myprojectjavaonkotlin.data.retrofit.ImdbApi
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://imdb-api.com/ru/API/"

class MyDiy {

    val myReceiver: MyReceiver by lazy { MyReceiver() }

    val genreRepo: GenreRepo by lazy { GenreRepoImpl() }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val imdbApi: ImdbApi by lazy { retrofit.create(ImdbApi::class.java) }
}