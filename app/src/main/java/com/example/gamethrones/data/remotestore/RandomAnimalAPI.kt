package com.example.gamethrones.data.remotestore

import com.example.gamethrones.data.remotestore.dto.AnimalDto
import retrofit2.http.GET

interface RandomAnimalAPI {

    @GET("/animals/rand")
    suspend fun getRandomAnimal(): AnimalDto

    companion object {
        const val BASE_URL = "https://zoo-animal-api.herokuapp.com/"
    }
}