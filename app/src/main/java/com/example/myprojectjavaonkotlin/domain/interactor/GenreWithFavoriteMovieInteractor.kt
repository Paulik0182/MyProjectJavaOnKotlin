package com.example.myprojectjavaonkotlin.domain.interactor

import com.example.myprojectjavaonkotlin.domain.entity.FavoriteCollectionEntity

interface GenreWithFavoriteMovieInteractor {
    fun getCollections(callback: (List<FavoriteCollectionEntity>) -> Unit)

    fun getMovie(id: String, callback: (FavoriteCollectionEntity?) -> Unit)
    fun getMovies(genres: List<String>, callback: (List<FavoriteCollectionEntity>) -> Unit)
}