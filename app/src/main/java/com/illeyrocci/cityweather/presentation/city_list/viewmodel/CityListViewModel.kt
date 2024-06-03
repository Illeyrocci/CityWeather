package com.illeyrocci.cityweather.presentation.city_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.usecase.GetCitiesUseCase
import com.illeyrocci.cityweather.presentation.city_list.components.CityListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityListViewModel(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(CityListUiState())
    val uiState: StateFlow<CityListUiState>
        get() = _uiState.asStateFlow()

    init {
        getCities()
    }

    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCitiesUseCase()
            _uiState.value = when (result) {
                is Resource.Success -> {
                    CityListUiState(
                        cities = mapCityListToSortedCityListItemList(
                            result.data ?: emptyList()
                        )
                    )
                }

                is Resource.Error -> {
                    CityListUiState(
                        error = result.message
                    )
                }

                is Resource.Loading -> {
                    CityListUiState(
                        isLoading = true
                    )
                }
            }
        }
    }

    private fun mapCityListToSortedCityListItemList(cityList: List<City>) =
        cityList.map { CityListItem(it.name) }.sortedBy { it.cityName }
}