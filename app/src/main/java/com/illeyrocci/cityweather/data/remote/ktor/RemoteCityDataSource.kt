package com.illeyrocci.cityweather.data.remote.ktor

import com.illeyrocci.cityweather.data.remote.dto.CityResponse

interface RemoteCityDataSource {

    suspend fun getCities(): List<CityResponse>
}