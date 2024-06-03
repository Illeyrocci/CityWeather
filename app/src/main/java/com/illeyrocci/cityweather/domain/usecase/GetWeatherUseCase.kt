package com.illeyrocci.cityweather.domain.usecase

import com.illeyrocci.cityweather.domain.repository.CityRepository

class GetWeatherUseCase(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double) =
        cityRepository.getWeather(latitude, longitude)
}