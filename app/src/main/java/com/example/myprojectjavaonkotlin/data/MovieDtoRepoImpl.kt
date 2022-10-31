package com.example.myprojectjavaonkotlin.data

import android.os.Handler
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class MovieDtoRepoImpl(
    private val imdbApiManager: ImdbApiManager,
    private val mainHandler: Handler
) : MovieDtoRepo {

    override fun getMovies(genres: MutableList<String>, callback: (MutableList<MovieDto>) -> Unit) {
        Thread {
            val movies = imdbApiManager.loadMovies(genres)
            mainHandler.post {
                callback.invoke(movies)
            }

        }.start()
    }
}