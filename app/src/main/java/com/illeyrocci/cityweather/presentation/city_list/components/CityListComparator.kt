package com.illeyrocci.cityweather.presentation.city_list.components

import androidx.recyclerview.widget.DiffUtil
import com.illeyrocci.cityweather.domain.model.City

class CityListComparator(
    private val oldList: List<City>,
    private val newList: List<City>
) : DiffUtil.Callback() {

    /**
     *Compare by name because neither ids nor names are not unique in given API.
     *But as far as I see, id-name pair is unique for every entry
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].name == oldList[oldItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() =
        oldList.size

    override fun getNewListSize() =
        newList.size
}