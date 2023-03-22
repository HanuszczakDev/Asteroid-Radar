package com.hanuszczak.asteroidradar.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.domain.Asteroid
import com.hanuszczak.asteroidradar.model.domain.PictureOfDay
import com.hanuszczak.asteroidradar.model.dto.asteroidsAsDatabaseModel
import com.hanuszczak.asteroidradar.model.dto.pictureAsDatabaseModel
import com.hanuszczak.asteroidradar.model.entity.asDomainModel
import com.hanuszczak.asteroidradar.util.Constants
import com.hanuszczak.asteroidradar.util.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class ApiRepository (private val db: Db) {

    val picture: LiveData<PictureOfDay> = Transformations
        .map(db.pictureOfDayDao.getLastPictureOfDay()) {
            it?.asDomainModel(it)
        }

    val asteroids: LiveData<List<Asteroid>> = Transformations
        .map(db.asteroidDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun getPictureFromApi() {
        withContext(Dispatchers.IO) {
            val pictureDto = NasaApi.retrofitService.getPicture(Constants.API_KEY)
            db.pictureOfDayDao.insert(pictureAsDatabaseModel(pictureDto))
        }
    }

    suspend fun getAsteroidsFromApi() {
        withContext(Dispatchers.IO) {
            val asteroidsResponse = NasaApi.retrofitService.getAsteroids(
                "2023-03-22", "2023-03-22", Constants.API_KEY)
            val jsonObject = JSONObject(asteroidsResponse)
            val parsedAsteroidList = parseAsteroidsJsonResult(jsonObject)
            db.asteroidDao.insertAll(asteroidsAsDatabaseModel(parsedAsteroidList))
        }
    }
}