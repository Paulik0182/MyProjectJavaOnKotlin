package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.GenreDto
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto

interface MovieDtoRepo {

    fun getMovies(genres: MutableList<String>, callback: (MutableList<MovieDto>) -> Unit)
}