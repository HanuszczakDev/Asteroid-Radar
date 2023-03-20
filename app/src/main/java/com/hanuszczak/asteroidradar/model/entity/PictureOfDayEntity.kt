package com.hanuszczak.asteroidradar.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_of_day")
data class PictureOfDayEntity(
    @PrimaryKey
    val id: Long = 0L,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)