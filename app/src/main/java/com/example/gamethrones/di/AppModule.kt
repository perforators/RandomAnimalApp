package com.example.gamethrones.di

import android.content.Context
import androidx.room.Room
import com.example.gamethrones.data.localstore.FavoriteAnimalDao
import com.example.gamethrones.data.localstore.FavoriteAnimalDatabase
import com.example.gamethrones.data.localstore.entity.FavoriteAnimal
import com.example.gamethrones.data.remotestore.RandomAnimalAPI
import com.example.gamethrones.data.repository.MainRepositoryImpl
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.domain.use_cases.GetRandomAnimalUseCase
import com.example.gamethrones.util.dbName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMainRepository(
        api: RandomAnimalAPI,
        dao: FavoriteAnimalDao
    ): MainRepository {
        return MainRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetRandomAnimalUseCase(repository: MainRepository): GetRandomAnimalUseCase {
        return GetRandomAnimalUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFavoriteAnimalDatabase(
        @ApplicationContext context: Context
    ): FavoriteAnimalDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteAnimalDatabase::class.java,
            dbName
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteAnimalDao(database: FavoriteAnimalDatabase): FavoriteAnimalDao {
        return database.favoriteAnimalDao()
    }
}