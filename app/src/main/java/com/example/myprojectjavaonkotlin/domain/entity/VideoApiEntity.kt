package com.example.myprojectjavaonkotlin.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoApiEntity(
    val id: Long = 0,
    @SerializedName("title")
    val name: String = "Название фильма",
    @SerializedName("genres")
    val genre: String = "Жанр",
    @SerializedName("genreList")
    val genreEntityList: MutableList<GenreEntity> = mutableListOf(),
    @SerializedName("year")
    val yearRelease: String = "1900",
    @SerializedName("plotLocal")
    val description: String = "Описание фильма",
    @SerializedName("image")
    val imageUrl: String = ""
) : Parcelable


