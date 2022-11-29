package com.example.myprojectjavaonkotlin.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myprojectjavaonkotlin.domain.entity.CollectionEntity
import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.ui.utils.mutable

class FavoriteListViewModel(
    private val collectionFavorite: CollectionInteractor,
) : ViewModel() {

    //Сделали класс Factory (Фабрика)
    class Factory(
        private val collectionFavorite: CollectionInteractor,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoriteListViewModel(collectionFavorite) as T
        }
    }

    val inProgressLiveData: LiveData<Boolean> = MutableLiveData(false)
    val favoriteListLiveData: LiveData<List<CollectionEntity>> = MutableLiveData()
    val selectedVideoLiveData: LiveData<FavoriteMovieDto> = MutableLiveData()

    init {
        inProgressLiveData.mutable().postValue(true)
        collectionFavorite.getCollections {
            inProgressLiveData.mutable().postValue(false)
            favoriteListLiveData.mutable().postValue(it)
        }
    }

    fun onVideoClick(favoriteMovieDto: FavoriteMovieDto) {
        selectedVideoLiveData.mutable().postValue(favoriteMovieDto)
    }
}