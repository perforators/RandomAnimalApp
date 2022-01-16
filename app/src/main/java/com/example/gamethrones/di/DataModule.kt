package com.example.gamethrones.di

import android.content.Context
import androidx.room.Room
import com.example.gamethrones.data.local.FavoriteAnimalDao
import com.example.gamethrones.data.local.FavoriteAnimalDatabase
import com.example.gamethrones.data.remote.api.RandomAnimalAPI
import com.example.gamethrones.data.remote.services.image_loader_service.ImageCache
import com.example.gamethrones.data.remote.services.image_loader_service.ImageLoaderService
import com.example.gamethrones.data.remote.services.image_loader_service.ImageLoaderServiceImpl
import com.example.gamethrones.data.repository.MainRepositoryImpl
import com.example.gamethrones.domain.repository.MainRepository
import com.example.gamethrones.util.DB_NAME
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
class DataModule {

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
        dao: FavoriteAnimalDao,
        imageLoaderService: ImageLoaderService
    ): MainRepository {
        return MainRepositoryImpl(api, dao, imageLoaderService)
    }

    @Provides
    @Singleton
    fun provideFavoriteAnimalDatabase(
        @ApplicationContext context: Context
    ): FavoriteAnimalDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteAnimalDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteAnimalDao(database: FavoriteAnimalDatabase): FavoriteAnimalDao {
        return database.favoriteAnimalDao()
    }

    @Provides
    @Singleton
    fun provideImageLoaderService(imageCache: ImageCache): ImageLoaderService {
        return ImageLoaderServiceImpl(imageCache)
    }

    @Provides
    @Singleton
    fun provideImageCache(): ImageCache {
        return ImageCache()
    }
}