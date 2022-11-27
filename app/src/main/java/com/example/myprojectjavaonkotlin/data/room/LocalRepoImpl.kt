package com.example.myprojectjavaonkotlin.data.room

import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto

class LocalRepoImpl(
    private val localData: HistoryDao
) : LocalRepo {

    override fun getAllHistory(): List<FavoriteMovieDto> {
        return mapToHistoryEntity(localData.getAll())
    }

    private fun mapToHistoryEntity(entityList: List<HistoryEntity>): List<FavoriteMovieDto> {

        return entityList.map {
            FavoriteMovieDto(
                id = "1",
                image = "",
                title = "film",
                description = "Обычный фильм",
                runtimeStr = "",
                genres = "comedy",
                genreList = ArrayList(),
                yearRelease = "2000",
                comment = "Интересный",
                isFavorite = true
            )
        }
    }
}