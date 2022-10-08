package com.example.myprojectjavaonkotlin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KotlinCounterEntity(
    val name: String,
    var counter: Int

): Parcelable{

    fun increment() {
        counter++
    }

    fun decrement() {
        counter--
    }
}