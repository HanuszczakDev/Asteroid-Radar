package com.hanuszczak.asteroidradar.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hanuszczak.asteroidradar.model.dao.AsteroidDao
import com.hanuszczak.asteroidradar.model.dao.PictureOfDayDao

class MainViewModelFactory(
    private val asteroidDao: AsteroidDao,
    private val pictureOfDayDao: PictureOfDayDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(asteroidDao, pictureOfDayDao) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}