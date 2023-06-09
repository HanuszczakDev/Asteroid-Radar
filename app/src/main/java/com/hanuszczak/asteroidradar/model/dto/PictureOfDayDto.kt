package com.hanuszczak.asteroidradar.model.dto

import com.hanuszczak.asteroidradar.model.entity.PictureOfDayEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureDtoContainer(val pictureDto: PictureOfDayDto)

@JsonClass(generateAdapter = true)
data class PictureOfDayDto(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)

fun pictureAsDatabaseModel(pictureDto: PictureOfDayDto): PictureOfDayEntity {
    return PictureOfDayEntity(
        mediaType = pictureDto.mediaType,
        title = pictureDto.title,
        url = pictureDto.url
    )
}