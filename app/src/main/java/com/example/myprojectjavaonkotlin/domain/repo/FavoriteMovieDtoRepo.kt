package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.GenreDto
import com.example.myprojectjavaonkotlin.domain.entity.MovieIdEntity

interface FavoriteMovieDtoRepo {

    fun addFavorite(movieDtoId: MovieIdEntity)
    fun removeEntity(movieDtoId: MovieIdEntity)
    fun getFavorites(): List<MovieIdEntity>
    fun isFavorite(genre: GenreDto, movieId: String): Boolean
}