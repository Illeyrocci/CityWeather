package com.illeyrocci.cityweather.presentation.city_list.components

import androidx.recyclerview.widget.RecyclerView
import com.illeyrocci.cityweather.databinding.ViewCityItemBinding
import com.illeyrocci.cityweather.domain.model.City

class CityListItemViewHolder(private val binding: ViewCityItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        cityListItem: City,
        onCityClicked: (Int) -> Unit
    ) {
        with(binding) {
            cityName.text = cityListItem.name
            root.setOnClickListener { onCityClicked(adapterPosition) }
        }
    }
}