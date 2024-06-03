package com.illeyrocci.cityweather.data.remote.mapper

import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.domain.model.City

class CityMapper {
    private fun mapCityResponseToCity(cityResponse: CityResponse): City = City(cityResponse.city)

    fun mapCityResponseListToCities(cityResponseList: List<CityResponse>): List<City> =
        cityResponseList.filter { it.city != "" }.map { mapCityResponseToCity(it) }
}