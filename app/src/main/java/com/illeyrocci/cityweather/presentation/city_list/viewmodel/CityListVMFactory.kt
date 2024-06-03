package com.illeyrocci.cityweather.presentation.city_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.illeyrocci.cityweather.data.repository.CityRepositoryImpl
import com.illeyrocci.cityweather.domain.usecase.GetCitiesSortedByNameUseCase

class CityListVMFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityListViewModel::class.java)) {
            return CityListViewModel(
                GetCitiesSortedByNameUseCase(CityRepositoryImpl())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}