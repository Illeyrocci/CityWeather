package com.illeyrocci.cityweather.data.remote

import com.illeyrocci.cityweather.common.Constants.RELATIVE_PATH_FOR_CITIES
import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import com.illeyrocci.cityweather.data.remote.ktor.ktor.getJson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.builtins.ListSerializer

class RemoteCityDataSourceImpl(
    private val client: HttpClient
) : RemoteCityDataSource {

    override suspend fun getCities(): List<CityResponse> {
        val httpRequest = client.get<HttpStatement> {
            url {
                path(RELATIVE_PATH_FOR_CITIES)
            }
        }

        return getJson().decodeFromString(
            ListSerializer(CityResponse.serializer()),
            httpRequest.execute().readText()
        )
    }
}