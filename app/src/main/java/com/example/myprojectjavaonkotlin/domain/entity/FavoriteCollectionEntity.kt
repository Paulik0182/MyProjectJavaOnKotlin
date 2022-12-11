package com.example.myprojectjavaonkotlin.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteCollectionEntity(
    val genre: GenreDto,
    val movies: MutableList<FavoriteMovieDto> = mutableListOf()
) : Parcelable
