package com.example.gamethrones.data.remotestore.dto

import com.example.gamethrones.domain.model.AnimalInfo

data class AnimalInfoDto(
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

    fun toAnimalInfo(): AnimalInfo {
        return AnimalInfo(
            name = name,
            animal_type = animal_type,
            diet = diet,
            habitat = habitat,
            image_link = image_link
        )
    }
}