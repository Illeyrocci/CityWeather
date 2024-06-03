package com.illeyrocci.cityweather.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse (
    val temp: Double
)