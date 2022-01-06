package com.example.gamethrones.data.repository

import com.example.gamethrones.data.remotestore.RandomAnimalAPI
import com.example.gamethrones.domain.model.AnimalInfo
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MainRepositoryImpl(
    private val api: RandomAnimalAPI
): MainRepository {

    override fun getRandomAnimal(): Flow<Resource<AnimalInfo>> = flow {
        emit(Resource.Loading())

        try {
            val animalInfo = api.getRandomAnimal().toAnimalInfo()
            emit(Resource.Success(animalInfo))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Error"
            ))
        }
    }

}