package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity

interface GenreEntityRepo {
    fun addGenre(genreEntity: GenreEntity)
    fun getGenres(onGenre: (List<GenreEntity>) -> Unit)
}