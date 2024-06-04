package com.illeyrocci.cityweather.presentation.city_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illeyrocci.cityweather.common.Resource
import com.illeyrocci.cityweather.domain.model.City
import com.illeyrocci.cityweather.domain.usecase.GetCitiesSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getCitiesSortedByNameUseCase: GetCitiesSortedByNameUseCase
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
            _uiState.value = CityListUiState(isLoading = true)
            val result = getCitiesSortedByNameUseCase()
            _uiState.value = when (result) {
                is Resource.Success -> {
                    CityListUiState(
                        cities = result.data!!
                    )
                }

                is Resource.Error -> {
                    CityListUiState(error = result.message)
                }
            }
        }
    }

    fun getCityAt(position: Int): City = uiState.value.cities[position]
}