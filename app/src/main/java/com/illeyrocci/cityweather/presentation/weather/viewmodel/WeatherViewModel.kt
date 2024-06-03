package com.illeyrocci.cityweather.presentation.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.Weather
import com.illeyrocci.cityweather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState>
        get() = _uiState.asStateFlow()

    fun getTemperatureForCity(cityName: String, latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = WeatherUiState(isLoading = true)
            val result: Resource<Weather> = getWeatherUseCase(latitude, longitude)
            _uiState.value = when (result) {
                is Resource.Success -> {
                    WeatherUiState(
                        cityName = cityName,
                        temperature = mapWeatherToInteger(result.data!!)
                    )
                }

                is Resource.Error -> {
                    WeatherUiState(error = result.message)
                }
            }
        }
    }

    private fun mapWeatherToInteger(weather: Weather) = weather.temp.roundToInt()
}