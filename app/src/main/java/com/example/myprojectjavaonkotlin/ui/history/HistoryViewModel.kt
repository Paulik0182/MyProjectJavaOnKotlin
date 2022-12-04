package com.example.myprojectjavaonkotlin.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myprojectjavaonkotlin.App.Companion.getHistoryDao
import com.example.myprojectjavaonkotlin.data.room.HistoryEntity
import com.example.myprojectjavaonkotlin.data.room.LocalRepo
import com.example.myprojectjavaonkotlin.data.room.LocalRepoImpl
import com.example.myprojectjavaonkotlin.ui.utils.mutable

class HistoryViewModel(
    private val historyRepository: LocalRepo = LocalRepoImpl(getHistoryDao())
) : ViewModel() {

    //Сделали класс Factory (Фабрика)
    class Factory(
        private val historyRepository: LocalRepo
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HistoryViewModel(historyRepository) as T
        }
    }

    val inProgressLiveData: LiveData<Boolean> = MutableLiveData(false)
    val historyLiveData: LiveData<List<HistoryEntity>> = MutableLiveData()

    init {
        inProgressLiveData.mutable().postValue(true)
        historyRepository.getAllHistory()
//            inProgressLiveData.mutable().postValue(false)
//            historyLiveData.mutable().postValue(it)

    }
}