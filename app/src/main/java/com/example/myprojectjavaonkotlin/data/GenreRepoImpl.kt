package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo

/**
 * список разделов
 */

class GenreRepoImpl : GenreRepo {

    override fun getGenres(): List<String> = mutableListOf(
        "action",
        "comedy",
        "family",
        "history"
    )
}