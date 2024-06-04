package com.illeyrocci.cityweather.data.remote.mapper

import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.dto.WeatherResponse
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.model.Weather
import javax.inject.Inject

class CityMapper @Inject constructor() {
    private fun mapCityResponseToCity(cityResponse: CityResponse): City = with(cityResponse) {
        City(if (id.isBlank()) null else id.toInt(), city.ifBlank { null }, latitude, longitude)
    }

    fun mapCityResponseListToCities(cityResponseList: List<CityResponse>): List<City> =
        cityResponseList.map { mapCityResponseToCity(it) }

    fun mapWeatherResponseToWeather(weatherResponse: WeatherResponse): Weather =
        Weather(weatherResponse.main.temp)
}