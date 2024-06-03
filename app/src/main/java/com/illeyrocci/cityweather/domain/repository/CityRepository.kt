package com.illeyrocci.cityweather.domain.repository

import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.model.Weather

interface CityRepository {

    suspend fun getCitiesSortedByName(): Resource<List<City>>

    suspend fun getWeather(latitude: Double, longitude: Double): Resource<Weather>
}