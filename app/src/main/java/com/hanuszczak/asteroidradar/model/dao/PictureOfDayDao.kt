package com.hanuszczak.asteroidradar.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hanuszczak.asteroidradar.model.entity.PictureOfDayEntity

@Dao
interface PictureOfDayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictureOfDay: PictureOfDayEntity)

    @Update
    suspend fun update(pictureOfDay: PictureOfDayEntity)

    @Delete
    suspend fun delete(pictureOfDay: PictureOfDayEntity)

    @Query("SELECT * FROM picture_of_day ORDER BY id DESC LIMIT 1")
    fun getLastPictureOfDay(): LiveData<PictureOfDayEntity>
}