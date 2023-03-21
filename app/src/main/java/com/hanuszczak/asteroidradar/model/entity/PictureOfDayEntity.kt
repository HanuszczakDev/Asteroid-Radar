package com.hanuszczak.asteroidradar.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hanuszczak.asteroidradar.model.domain.PictureOfDay

@Entity(tableName = "picture_of_day")
data class PictureOfDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "media_type")
    val mediaType: String = "",
    val title: String = "",
    val url: String = ""
)

fun PictureOfDayEntity.asDomainModel(pictureEntity: PictureOfDayEntity): PictureOfDay {
    return PictureOfDay(
        id = pictureEntity.id,
        mediaType = pictureEntity.mediaType,
        title = pictureEntity.title,
        url = pictureEntity.url
    )
}