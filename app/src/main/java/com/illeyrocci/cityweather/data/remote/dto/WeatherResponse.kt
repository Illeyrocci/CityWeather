package com.illeyrocci.cityweather.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse (
    val main: Main
) {
    @Serializable
    data class Main(
        val temp: Double
    )
}