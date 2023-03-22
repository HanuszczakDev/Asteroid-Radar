package com.hanuszczak.asteroidradar.model.repository

import com.hanuszczak.asteroidradar.model.dto.PictureOfDayDto
import com.hanuszczak.asteroidradar.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface ApiService {
    @GET("planetary/apod")
    suspend fun getPicture(
        @Query("api_key") apiKey: String
    ): PictureOfDayDto

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): String
}

object NasaApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}