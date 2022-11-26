package com.example.myprojectjavaonkotlin.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myprojectjavaonkotlin.domain.entity.CollectionEntity
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.ui.utils.mutable

class FavoriteListViewModel(
    private val collectionVideoRepo: CollectionInteractor,
) : ViewModel() {

    //Сделали класс Factory (Фабрика)
    class Factory(
        private val collectionVideoRepo: CollectionInteractor,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoriteListViewModel(collectionVideoRepo) as T
        }
    }

    val inProgressLiveData: LiveData<Boolean> = MutableLiveData(false)
    val favoriteListLiveData: LiveData<List<CollectionEntity>> = MutableLiveData()
    val selectedVideoLiveData: LiveData<MovieDto> = MutableLiveData()

    init {
        inProgressLiveData.mutable().postValue(true)
        collectionVideoRepo.getCollections {
            inProgressLiveData.mutable().postValue(false)
            favoriteListLiveData.mutable().postValue(it)
        }
    }

    fun onVideoClick(movieDto: MovieDto) {
        selectedVideoLiveData.mutable().postValue(movieDto)
    }
}