package com.illeyrocci.cityweather

import kotlinx.serialization.json.Json

fun getJson() = Json {
    isLenient = false
    ignoreUnknownKeys = true
}