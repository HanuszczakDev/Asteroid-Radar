package com.hanuszczak.asteroidradar.viewmodel.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.domain.Asteroid
import com.hanuszczak.asteroidradar.model.repository.ApiRepository
import kotlinx.coroutines.launch

enum class OptionMenu { SHOW_ALL, SHOW_TODAY, SHOW_WEEK }

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

    var optionMenu = MutableLiveData(OptionMenu.SHOW_WEEK)

    var asteroids: LiveData<List<Asteroid>?> = Transformations.switchMap(optionMenu) {
        when(it) {
            OptionMenu.SHOW_ALL -> repository.allAsteroids
            OptionMenu.SHOW_TODAY -> repository.asteroidOfToday
            else -> repository.asteroidsOfWeek
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToAsteroid.value = null
    }
}