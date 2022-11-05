package com.example.myprojectjavaonkotlin.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.repo.MovieDtoRepo
import com.example.myprojectjavaonkotlin.ui.utils.mutable

/**
 * liveData - это такой тип данных который позволяет подписатся на него и все время получать изменения
 * Тмкже liveData умеет кэшировать значения
 *
 * liveData нельзя создать (это абстрактный класс), но у него есть несколько наследников
 * postValue работает с многопоточностью, из любого потока делаем postValue и приходит все на главный поток
 */

class DetailsViewModel(
    //Аргумент конструктора, член класса
    private val movieDtoRepo: MovieDtoRepo,
    private val videoId: String
) {

    val videoLiveData: LiveData<MovieDto> = MutableLiveData()

    init {
        //проверяе на наличие данных в videoLiveData.
        // Это необходимо для того чтобы при повороте данные не закачивались заново
        if (videoLiveData.value == null) {
            movieDtoRepo.getMovie(videoId) {
                it.let {
                    videoLiveData.mutable().postValue(it)
                }
            }
        }
    }
}