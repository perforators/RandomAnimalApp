package com.example.gamethrones.domain.model

import android.graphics.Bitmap
import com.example.gamethrones.BuildConfig

data class Animal(
    val name: String,
    val animalType: String,
    val diet: String,
    val habitat: String,
    val imageBitmap: Bitmap?,
    val isFavorite: Boolean
) {

    companion object{

        val EMPTY = Animal(
            name = "",
            animalType = "",
            diet = "",
            habitat = "",
            imageBitmap = null,
            isFavorite = false
        )
    }
}