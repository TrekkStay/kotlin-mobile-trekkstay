package com.trekkstay.hotel.feature.hotel.data.models

import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList

import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.core.typedef.DataMap
import org.json.JSONArray
import org.json.JSONObject

data class LocationListModel(
    @SerializedName("location_list") val locationList: List<LocationModel>,
) {
    companion object {
        fun empty(): LocationListModel {
            return LocationListModel(
                locationList = emptyList(),
            )
        }

        private fun JSONObject.toMap(): Map<String, String> {
            val map = mutableMapOf<String, String>()
            val keysItr = keys()

            while (keysItr.hasNext()) {
                val key = keysItr.next()
                val value = getString(key)
                map[key] = value
            }

            return map
        }
        fun fromJson(source: String): LocationListModel {

            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, String>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return fromMap(list)
        }

        private fun fromMap(data: List<DataMap>): LocationListModel {
            val locations =
                data.map { LocationModel.fromMap(it) }

            return LocationListModel(locationList = locations)
        }
    }
}


fun LocationListModel.toEntity(): LocationList {
    val locationEntities = locationList.map { it.toEntity() }
    return LocationList(locationEntities)
}
