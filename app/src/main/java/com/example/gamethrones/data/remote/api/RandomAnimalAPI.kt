package com.example.gamethrones.data.remote.api

import com.example.gamethrones.data.remote.api.dto.AnimalDto
import retrofit2.http.GET

interface RandomAnimalAPI {

    @GET("/animals/rand")
    suspend fun getRandomAnimal(): AnimalDto

    companion object {
        const val BASE_URL = "https://zoo-animal-api.herokuapp.com/"
    }
}