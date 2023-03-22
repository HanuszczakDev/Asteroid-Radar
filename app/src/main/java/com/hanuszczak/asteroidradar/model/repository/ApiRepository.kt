package com.hanuszczak.asteroidradar.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.domain.PictureOfDay
import com.hanuszczak.asteroidradar.model.dto.asDatabaseModel
import com.hanuszczak.asteroidradar.model.entity.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiRepository (private val db: Db) {

    val picture: LiveData<PictureOfDay> = Transformations
        .map(db.pictureOfDayDao.getLastPictureOfDay()) {
            it?.asDomainModel(it)
        }

    suspend fun getPictureFromApi() {
        withContext(Dispatchers.IO) {
            val pictureOfDayDto = NasaApi.retrofitService.getPicture("0CR51QdIK9znPl7l9db8L4TTx5FYRrVUeyJMFo2H")
            db.pictureOfDayDao.insert(pictureOfDayDto.asDatabaseModel())
        }
    }
}