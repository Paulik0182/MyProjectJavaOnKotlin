package com.example.myprojectjavaonkotlin.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionVideoEntity(
    val id: Long = 0,
    val name: String = "Название коллекции",
    val video: MutableList<VideoEntity> = mutableListOf()
) : Parcelable