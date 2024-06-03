package com.illeyrocci.cityweather.presentation.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.illeyrocci.cityweather.data.repository.CityRepositoryImpl
import com.illeyrocci.cityweather.domain.usecase.GetCitiesUseCase

class WeatherVMFactory  : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                GetCitiesUseCase(CityRepositoryImpl())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}