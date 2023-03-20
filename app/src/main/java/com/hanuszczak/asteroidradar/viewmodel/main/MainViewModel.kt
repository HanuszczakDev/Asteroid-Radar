package com.hanuszczak.asteroidradar.viewmodel.main

import androidx.lifecycle.ViewModel
import com.hanuszczak.asteroidradar.model.dao.AsteroidDao
import com.hanuszczak.asteroidradar.model.dao.PictureOfDayDao

class MainViewModel(
    private val asteroidDao: AsteroidDao,
    private val pictureOfDayDao: PictureOfDayDao
) : ViewModel() {

}