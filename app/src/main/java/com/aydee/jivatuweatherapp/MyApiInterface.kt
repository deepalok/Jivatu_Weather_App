package com.aydee.jivatuweatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiInterface {
    @GET("/v1/forecast")
    fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: Array<String>
    ) : Call<WeatherApi>
}