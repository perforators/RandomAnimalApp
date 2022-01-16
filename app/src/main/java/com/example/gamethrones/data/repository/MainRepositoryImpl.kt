package com.example.gamethrones.data.repository

import com.example.gamethrones.data.local.FavoriteAnimalDao
import com.example.gamethrones.data.remote.api.RandomAnimalAPI
import com.example.gamethrones.data.remote.services.image_loader_service.ImageLoaderService
import com.example.gamethrones.domain.model.Animal
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MainRepositoryImpl(
    private val api: RandomAnimalAPI,
    private val dao: FavoriteAnimalDao,
    private val imageLoaderService: ImageLoaderService
): MainRepository {

    override fun getRandomAnimal(): Flow<Resource<Animal>> = flow {
        emit(Resource.Loading())

        try {
            val animalDto = api.getRandomAnimal()
            val isFavorite = dao.isAnimalExists(animalDto.name)
            val imageBitmap = imageLoaderService.load(animalDto.image_link)
            val animal = animalDto.toAnimal(
                isFavorite = isFavorite,
                imageBitmap = imageBitmap
            )
            emit(Resource.Success(animal))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Network Error"
            ))
        } catch(e: Exception) {
            emit(Resource.Error(
                message = "Any Error"
            ))
        }
    }

}