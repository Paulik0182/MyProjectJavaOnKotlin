package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.VideoRepo

class VideoRepoImpl: VideoRepo {

    private var data: MutableList<VideoEntity> = mutableListOf()

    override fun addVideo(videoEntity: VideoEntity) {
        data.add(videoEntity)
    }

    override fun getVideo(): List<VideoEntity> {
        return ArrayList(data)
    }

    override fun removeVideo(videoEntity: VideoEntity) {
        data.remove(videoEntity)
    }

    init {
        data.add(VideoEntity
            (1,
            "Любовь и голуби",
            "Комедия", "1984 год",
            "Деревенская комедия - мелодрама «Любовь и голуби» повествует о непростых " +
                    "взаимоотношениях простых людей, которые не думают о том, что любовь сильная " +
                    "и вечная бывает не только в кино, но и они сами способны так любить. В центре " +
                    "сюжета будни деревенской семьи Василия Кузякина. Он живет простой обычной " +
                    "жизнью, есть у него и жена Надежда, женщина шумная и часто ругающая своего " +
                    "мужа за увлечение голубями."))
    }

}