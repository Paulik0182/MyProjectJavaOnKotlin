package com.example.myprojectjavaonkotlin.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myprojectjavaonkotlin.domain.entity.CollectionVideoEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.CollectionVideoRepo

/**
 * liveData - это такой тип данных который позволяет подписатся на него и все время получать изменения
 * Тмкже liveData умеет кэшировать значения
 *
 * liveData нельзя создать (это абстрактный класс), но у него есть несколько наследников
 * postValue работает с многопоточностью, из любого потока делаем postValue и приходит все на главный поток
 */

class VideoListViewModel(
    //Аргумент конструктора, член класса
    private val collectionVideoRepo: CollectionVideoRepo
) {

    val videoListLiveData: LiveData<List<CollectionVideoEntity>> = MutableLiveData()
    val selectedVideoLiveData: LiveData<VideoEntity> = MutableLiveData()

    init {
        collectionVideoRepo.getCollectionVideos{
            videoListLiveData.mutable().postValue(it)
        }
    }

    fun onVideoClick(videoEntity: VideoEntity){
        selectedVideoLiveData.mutable().postValue(videoEntity)
    }

    //экстеншен (расширение обычной чужой функции). Можно указать mutable расширение и оно вернет версию MutableLiveData
    //это сделано чтобы во фрагменте случайно не изменить список (в этом рельной безописности нет)
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData
    }
}