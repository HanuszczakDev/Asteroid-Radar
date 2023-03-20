package com.hanuszczak.asteroidradar.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hanuszczak.asteroidradar.model.dao.AsteroidDao
import com.hanuszczak.asteroidradar.model.dao.PictureOfDayDao
import com.hanuszczak.asteroidradar.model.entity.AsteroidEntity
import com.hanuszczak.asteroidradar.model.entity.PictureOfDayEntity

@Database(entities = [AsteroidEntity::class, PictureOfDayEntity::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
    abstract val pictureOfDayDao: PictureOfDayDao

    companion object {
        @Volatile
        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Db::class.java,
                        "asteroid_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}