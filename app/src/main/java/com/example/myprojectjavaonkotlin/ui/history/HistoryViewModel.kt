package com.example.myprojectjavaonkotlin.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myprojectjavaonkotlin.App.Companion.getHistoryMovieViewingDao
import com.example.myprojectjavaonkotlin.AppStateMovie
import com.example.myprojectjavaonkotlin.data.room.HistoryLocalRepo
import com.example.myprojectjavaonkotlin.data.room.HistoryLocalRepoImpl

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppStateMovie> = MutableLiveData(),
    private val historyLocalRepo: HistoryLocalRepo = HistoryLocalRepoImpl(getHistoryMovieViewingDao())
) : ViewModel() {

//    //Сделали класс Factory (Фабрика)
//    class Factory(
//        private val historyRepository: HistoryLocalRepo
//    ) :
//        ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HistoryViewModel(historyRepository) as T
//        }
//    }

    fun getAllHistory() {
        historyLiveData.value = AppStateMovie.Loading
        historyLiveData.value = AppStateMovie.SuccessMovie(historyLocalRepo.getAllHistory())
    }

//    val inProgressLiveData: LiveData<Boolean> = MutableLiveData(false)


//    val historyLiveData: LiveData<List<HistoryMovieViewingEntity>> = MutableLiveData()

//    init {
//        inProgressLiveData.mutable().postValue(true)
//        historyLocalRepo.getAllHistory()
//            inProgressLiveData.mutable().postValue(false)
//            historyLiveData.mutable().postValue(it)
//    }
}