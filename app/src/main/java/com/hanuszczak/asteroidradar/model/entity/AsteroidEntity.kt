package com.hanuszczak.asteroidradar.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid")
data class AsteroidEntity(
    @PrimaryKey
    var id: Long = 0L,
    @ColumnInfo(name = "code_name")
    var codename: String = "",
    @ColumnInfo(name = "close_approach_date")
    var closeApproachDate: String = "",
    @ColumnInfo(name = "absolute_magnitude")
    var absoluteMagnitude: Double = .0,
    @ColumnInfo(name = "estimated_diameter")
    var estimatedDiameter: Double = .0,
    @ColumnInfo(name = "relative_velocity")
    var relativeVelocity: Double = .0,
    @ColumnInfo(name = "distance_from_earth")
    var distanceFromEarth: Double = .0,
    @ColumnInfo(name = "is_potentially_hazardous")
    var isPotentiallyHazardous: Boolean = false
)