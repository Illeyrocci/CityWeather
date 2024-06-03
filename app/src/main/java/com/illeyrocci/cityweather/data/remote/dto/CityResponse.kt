package com.illeyrocci.cityweather.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val id: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)