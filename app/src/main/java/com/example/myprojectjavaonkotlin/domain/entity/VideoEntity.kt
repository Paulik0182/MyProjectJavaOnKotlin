package com.example.myprojectjavaonkotlin.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoEntity(
    val id: Long = 0,
    val name: String = "Название фильма",
    val genre: String = "Жанр",
    val yearRelease: String = "1900",
    val description: String = "Описание фильма",
    val imageUrl: String = ""
) : Parcelable