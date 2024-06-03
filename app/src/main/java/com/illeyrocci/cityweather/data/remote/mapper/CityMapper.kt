package com.illeyrocci.cityweather.data.remote.mapper

import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.dto.WeatherResponse
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.model.Weather

class CityMapper {
    private fun mapCityResponseToCity(cityResponse: CityResponse): City =
        City(cityResponse.city, cityResponse.latitude, cityResponse.longitude)

    fun mapCityResponseListToCities(cityResponseList: List<CityResponse>): List<City> =
        cityResponseList.filter { it.city != "" }.map { mapCityResponseToCity(it) }

    fun mapWeatherResponseToWeather(weatherResponse: WeatherResponse): Weather =
        Weather(weatherResponse.main.temp)
}