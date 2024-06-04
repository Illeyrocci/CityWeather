package com.illeyrocci.cityweather.data.remote

import com.illeyrocci.cityweather.common.Constants.OPENWEATHERMAP_API_KEY
import com.illeyrocci.cityweather.common.Constants.CITY_HOST
import com.illeyrocci.cityweather.common.Constants.RELATIVE_PATH_FOR_CITIES
import com.illeyrocci.cityweather.common.Constants.RELATIVE_PATH_FOR_WEATHER
import com.illeyrocci.cityweather.common.Constants.WEATHER_HOST
import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.dto.WeatherResponse
import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RemoteCityDataSourceImpl @Inject constructor(
    private val client: HttpClient,
    private val json: Json
) : RemoteCityDataSource {

    override suspend fun getCities(): List<CityResponse> {
        val httpRequest = client.get<HttpStatement> {
            host = CITY_HOST
            url(RELATIVE_PATH_FOR_CITIES)
        }

        return json.decodeFromString(
            ListSerializer(CityResponse.serializer()),
            httpRequest.execute().readText()
        )
    }

    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse {
        val httpRequest = client.get<HttpStatement> {
            host = WEATHER_HOST
            url(RELATIVE_PATH_FOR_WEATHER)
            parameter("appid", OPENWEATHERMAP_API_KEY)
            parameter("lon", longitude)
            parameter("lat", latitude)
        }

        return json.decodeFromString(
            WeatherResponse.serializer(),
            httpRequest.execute().readText()
        )
    }
}