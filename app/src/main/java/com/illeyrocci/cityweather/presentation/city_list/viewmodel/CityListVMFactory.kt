package com.illeyrocci.cityweather.presentation.city_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.illeyrocci.cityweather.data.repository.CityRepositoryImpl
import com.illeyrocci.cityweather.domain.usecase.GetCitiesUseCase

class CityListVMFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityListViewModel::class.java)) {
            return CityListViewModel(
                GetCitiesUseCase(CityRepositoryImpl())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}