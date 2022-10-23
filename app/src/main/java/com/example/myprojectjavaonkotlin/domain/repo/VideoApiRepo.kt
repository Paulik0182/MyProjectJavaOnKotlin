package com.example.myprojectjavaonkotlin.domain.repo

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoApiEntity

interface VideoApiRepo {

    fun getVideosApi(onVideoApi: (MutableList<VideoApiEntity>) -> Unit)

    fun getGenre(genreKey: String, onGenre: (GenreEntity?) -> Unit)

    fun getGenreKey(): List<GenreEntity>
}