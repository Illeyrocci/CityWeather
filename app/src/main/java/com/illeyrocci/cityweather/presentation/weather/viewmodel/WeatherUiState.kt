package com.illeyrocci.cityweather.presentation.weather.viewmodel

class WeatherUiState (
    val isLoading: Boolean = false,
    val temperature: Int? = null,
    val error: String? = null
)