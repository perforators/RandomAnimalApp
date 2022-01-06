package com.example.gamethrones.di

import com.example.gamethrones.data.remotestore.RandomAnimalAPI
import com.example.gamethrones.data.repository.MainRepositoryImpl
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.domain.use_cases.GetRandomAnimalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRandomAnimalAPI(): RandomAnimalAPI {
        return Retrofit.Builder()
            .baseUrl(RandomAnimalAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomAnimalAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(api: RandomAnimalAPI): MainRepository {
        return MainRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetRandomAnimalUseCase(repository: MainRepository): GetRandomAnimalUseCase {
        return GetRandomAnimalUseCase(repository)
    }
}