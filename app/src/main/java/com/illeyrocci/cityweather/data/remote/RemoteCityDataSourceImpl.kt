package com.illeyrocci.cityweather.data.remote

import com.illeyrocci.cityweather.common.Constants.CITY_HOST
import com.illeyrocci.cityweather.common.Constants.RELATIVE_PATH_FOR_CITIES
import com.illeyrocci.cityweather.common.Constants.RELATIVE_PATH_FOR_WEATHER
import com.illeyrocci.cityweather.common.Constants.WEATHER_HOST
import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.dto.WeatherResponse
import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import com.illeyrocci.cityweather.data.remote.ktor.ktor.getJson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.builtins.ListSerializer

class RemoteCityDataSourceImpl(
    private val client: HttpClient
) : RemoteCityDataSource {

    override suspend fun getCities(): List<CityResponse> {
        val httpRequest = client.get<HttpStatement> {
            host = CITY_HOST
            url {
                path(RELATIVE_PATH_FOR_CITIES)
            }
        }

        return getJson().decodeFromString(
            ListSerializer(CityResponse.serializer()),
            httpRequest.execute().readText()
        )
    }

    override suspend fun getWeather(): WeatherResponse {
        val httpRequest = client.get<HttpStatement> {
            host = WEATHER_HOST
            url {
                path(RELATIVE_PATH_FOR_WEATHER)
            }
        }

        return getJson().decodeFromString(
            WeatherResponse.serializer(),
            httpRequest.execute().readText()
        )
    }
}