package com.example.myprojectjavaonkotlin.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreEntity(
    val key: String = "",
    val genre: String = "",
    val movie: MutableList<MovieDto> = mutableListOf()
) : Parcelable
