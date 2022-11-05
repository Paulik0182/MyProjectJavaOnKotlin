package com.example.myprojectjavaonkotlin.data

import android.os.Handler
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class MovieDtoRepoImpl(
    private val imdbApiManager: ImdbApiManager,
    private val mainHandler: Handler
) : MovieDtoRepo {

    override fun getMovies(genres: List<String>, callback: (List<MovieDto>) -> Unit) {
        Thread {
            val movies = imdbApiManager.loadMovies(genres)
            mainHandler.post {
                callback.invoke(movies)
            }

        }.start()
    }

    override fun getMovie(id: String, callback: (MovieDto) -> Unit) {
        throw UninitializedPropertyAccessException("не сделано")
    }
}