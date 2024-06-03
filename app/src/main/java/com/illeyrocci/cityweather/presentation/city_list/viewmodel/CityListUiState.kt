package com.illeyrocci.cityweather.presentation.city_list.viewmodel

import com.illeyrocci.cityweather.domain.model.City

data class CityListUiState(
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList(),
    val error: String? = null
)