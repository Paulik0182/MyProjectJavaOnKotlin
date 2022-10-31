package com.example.myprojectjavaonkotlin.ui.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * extension-функции для Snackbar
 */

fun View.snack(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.ANIMATION_MODE_SLIDE
    ).show()
}