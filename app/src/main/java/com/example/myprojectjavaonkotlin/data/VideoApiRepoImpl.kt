package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.GenreEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoApiEntity
import com.example.myprojectjavaonkotlin.domain.repo.VideoApiRepo
import com.google.gson.Gson
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class VideoApiRepoImpl: VideoApiRepo {

    private val database = URL("https://imdb-api.com/ru/API/Title/k_r6gwl7te")
    private val videoImdbUrl: String = "https://imdb-api.com/ru/API/Title/k_r6gwl7te"
//    private val database = URL("https://imdb-api.com/ru/API/Title/k_r6gwl7te/tt1375666")

    private val gson by lazy { Gson() }

    override fun getVideosApi(onVideoApi: (MutableList<VideoApiEntity>) -> Unit) {
        val url = URL(videoImdbUrl)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.connectTimeout = 3_000//время после которого загрузка рекратится
        val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

        val resultJsonString = bufferedReader.readLines().toString()

         val dataArray = Gson().fromJson(resultJsonString, Array<Array<VideoApiEntity>>::class.java)
        val video: MutableList<VideoApiEntity> = mutableListOf()
        dataArray.forEach { array ->
            array.forEach {
                onVideoApi.invoke(video)
            }
        }
        urlConnection.disconnect()
    }

    override fun getGenre(GenreKey: String, onGenre: (GenreEntity?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getGenreKey(): List<GenreEntity> {
        TODO("Not yet implemented")
    }
}