package com.example.myprojectjavaonkotlin.data

import android.os.Handler
import com.example.myprojectjavaonkotlin.data.retrofit.ApiRetrofitImpl
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class MovieDtoRepoImpl(
    private val imdbApiManager: ApiRetrofitImpl,
//    private val imdbApiManager: ImdbApiManager,
    private val mainHandler: Handler,
    private val genreRepo: GenreRepo

) : MovieDtoRepo {

    private lateinit var movieDtoRepo: MovieDtoRepo

    override fun getMovies(genres: List<String>, callback: (List<MovieDto>) -> Unit) {
//        Thread {
            val movies = imdbApiManager.loadMovies(genres)
            mainHandler.post {
                callback.invoke(movies)
            }
//        }.start()
    }

    override fun getMovie(id: String, callback: (MovieDto?) -> Unit) {
        getMovies(genreRepo.getGenres()) { movieDto ->
            val result = movieDto.find { it.id == id }
            callback.invoke(result)
        }
    }
}
