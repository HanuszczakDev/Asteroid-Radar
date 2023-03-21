package com.hanuszczak.asteroidradar.model.domain

data class PictureOfDay(
    val id: Long,
    val mediaType: String,
    val title: String,
    val url: String
)