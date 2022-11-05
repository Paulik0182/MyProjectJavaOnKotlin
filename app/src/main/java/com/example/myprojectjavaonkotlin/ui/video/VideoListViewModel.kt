package com.example.myprojectjavaonkotlin.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myprojectjavaonkotlin.domain.entity.CollectionEntity
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.ui.utils.mutable

/**
 * liveData - это такой тип данных который позволяет подписатся на него и все время получать изменения
 * Тмкже liveData умеет кэшировать значения
 *
 * liveData нельзя создать (это абстрактный класс), но у него есть несколько наследников
 * postValue работает с многопоточностью, из любого потока делаем postValue и приходит все на главный поток
 */

class VideoListViewModel(
    //Аргумент конструктора, член класса
    private val collectionVideoRepo: CollectionInteractor
) {

    val videoListLiveData: LiveData<List<CollectionEntity>> = MutableLiveData()
    val selectedVideoLiveData: LiveData<MovieDto> = MutableLiveData()

    init {
        collectionVideoRepo.getComingSoonCollection {
            videoListLiveData.mutable().postValue(it)
        }
    }

    fun onVideoClick(movieDto: MovieDto) {
        selectedVideoLiveData.mutable().postValue(movieDto)
    }
}