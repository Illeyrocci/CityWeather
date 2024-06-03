package com.illeyrocci.cityweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.illeyrocci.cityweather.data.remote.ktor.RemoteDataSourceImpl
import com.illeyrocci.cityweather.data.remote.ktor.ktor.getHttpClient
import com.illeyrocci.cityweather.databinding.FragmentCityListBinding
import kotlinx.coroutines.runBlocking

class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    //private val viewModel: MyProfileViewModel by viewModels { MyProfileViewModelFactory() }

    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = CityAdapter()
        _binding = FragmentCityListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityList.adapter = adapter
        binding.cityList.addItemDecoration(
            CityStickyLabelDecoration(
                resources.getDimensionPixelSize(R.dimen.medium_text_size),
                resources.getDimensionPixelSize(R.dimen.vertical_margin),
                resources.getDimensionPixelSize(R.dimen.label_width)
            )
        )

        runBlocking {
            adapter.update(
                RemoteDataSourceImpl(getHttpClient()).getCities().map { if (it.city == "") "Unknown" else it.city }.sorted()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}