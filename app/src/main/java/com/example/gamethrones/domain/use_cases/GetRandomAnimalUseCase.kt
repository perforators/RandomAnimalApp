package com.example.gamethrones.domain.use_cases

import com.example.gamethrones.domain.model.AnimalInfo
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.util.Resource
import kotlinx.coroutines.flow.Flow

class GetRandomAnimalUseCase(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Resource<AnimalInfo>> {
        return repository.getRandomAnimal()
    }
}