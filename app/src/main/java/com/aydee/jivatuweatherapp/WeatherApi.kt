package com.aydee.jivatuweatherapp

data class WeatherApi(
    val current: Current,
    val current_units: CurrentUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)