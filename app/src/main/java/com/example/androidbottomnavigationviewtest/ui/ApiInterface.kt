package com.example.androidbottomnavigationviewtest.ui

import com.example.androidbottomnavigationviewtest.ui.model.currentWeather.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): Response<CurrentWeather>
}