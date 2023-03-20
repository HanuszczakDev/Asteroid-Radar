package com.hanuszczak.asteroidradar.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanuszczak.asteroidradar.model.dao.AsteroidDao
import com.hanuszczak.asteroidradar.model.dao.PictureOfDayDao
import com.hanuszczak.asteroidradar.model.data.PictureOfDay
import com.hanuszczak.asteroidradar.model.repository.NasaApi
import kotlinx.coroutines.launch

class MainViewModel(
    private val asteroidDao: AsteroidDao,
    private val pictureOfDayDao: PictureOfDayDao
) : ViewModel() {
    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    init {
        getPicture()
    }

    private fun getPicture() {
        viewModelScope.launch {
            try {
                _pictureOfDay.value = NasaApi.retrofitService.getPicture("XXX")
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}