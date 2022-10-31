package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.interactor.GenreInteractor
import com.example.myprojectjavaonkotlin.domain.repo.GenreDtoRepo
import com.example.myprojectjavaonkotlin.domain.repo.GenreEntityRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class GenreInteractorImpl(
    private val genreEntityRepo: GenreEntityRepo,
    private val genreDtoRepo: GenreDtoRepo,
    private val movieDtoRepo: MovieDtoRepo
): GenreInteractor {

    override fun getGenre(genre: (MutableList<GenreEntity>) -> Unit) {
        movieDtoRepo.getMovies(
            mutableListOf(
                "action",
                "comedy",
                "family",
                "history"
            )
        ){movieList ->
            movieList.map {
                it.mapToGenre(movieList) }.toMutableList()
        }
    }

    override fun getMovies(movieId: Long, movies: (MutableList<MovieDto>) -> Unit) {
        TODO("Not yet implemented")
    }
}