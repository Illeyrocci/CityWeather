package com.illeyrocci.cityweather

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class CityResponse(
    val id: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)

interface RemoteDataSource {

    suspend fun getCities(): List<CityResponse>
}

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