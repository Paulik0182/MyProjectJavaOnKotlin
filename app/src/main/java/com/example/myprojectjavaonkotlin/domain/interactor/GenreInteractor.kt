package com.example.myprojectjavaonkotlin.domain.interactor

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto

interface GenreInteractor {

    fun getGenre(genre: (MutableList<GenreEntity>) -> Unit)
    fun getMovies(movieId: Long, movies: (MutableList<MovieDto>) -> Unit)

}