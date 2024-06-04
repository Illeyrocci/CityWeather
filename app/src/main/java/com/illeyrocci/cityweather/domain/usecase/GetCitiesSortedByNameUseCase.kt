package com.illeyrocci.cityweather.domain.usecase

import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.repository.CityRepository
import javax.inject.Inject

class GetCitiesSortedByNameUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(): Resource<List<City>> =
        cityRepository.getCitiesSortedByName()
}