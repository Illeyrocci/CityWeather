package com.illeyrocci.cityweather.data.remote.ktor.ktor

import kotlinx.serialization.json.Json

fun getJson() = Json {
    isLenient = false
    ignoreUnknownKeys = true
}