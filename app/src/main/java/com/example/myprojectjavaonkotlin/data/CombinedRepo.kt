package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class CombinedRepo(
    private val cacheRepo: MovieDtoRepo,
    private val networkRepo: MovieDtoRepo
) : MovieDtoRepo {

    private val condition = true

    override fun getMovies(genres: List<String>, callback: (List<MovieDto>) -> Unit) {
        val workingRepo = if (condition) cacheRepo else networkRepo
        workingRepo.getMovies(genres, callback)
    }

    override fun getMovie(id: String, callback: (MovieDto?) -> Unit) {
        val workingRepo = if (condition) cacheRepo else networkRepo
        workingRepo.getMovie(id, callback)
    }

}