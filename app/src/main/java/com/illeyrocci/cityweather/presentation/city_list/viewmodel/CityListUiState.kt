package com.illeyrocci.cityweather.presentation.city_list.viewmodel

import com.illeyrocci.cityweather.presentation.city_list.components.CityListItem

data class CityListUiState(
    val isLoading: Boolean = false,
    val cities: List<CityListItem> = emptyList(),
    val error: String? = null
)