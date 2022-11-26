package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.GenreDto
import com.example.myprojectjavaonkotlin.domain.entity.MovieIdEntity
import com.example.myprojectjavaonkotlin.domain.repo.FavoriteMovieDtoRepo

/**
 * MutableSet, HashSet (Set) - это позволяет добавлять только один элемент
 */

class FavoriteMovieDtoRepoImpl : FavoriteMovieDtoRepo {

    private val date: MutableSet<MovieIdEntity> = HashSet()

    override fun addFavorite(movieDtoId: MovieIdEntity) {
        date.add(movieDtoId)
    }

    override fun removeEntity(movieDtoId: MovieIdEntity) {
        date.remove(movieDtoId)
    }

    override fun getFavorites(): List<MovieIdEntity> {
        return ArrayList(date)
    }

    override fun isFavorite(genre: GenreDto, movieId: String): Boolean {
        TODO("Not yet implemented")
    }
}