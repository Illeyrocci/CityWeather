package com.illeyrocci.cityweather.data.remote.ktor

import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.dto.WeatherResponse

interface RemoteCityDataSource {

    suspend fun getCities(): List<CityResponse>

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse
}