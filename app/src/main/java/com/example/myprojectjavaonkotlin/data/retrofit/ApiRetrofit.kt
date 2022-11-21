package com.example.myprojectjavaonkotlin.data.retrofit

import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {

    @GET("genres=")
    fun loadMovies(
        @Query("genres") genres: List<String>
    ): Call<List<MovieDto>>
}