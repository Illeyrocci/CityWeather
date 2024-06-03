package com.illeyrocci.cityweather.presentation.city_list.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.illeyrocci.cityweather.databinding.ViewCityItemBinding
import com.illeyrocci.cityweather.domain.model.City

class CityAdapter(
    private val onCityClicked: (Int) -> Unit
) : RecyclerView.Adapter<CityListItemViewHolder>() {

    private var data: List<City> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCityItemBinding.inflate(inflater, parent, false)
        return CityListItemViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CityListItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onCityClicked)
    }

    fun update(results: List<City>) {
        data = results
        notifyDataSetChanged()
    }

    fun cityNameAt(index: Int) = data[index].name
}