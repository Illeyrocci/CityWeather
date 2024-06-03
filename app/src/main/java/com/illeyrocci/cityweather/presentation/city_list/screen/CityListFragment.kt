package com.illeyrocci.cityweather.presentation.city_list.screen

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
import androidx.navigation.fragment.findNavController
import com.illeyrocci.cityweather.R
import com.illeyrocci.cityweather.databinding.FragmentCityListBinding
import com.illeyrocci.cityweather.presentation.city_list.components.CityAdapter
import com.illeyrocci.cityweather.presentation.city_list.components.CityStickyLabelDecoration
import com.illeyrocci.cityweather.presentation.city_list.viewmodel.CityListUiState
import com.illeyrocci.cityweather.presentation.city_list.viewmodel.CityListVMFactory
import com.illeyrocci.cityweather.presentation.city_list.viewmodel.CityListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: CityListViewModel by viewModels { CityListVMFactory() }

    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = CityAdapter { position ->
            val city = viewModel.getCityAt(position)
            findNavController().navigate(
                CityListFragmentDirections.listToWeather(
                    city.name,
                    city.latitude.toString(),
                    city.longitude.toString()
                )
            )
        }
        _binding = FragmentCityListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cityList.adapter = adapter
            cityList.addItemDecoration(
                CityStickyLabelDecoration(
                    resources.getDimensionPixelSize(R.dimen.medium_text_size),
                    resources.getDimensionPixelSize(R.dimen.label_width)
                )
            )
            buttonUpdate.setOnClickListener {
                viewModel.getCities()
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->

                    adapter.update(uiState.cities)

                    with(binding) {
                        toggleVisibility(uiState)
                        if (!uiState.error.isNullOrBlank()) textError.text = uiState.error
                    }
                }
            }
        }
    }

    private fun FragmentCityListBinding.toggleVisibility(uiState: CityListUiState) {
        errorStub.isVisible = !uiState.error.isNullOrBlank()
        progressCircle.isVisible = uiState.isLoading
        cityList.isVisible = uiState.cities.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}