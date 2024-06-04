package com.illeyrocci.cityweather.domain.usecase

import com.illeyrocci.cityweather.domain.repository.CityRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double) =
        cityRepository.getWeather(latitude, longitude)
}