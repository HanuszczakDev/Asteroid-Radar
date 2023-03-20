package com.hanuszczak.asteroidradar.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.hanuszczak.asteroidradar.model.entity.PictureOfDayEntity

@Dao
interface PictureOfDayDao {
    @Insert
    suspend fun insert(pictureOfDay: PictureOfDayEntity)

    @Update
    suspend fun update(pictureOfDay: PictureOfDayEntity)

    @Delete
    suspend fun delete(pictureOfDay: PictureOfDayEntity)
}