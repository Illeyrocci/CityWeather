package com.illeyrocci.cityweather.presentation.weather.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.illeyrocci.cityweather.databinding.FragmentWeatherBinding
import com.illeyrocci.cityweather.presentation.weather.viewmodel.WeatherUiState
import com.illeyrocci.cityweather.presentation.weather.viewmodel.WeatherVMFactory
import com.illeyrocci.cityweather.presentation.weather.viewmodel.WeatherViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: WeatherViewModel by viewModels { WeatherVMFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonUpdate.setOnClickListener {
                viewModel.getTemperature()
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->

                    with(binding) {
                        toggleVisibility(uiState)
                        if (!uiState.error.isNullOrBlank()) textError.text = uiState.error
                    }
                }
            }
        }
    }

    private fun FragmentWeatherBinding.toggleVisibility(uiState: WeatherUiState) {
        textError.isVisible = !uiState.error.isNullOrBlank()
        progressCircle.isVisible = uiState.isLoading
        textCelsius.isVisible = uiState.temperature != null
        textCity.isVisible = uiState.temperature != null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}