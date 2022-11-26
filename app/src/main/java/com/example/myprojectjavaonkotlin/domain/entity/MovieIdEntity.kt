package com.example.myprojectjavaonkotlin.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieIdEntity(
    val genreId: String,
    val movieDtoId: String
) : Parcelable
