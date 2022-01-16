package com.example.gamethrones.data.remote.api.dto

import android.graphics.Bitmap
import com.example.gamethrones.domain.model.Animal

data class AnimalDto(
    val active_time: String,
    val animal_type: String,
    val diet: String,
    val geo_range: String,
    val habitat: String,
    val id: Int,
    val image_link: String,
    val latin_name: String,
    val length_max: String,
    val length_min: String,
    val lifespan: String,
    val name: String,
    val weight_max: String,
    val weight_min: String
) {

    fun toAnimal(isFavorite: Boolean, imageBitmap: Bitmap): Animal {
        return Animal(
            name = name,
            animalType = animal_type,
            diet = diet,
            habitat = habitat,
            imageBitmap = imageBitmap,
            isFavorite = isFavorite
        )
    }
}