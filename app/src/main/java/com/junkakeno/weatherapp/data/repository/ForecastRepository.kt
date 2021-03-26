package com.junkakeno.weatherapp.data.repository

import com.junkakeno.weatherapp.data.network.Retrofit

class ForecastRepository {

    private val service = Retrofit.getForecastService()

    suspend fun getForecast(city:String) = service.getForecast(city)

}