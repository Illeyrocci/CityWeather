package com.illeyrocci.cityweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.illeyrocci.cityweather.databinding.ViewCityItemBinding

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var data: List<String> = listOf()

    class CityViewHolder(private val binding: ViewCityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            city: String
        ) {
            binding.cityName.text = city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCityItemBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    fun update(results: List<String>) {
        data = results
        notifyDataSetChanged()
    }

    fun cityAt(position: Int) = data[position]
}