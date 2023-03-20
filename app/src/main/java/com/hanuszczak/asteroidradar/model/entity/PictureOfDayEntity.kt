package com.hanuszczak.asteroidradar.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "picture_of_day")
data class PictureOfDayEntity(
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)