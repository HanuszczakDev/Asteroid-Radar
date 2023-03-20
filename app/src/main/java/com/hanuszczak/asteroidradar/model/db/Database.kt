package com.hanuszczak.asteroidradar.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hanuszczak.asteroidradar.model.dao.AsteroidDao
import com.hanuszczak.asteroidradar.model.dao.PictureOfDayDao

abstract class Database : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
    abstract val pictureOfDayDap: PictureOfDayDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "asteroid_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}