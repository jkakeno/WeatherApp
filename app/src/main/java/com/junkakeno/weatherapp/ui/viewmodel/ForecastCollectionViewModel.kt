package com.junkakeno.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.junkakeno.weatherapp.data.repository.ForecastRepository
import com.junkakeno.weatherapp.data.util.Resource
import kotlinx.coroutines.Dispatchers

class ForecastCollectionViewModel : ViewModel() {

    private val repository = ForecastRepository()

    fun getForecast(city:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getForecast(city)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = "No forecast found for $city. \nTry a different city..."))
        }
    }

}