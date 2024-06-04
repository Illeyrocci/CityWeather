package com.illeyrocci.cityweather.data.repository

import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import com.illeyrocci.cityweather.data.remote.mapper.CityMapper
import com.illeyrocci.cityweather.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val remoteCityDataSource: RemoteCityDataSource,
    private val cityMapper: CityMapper
) : CityRepository, BaseKtorRepository() {

    override suspend fun getCitiesSortedByName() = doWebRequest {
        cityMapper.mapCityResponseListToCities(remoteCityDataSource.getCities())
            .filter { !it.name.isNullOrBlank() }.sortedBy { it.name }
    }

    override suspend fun getWeather(latitude: Double, longitude: Double) = doWebRequest {
        cityMapper.mapWeatherResponseToWeather(remoteCityDataSource.getWeather(latitude, longitude))
    }
}