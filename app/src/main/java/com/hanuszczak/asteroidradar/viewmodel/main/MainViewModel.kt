package com.hanuszczak.asteroidradar.viewmodel.main

import android.app.Application
import androidx.lifecycle.*
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.repository.ApiRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val database = Db.getInstance(application)
    private val repository = ApiRepository(database)

    private val _navigateToAsteroid= MutableLiveData<Long?>()
    val navigateToAsteroid: LiveData<Long?>
        get() = _navigateToAsteroid

    init {
        viewModelScope.launch {
            repository.getPictureFromApi()
            repository.getAsteroidsFromApi()
        }
    }

    val pictureOfDay = repository.picture

    val asteroids = repository.asteroids

    fun onAsteroidClicked(id: Long) {
        _navigateToAsteroid.value = id
    }
}