package com.example.gamethrones.data.localstore

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FavoriteAnimalDao {

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_animals WHERE name = :name)")
    fun isAnimalExists(name: String): Boolean
}