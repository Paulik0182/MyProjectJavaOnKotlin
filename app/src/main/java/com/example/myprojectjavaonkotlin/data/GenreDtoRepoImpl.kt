package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.repo.GenreDtoRepo

class GenreDtoRepoImpl : GenreDtoRepo {

    override fun getGenre(): MutableList<String> =
        mutableListOf(
        "action",
        "comedy",
        "family",
        "history"
    )
}