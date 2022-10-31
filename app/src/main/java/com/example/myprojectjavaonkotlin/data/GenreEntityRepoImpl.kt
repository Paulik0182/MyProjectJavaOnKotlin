package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity
import com.example.myprojectjavaonkotlin.domain.repo.GenreEntityRepo

class GenreEntityRepoImpl : GenreEntityRepo {

    private var data: MutableList<GenreEntity> = mutableListOf()

    override fun addGenre(genreEntity: GenreEntity) {
        data.add(genreEntity)
    }

    override fun getGenres(onGenre: (List<GenreEntity>) -> Unit) {
        onGenre(ArrayList(data))
    }

    init {
        data.add(
            GenreEntity(
                "01",
                "action",
                mutableListOf()
            )
        )
        data.add(
            GenreEntity(
                "02",
                "comedy",
                mutableListOf()
            )
        )
        data.add(
            GenreEntity(
                "03",
                "family",
                mutableListOf()
            )
        )
        data.add(
            GenreEntity(
                "04",
                "history",
                mutableListOf()
            )
        )
    }
}