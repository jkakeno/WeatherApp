package com.junkakeno.weatherapp.data.network

import com.junkakeno.weatherapp.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val API_KEY = "65d00499677e59496ca2f318eb68c049"
    }

    @GET("/data/2.5/forecast?appid=$API_KEY")
    suspend fun getForecast(@Query("q") city: String?): Response

}