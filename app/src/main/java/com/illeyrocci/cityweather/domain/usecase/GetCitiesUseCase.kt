package com.illeyrocci.cityweather.domain.usecase

import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.repository.CityRepository

class GetCitiesUseCase(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(): Resource<List<City>> =
        cityRepository.getCities()
}