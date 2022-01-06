package com.example.gamethrones.data.remotestore

import com.example.gamethrones.data.remotestore.dto.AnimalInfoDto
import retrofit2.http.GET

interface RandomAnimalAPI {

    @GET("/animals/rand")
    suspend fun getRandomAnimal(): AnimalInfoDto

    companion object {
        const val BASE_URL = "https://zoo-animal-api.herokuapp.com/"
    }
}