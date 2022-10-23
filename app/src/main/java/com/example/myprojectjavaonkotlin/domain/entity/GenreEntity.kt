package com.example.myprojectjavaonkotlin.domain.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreEntity(
    @SerializedName("key")
    val key: String = "Ключь жанра",
    @SerializedName("value")
    val genre: String = "Название жанра"
) : Parcelable