package com.trekkstay.hotel.feature.hotel.data.models


import com.trekkstay.hotel.feature.hotel.domain.entities.DestinationList

import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.core.typedef.DataMap
import org.json.JSONArray
import org.json.JSONObject

data class DestinationListModel(
    @SerializedName("destination_list") val destinationList: List<DestinationModel>,
) {
    companion object {
        fun empty(): DestinationListModel {
            return DestinationListModel(
                destinationList = emptyList(),
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
        fun fromJson(source: String): DestinationListModel {

            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, String>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return fromMap(list)
        }

        private fun fromMap(data: List<DataMap>): DestinationListModel {
            val destinations =
                data.map { DestinationModel.fromMap(it) }

            return DestinationListModel(destinationList = destinations)
        }
    }
}


fun DestinationListModel.toEntity(): DestinationList {
    val destination = destinationList.map { it.toEntity() }
    return DestinationList(destination)
}
