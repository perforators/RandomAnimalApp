package com.example.gamethrones.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamethrones.data.local.entity.FavoriteAnimal

@Database(entities = [FavoriteAnimal::class], version = 1)
abstract class FavoriteAnimalDatabase: RoomDatabase() {

    abstract fun favoriteAnimalDao(): FavoriteAnimalDao
}