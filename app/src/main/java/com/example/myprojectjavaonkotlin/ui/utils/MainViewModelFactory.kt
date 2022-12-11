package com.example.myprojectjavaonkotlin.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import com.example.myprojectjavaonkotlin.ui.video.VideoListViewModel

class MainViewModelFactory(
    private val collectionVideoRepo: CollectionInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoListViewModel::class.java)) {
            return VideoListViewModel(collectionVideoRepo) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}