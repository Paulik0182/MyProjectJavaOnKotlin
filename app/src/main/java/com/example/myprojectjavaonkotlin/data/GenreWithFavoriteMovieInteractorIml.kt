package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.FavoriteCollectionEntity
import com.example.myprojectjavaonkotlin.domain.interactor.GenreWithFavoriteMovieInteractor
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class GenreWithFavoriteMovieInteractorIml(
    private val genreRepo: GenreRepo,
    private val movieDtoRepo: MovieDtoRepo
) : GenreWithFavoriteMovieInteractor {

    override fun getMovies(
        genres: List<String>,
        callback: (List<FavoriteCollectionEntity>) -> Unit
    ) {

    }

    override fun getMovie(id: String, callback: (FavoriteCollectionEntity?) -> Unit) {

    }

    override fun getCollections(callback: (List<FavoriteCollectionEntity>) -> Unit) {
        val genre = genreRepo.getGenres()
        val hashMapMovies = HashMap<String, FavoriteCollectionEntity>()

        movieDtoRepo.getMovies(genre) { movieList ->
            val rawMovies = movieList.map {
                it.mapToFavoriteMovie()
            }

            rawMovies.forEach { film ->
                film.genreList.forEach { genre ->
                    val collection = hashMapMovies[genre.genre]?.apply {
                        this.movies.add(film)
                    } ?: FavoriteCollectionEntity(genre, mutableListOf(film))
                    hashMapMovies[genre.genre] = collection
                }
            }
            callback(hashMapMovies.values.toList())
        }
    }
}