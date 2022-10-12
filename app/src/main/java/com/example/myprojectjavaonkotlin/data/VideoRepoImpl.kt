package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.VideoRepo
import java.lang.NullPointerException

class VideoRepoImpl(
) : VideoRepo {

    private var data: MutableList<VideoEntity> = mutableListOf()

    override fun addVideo(videoEntity: VideoEntity): MutableList<VideoEntity> {
        data.add(videoEntity)
        return mutableListOf(videoEntity)
    }

    override fun getVideo(onVideo: (MutableList<VideoEntity>) -> Unit) {
        val video = VideoEntity(1,
            "Любовь и голуби",
            "Комедия",
            "1984 год",
            "Деревенская комедия - мелодрама «Любовь и голуби» повествует о непростых " +
                    "взаимоотношениях простых людей, которые не думают о том, что любовь сильная " +
                    "и вечная бывает не только в кино, но и они сами способны так любить. В центре " +
                    "сюжета будни деревенской семьи Василия Кузякина. Он живет простой обычной " +
                    "жизнью, есть у него и жена Надежда, женщина шумная и часто ругающая своего " +
                    "мужа за увлечение голубями.")
        try {
            addVideo(video).let {
                onVideo.invoke(it)
            }
        } catch (exc: NullPointerException) {
            exc.printStackTrace()
        }
    }

    init {
        data.add(
            VideoEntity
                (
                1,
                "Любовь и голуби",
                "Комедия",
                "1984 год",
                "Деревенская комедия - мелодрама «Любовь и голуби» повествует о непростых " +
                        "взаимоотношениях простых людей, которые не думают о том, что любовь сильная " +
                        "и вечная бывает не только в кино, но и они сами способны так любить. В центре " +
                        "сюжета будни деревенской семьи Василия Кузякина. Он живет простой обычной " +
                        "жизнью, есть у него и жена Надежда, женщина шумная и часто ругающая своего " +
                        "мужа за увлечение голубями."
            )
        )
    }

}