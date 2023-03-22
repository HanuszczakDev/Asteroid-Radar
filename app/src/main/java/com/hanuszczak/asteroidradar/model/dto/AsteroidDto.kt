package com.hanuszczak.asteroidradar.model.dto

import com.hanuszczak.asteroidradar.model.domain.Asteroid
import com.hanuszczak.asteroidradar.model.entity.AsteroidEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AsteroidDtoContainer(val asteroidsDto: List<AsteroidDto>)

@JsonClass(generateAdapter = true)
data class AsteroidDto(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

fun AsteroidDtoContainer.asDomainModel(): List<Asteroid> {
    return asteroidsDto.map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun AsteroidDtoContainer.asDatabaseModel(): Array<AsteroidEntity> {
    return asteroidsDto.map {
        AsteroidEntity(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}