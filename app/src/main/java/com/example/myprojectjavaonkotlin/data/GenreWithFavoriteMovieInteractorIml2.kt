package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.FavoriteCollectionEntity
import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.domain.interactor.GenreWithFavoriteMovieInteractor
import com.example.myprojectjavaonkotlin.domain.repo.GenreRepo
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo

class GenreWithFavoriteMovieInteractorIml2(
    private val genreRepo: GenreRepo,
    private val movieDtoRepo: MovieDtoRepo,
    private val collectionInteractor: CollectionInteractor
) : GenreWithFavoriteMovieInteractor {

    override fun getFavMovies(genres: List<String>, callback: (List<FavoriteMovieDto>) -> Unit) {
        val hashMapMovies = HashMap<String, FavoriteCollectionEntity>()

        collectionInteractor.getCollections { collectionFilm ->
            val rawMovies = collectionFilm.map {
                it.mapToFavoriteGenre()
            }.toMutableList()

        }
    }

    override fun getFavMovie(id: String, callback: (FavoriteMovieDto?) -> Unit) {
        collectionInteractor.getCollections { collectionFilm ->
            val rawMovies = collectionFilm.map {
                it.mapToFavoriteGenre()
            }.toMutableList()

        }
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


    override fun getMovies(
        genres: List<String>,
        callback: (List<FavoriteCollectionEntity>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getMovie(id: String, callback: (FavoriteCollectionEntity?) -> Unit) {
        TODO("Not yet implemented")
    }
}