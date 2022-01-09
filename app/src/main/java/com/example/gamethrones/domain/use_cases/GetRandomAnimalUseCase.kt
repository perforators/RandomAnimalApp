package com.example.gamethrones.domain.use_cases

import com.example.gamethrones.domain.model.Animal
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.util.Resource
import kotlinx.coroutines.flow.Flow

class GetRandomAnimalUseCase(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Resource<Animal>> {
        return repository.getRandomAnimal()
    }
}