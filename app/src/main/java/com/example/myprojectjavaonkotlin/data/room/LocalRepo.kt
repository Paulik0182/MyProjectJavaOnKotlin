package com.example.myprojectjavaonkotlin.data.room

import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto

interface LocalRepo {
    fun getAllHistory(): List<FavoriteMovieDto>
}