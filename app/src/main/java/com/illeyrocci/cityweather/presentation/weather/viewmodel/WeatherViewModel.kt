package com.illeyrocci.cityweather.presentation.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.usecase.GetCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState>
        get() = _uiState.asStateFlow()

    init {
        getTemperature()
    }

    fun getTemperature() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = WeatherUiState(isLoading = true)
            val result: Resource<Int> = Resource.Success(data = 32)//getCitiesUseCase()
            _uiState.value = when (result) {
                is Resource.Success -> {
                    WeatherUiState(
                        temperature = result.data!!
                    )
                }

                is Resource.Error -> {
                    WeatherUiState(error = result.message)
                }
            }
        }
    }
}