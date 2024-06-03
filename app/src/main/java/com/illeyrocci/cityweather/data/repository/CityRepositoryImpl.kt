package com.illeyrocci.cityweather.data.repository

import com.illeyrocci.cityweather.data.remote.RemoteCityDataSourceImpl
import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import com.illeyrocci.cityweather.data.remote.ktor.ktor.getHttpClient
import com.illeyrocci.cityweather.data.remote.mapper.CityMapper
import com.illeyrocci.cityweather.domain.repository.CityRepository

class CityRepositoryImpl : CityRepository, BaseKtorRepository() {

    private val remoteCityDataSource: RemoteCityDataSource =
        RemoteCityDataSourceImpl(getHttpClient())
    private val cityMapper = CityMapper()
    override suspend fun getCitiesSortedByName() = doWebRequest {
        cityMapper.mapCityResponseListToCities(remoteCityDataSource.getCities())
            .sortedBy { it.name }
    }

    override suspend fun getWeather(latitude: Double, longitude: Double) = doWebRequest {
        cityMapper.mapWeatherResponseToWeather(remoteCityDataSource.getWeather(latitude, longitude))
    }
}