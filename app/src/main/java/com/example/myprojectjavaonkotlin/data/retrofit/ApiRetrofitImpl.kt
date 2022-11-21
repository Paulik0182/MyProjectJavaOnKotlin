package com.example.myprojectjavaonkotlin.data.retrofit

import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofitImpl {

    companion object {
        private const val API_KEY = "k_r6gwl7te"
        private const val BASE_URL = "https://imdb-api.com"
        private const val API = "API"
        private const val LANG = "ru"
        private const val ADVANCED_SEARCH = "AdvancedSearch"

        private const val ADVANCED_SEARCH_REQUEST =
            "$BASE_URL/$LANG/$API/$ADVANCED_SEARCH/$API_KEY?"
    }

    fun loadMovies(genres: List<String>): List<MovieDto> {
        val genresSb = StringBuilder()
        genresSb.append("genres=") //собираем строку для адреса
        genres.forEach {
            genresSb.append("$it, ") // то-есть в адрес будет свтавлятся action,comedy,family,history. Итог - полноценная строка
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ADVANCED_SEARCH_REQUEST + genresSb.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiRetrofit: ApiRetrofit = retrofit.create(ApiRetrofit::class.java)

        return apiRetrofit.loadMovies(genres).execute().body() ?: emptyList()
    }
}
