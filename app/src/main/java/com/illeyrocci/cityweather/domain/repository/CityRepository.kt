package com.illeyrocci.cityweather.domain.repository

import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.City

interface CityRepository {

    suspend fun getCities(): Resource<List<City>>
}