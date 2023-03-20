package com.hanuszczak.asteroidradar.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.hanuszczak.asteroidradar.model.entity.AsteroidEntity

@Dao
interface AsteroidDao{
    @Insert
    suspend fun insert(asteroid: AsteroidEntity)

    @Update
    suspend fun update(asteroid: AsteroidEntity)

    @Delete
    suspend fun delete(asteroid: AsteroidEntity)
}