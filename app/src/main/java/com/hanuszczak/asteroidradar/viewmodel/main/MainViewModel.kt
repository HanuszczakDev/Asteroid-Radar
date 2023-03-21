package com.hanuszczak.asteroidradar.viewmodel.main

import android.app.Application
import androidx.lifecycle.*
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.dao.AsteroidDao
import com.hanuszczak.asteroidradar.model.dao.PictureOfDayDao
import com.hanuszczak.asteroidradar.model.dto.PictureOfDayDto
import com.hanuszczak.asteroidradar.model.repository.ApiRepository
import com.hanuszczak.asteroidradar.model.repository.NasaApi
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
//    private val _pictureOfDay = MutableLiveData<PictureOfDayDto>()
//    val pictureOfDay: LiveData<PictureOfDayDto>
//        get() = _pictureOfDay

    private val database = Db.getInstance(application)
    private val repository = ApiRepository(database)

    init {
        viewModelScope.launch { repository.getPictureFromApi() }
    }

    val pictureOfDay = repository.picture

//    private fun getPicture() {
//        viewModelScope.launch {
//            try {
////                _pictureOfDay.value = NasaApi.retrofitService.getPicture("0CR51QdIK9znPl7l9db8L4TTx5FYRrVUeyJMFo2H")
//
//
//            } catch (e: Exception) {
//                println(e.message)
//            }
//        }
//    }
}