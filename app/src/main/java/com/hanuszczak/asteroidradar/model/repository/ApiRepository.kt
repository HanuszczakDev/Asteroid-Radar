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
import java.text.SimpleDateFormat
import java.util.*

class ApiRepository (private val db: Db) {

    val picture: LiveData<PictureOfDay> = Transformations
        .map(db.pictureOfDayDao.getLastPictureOfDay()) {
            it?.asDomainModel(it)
        }

    val allAsteroids: LiveData<List<Asteroid>> = Transformations
        .map(db.asteroidDao.getAll(currentDate())) {
            it.asDomainModel()
        }

    val asteroidsOfWeek: LiveData<List<Asteroid>> = Transformations
        .map(db.asteroidDao.getWeek(onwardDate(Constants.ONE_DAY), onwardDate(Constants.SEVEN_DAYS))) {
            it.asDomainModel()
        }

    val asteroidOfToday: LiveData<List<Asteroid>> = Transformations
        .map(db.asteroidDao.getToday(currentDate())) {
            it.asDomainModel()
        }

    suspend fun getPictureFromApi() {
        withContext(Dispatchers.IO) {
            val pictureDto = NasaApi.retrofitService.getPicture(Constants.API_KEY)
            db.pictureOfDayDao.insert(pictureAsDatabaseModel(pictureDto))
        }
    }

    suspend fun getAsteroidsFromApi(plusDays: Int = 0) {
        withContext(Dispatchers.IO) {
            val asteroidsResponse = NasaApi.retrofitService.getAsteroids(
                onwardDate(plusDays), onwardDate(Constants.SEVEN_DAYS), Constants.API_KEY)
            val jsonObject = JSONObject(asteroidsResponse)
            val parsedAsteroidList = parseAsteroidsJsonResult(jsonObject)
            db.asteroidDao.insertAll(asteroidsAsDatabaseModel(parsedAsteroidList))
        }
    }

    private fun currentDate(): String = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT)
        .format(Calendar.getInstance().time)

    private fun onwardDate(plusDays: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, plusDays)
        return SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT).format(calendar.time)
    }
}