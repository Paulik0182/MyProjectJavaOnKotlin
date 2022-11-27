package com.example.myprojectjavaonkotlin.domain.interactor

import com.example.myprojectjavaonkotlin.domain.entity.FavoriteCollectionEntity
import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto

interface GenreWithFavoriteMovieInteractor {
    fun getCollections(callback: (List<FavoriteCollectionEntity>) -> Unit)

    fun getMovies(genres: List<String>, callback: (List<FavoriteCollectionEntity>) -> Unit)

    fun getMovie(id: String, callback: (FavoriteCollectionEntity?) -> Unit)

    fun getFavMovies(genres: List<String>, callback: (List<FavoriteMovieDto>) -> Unit)
    fun getFavMovie(id: String, callback: (FavoriteMovieDto?) -> Unit)
}
