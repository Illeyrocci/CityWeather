package com.illeyrocci.cityweather.data.remote.ktor

import com.illeyrocci.cityweather.data.remote.dto.CityResponse
import com.illeyrocci.cityweather.data.remote.ktor.ktor.getJson
import com.illeyrocci.cityweather.data.remote.relativePathForCities
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.builtins.ListSerializer

class RemoteDataSourceImpl(
    private val client: HttpClient
) : RemoteDataSource {

    override suspend fun getCities(): List<CityResponse> {
        val httpRequest = client.get<HttpStatement> {
            url {
                path(relativePathForCities)
            }
        }

        return getJson().decodeFromString(
            ListSerializer(CityResponse.serializer()),
            httpRequest.execute().readText()
        )
    }
}