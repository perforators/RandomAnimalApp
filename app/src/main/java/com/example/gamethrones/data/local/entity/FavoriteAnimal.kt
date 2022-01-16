package com.example.gamethrones.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_animals")
data class FavoriteAnimal(
    @PrimaryKey
    val name: String,
    val animalType: String,
    val diet: String,
    val habitat: String,
    val imageLink: String
)