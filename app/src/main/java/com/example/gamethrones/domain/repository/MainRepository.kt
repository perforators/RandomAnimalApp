package com.example.gamethrones.domain.repository

import com.example.gamethrones.domain.model.AnimalInfo
import com.example.gamethrones.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getRandomAnimal(): Flow<Resource<AnimalInfo>>
}