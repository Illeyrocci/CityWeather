package com.illeyrocci.cityweather.presentation.city_list.components

import androidx.recyclerview.widget.RecyclerView
import com.illeyrocci.cityweather.databinding.ViewCityItemBinding

class CityListItemViewHolder(private val binding: ViewCityItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        cityListItem: CityListItem
    ) {
        binding.cityName.text = cityListItem.cityName
    }
}