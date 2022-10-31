package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto

fun MovieDto.mapToGenre(movie: MutableList<MovieDto>): GenreEntity{
    return GenreEntity(
        key = "",
        genre = genres,
        movie = movie
    )
}