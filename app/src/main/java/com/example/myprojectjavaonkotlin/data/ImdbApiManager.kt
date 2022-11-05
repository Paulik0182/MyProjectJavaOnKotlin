package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ImdbApiManager {
    companion object {
        private const val API_KEY = "k_r6gwl7te"
        private const val BASE_URL = "https://imdb-api.com"
        private const val API = "API"
        private const val LANG = "ru"
        private const val ADVANCED_SEARCH = "AdvancedSearch"
        private const val COMING_SOON = "ComingSoon"

        private const val ADVANCED_SEARCH_REQUEST =
            "$BASE_URL/$LANG/$API/$ADVANCED_SEARCH/$API_KEY?"
        private const val COMING_SOON_REQUEST =
            "$BASE_URL/$LANG/$API/$COMING_SOON/$API_KEY?"
    }

    fun loadComingSoon(): List<MovieDto> {
        val url = URL(COMING_SOON_REQUEST) //собрали поноценный адрес

        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.connectTimeout = 3_000//время после которого загрузка рекратится
        urlConnection.connect()
        val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

        val resultJsonString = bufferedReader.readLines().toString()
        urlConnection.disconnect()

        val itemType = object : TypeToken<MutableList<ComingSoonResponseDto>>() {}.type
        val result =
            Gson().fromJson<List<ComingSoonResponseDto>>(resultJsonString, itemType)
        return result.first().items
    }

    fun loadMovies(genres: List<String>): List<MovieDto> {
        val genresSb = StringBuilder()
        genresSb.append("genres=") //собираем строку для адреса
        genres.forEach {
            genresSb.append("$it, ") // то-есть в адрес будет свтавлятся action,comedy,family,history. Итог - полноценная строка
        }
        val url = URL(ADVANCED_SEARCH_REQUEST + genresSb.toString()) //собрали поноценный адрес

        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.connectTimeout = 3_000//время после которого загрузка рекратится
        urlConnection.connect()
        val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

        val resultJsonString = bufferedReader.readLines().toString()
        urlConnection.disconnect()

        val itemType = object : TypeToken<MutableList<AdvancedSearchResponseDto>>() {}.type
        val result =
            Gson().fromJson<MutableList<AdvancedSearchResponseDto>>(resultJsonString, itemType)
        return result.first().results
    }

    data class ComingSoonResponseDto(
        val errorMassage: String,
        val items: MutableList<MovieDto>
    )

    data class AdvancedSearchResponseDto(
        val queryString: String,
        val results: MutableList<MovieDto>
    )
}