package com.hanuszczak.asteroidradar.viewmodel.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.domain.Asteroid
import com.hanuszczak.asteroidradar.model.repository.ApiRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val database = Db.getInstance(application)
    private val repository = ApiRepository(database)

    private val _navigateToAsteroid= MutableLiveData<Asteroid?>()
    val navigateToAsteroid: LiveData<Asteroid?>
        get() = _navigateToAsteroid

    init {
        viewModelScope.launch {
            try {
                repository.getPictureFromApi()
                repository.getAsteroidsFromApi()
            } catch (e: java.lang.Exception) {
                Log.e("MainViewModel", "exception in init block: ${e.localizedMessage}")
            }
        }
    }

    val pictureOfDay = repository.picture

    val asteroids = repository.asteroids

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToAsteroid.value = null
    }
}