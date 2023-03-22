package com.hanuszczak.asteroidradar.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hanuszczak.asteroidradar.model.entity.AsteroidEntity

@Dao
interface AsteroidDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(AsteroidsArray: Array<AsteroidEntity>)

    @Update
    suspend fun update(asteroid: AsteroidEntity)

    @Delete
    suspend fun delete(asteroid: AsteroidEntity)

    @Query("SELECT * FROM asteroid ORDER BY id DESC")
    fun getAll(): LiveData<List<AsteroidEntity>>
}