package com.illeyrocci.cityweather.data.repository

import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import com.illeyrocci.cityweather.data.remote.RemoteCityDataSourceImpl
import com.illeyrocci.cityweather.data.remote.ktor.ktor.getHttpClient
import com.illeyrocci.cityweather.data.remote.mapper.CityMapper
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.repository.CityRepository

class CityRepositoryImpl : CityRepository {

    private val remoteCityDataSource: RemoteCityDataSource =
        RemoteCityDataSourceImpl(getHttpClient())
    private val cityMapper = CityMapper()
    override suspend fun getCities() : Resource<List<City>> =
        Resource.Success(cityMapper.mapCityResponseListToCities(remoteCityDataSource.getCities()))
}