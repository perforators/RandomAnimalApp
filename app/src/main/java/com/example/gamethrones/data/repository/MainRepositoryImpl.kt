package com.example.gamethrones.data.repository

import com.example.gamethrones.data.localstore.FavoriteAnimalDao
import com.example.gamethrones.data.remotestore.RandomAnimalAPI
import com.example.gamethrones.domain.model.Animal
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MainRepositoryImpl(
    private val api: RandomAnimalAPI,
    private val dao: FavoriteAnimalDao
): MainRepository {

    override fun getRandomAnimal(): Flow<Resource<Animal>> = flow {
        emit(Resource.Loading())

        try {
            val animalDto = api.getRandomAnimal()
            val isFavorite = dao.isAnimalExists(animalDto.name)
            val animal = animalDto.toAnimal(isFavorite)
            emit(Resource.Success(animal))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Error"
            ))
        }
    }

}