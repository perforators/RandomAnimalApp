package com.example.gamethrones.domain.model

data class Animal(
    val name: String,
    val animalType: String,
    val diet: String,
    val habitat: String,
    val imageLink: String,
    val isFavorite: Boolean
)