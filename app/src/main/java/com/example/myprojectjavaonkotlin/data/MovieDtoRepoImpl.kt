package com.example.myprojectjavaonkotlin.data

import android.os.Handler
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class MovieDtoRepoImpl(
    private val imdbApiManager: ImdbApiManager,
    private val mainHandler: Handler
) : MovieDtoRepo {

    private lateinit var movieDtoRepo: MovieDtoRepo

    override fun getMovies(genres: List<String>, callback: (List<MovieDto>) -> Unit) {
        Thread {
            val movies = imdbApiManager.loadMovies(genres)
            mainHandler.post {
                callback.invoke(movies)
            }
        }.start()
    }

    override fun getMovie(id: String, callback: (MovieDto?) -> Unit) {
        getMovies(listOf()) { movieDto ->
            val result = movieDto.find { it.id == id }
            callback.invoke(result)
        }
//        val isFilm = movieDtoRepo.getMovies(id, callback)
//        movieDtoRepo.getMovie(id, callback) {
//            callback(it?.isFilm)
//        }
//        throw UninitializedPropertyAccessException("не сделано")
    }
}
