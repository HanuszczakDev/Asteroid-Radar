package com.hanuszczak.asteroidradar.model.dto

import com.hanuszczak.asteroidradar.model.domain.PictureOfDay
import com.hanuszczak.asteroidradar.model.entity.PictureOfDayEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureDtoContainer(val pictureDto: PictureOfDayDto)

@JsonClass(generateAdapter = true)
data class PictureOfDayDto(
    val id: Long,
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)

fun PictureDtoContainer.asDomainModel(): PictureOfDay {
    return PictureOfDay(
        id = pictureDto.id,
        mediaType = pictureDto.mediaType,
        title = pictureDto.title,
        url = pictureDto.url
    )
}

fun PictureDtoContainer.asDatabaseModel(): PictureOfDayEntity {
    return PictureOfDayEntity(
        id = pictureDto.id,
        mediaType = pictureDto.mediaType,
        title = pictureDto.title,
        url = pictureDto.url
    )
}