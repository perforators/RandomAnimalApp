package com.example.gamethrones.di

import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.domain.use_cases.GetRandomAnimalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGetRandomAnimalUseCase(repository: MainRepository): GetRandomAnimalUseCase {
        return GetRandomAnimalUseCase(repository)
    }
}