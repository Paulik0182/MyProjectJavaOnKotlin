package com.example.myprojectjavaonkotlin.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.myprojectjavaonkotlin.domain.interactor.AdultInteractor
import com.example.myprojectjavaonkotlin.ui.utils.mutable

private const val IS_ADULT_KEY = "IS_ADULT_KEY"
private const val SAVE_SETTINGS_KEY = "SAVE_SETTINGS_KEY"

class SharedPrefAdultInteractionImpl(
    context: Context
) : AdultInteractor {
    private val sharedPreferences =
        context.getSharedPreferences(SAVE_SETTINGS_KEY, Context.MODE_PRIVATE)

    override val isAdult: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isAdult.mutable().postValue(
            sharedPreferences.getBoolean(IS_ADULT_KEY, false)
        )
        // подписываемся на изменение (подписываемся навсегда)
        isAdult.observeForever {
            sharedPreferences.edit().putBoolean(IS_ADULT_KEY, it).apply()
        }
    }
}