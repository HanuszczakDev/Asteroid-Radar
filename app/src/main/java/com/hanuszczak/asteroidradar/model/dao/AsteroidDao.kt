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

    @Query("SELECT * FROM asteroid WHERE close_approach_date BETWEEN :currentDate and :endDate ORDER BY close_approach_date ASC")
    fun getWeek(currentDate: String, endDate: String): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid WHERE close_approach_date = :currentDate ORDER BY close_approach_date ASC")
    fun getToday(currentDate: String): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid ORDER BY close_approach_date ASC")
    fun getAll(): LiveData<List<AsteroidEntity>>
}