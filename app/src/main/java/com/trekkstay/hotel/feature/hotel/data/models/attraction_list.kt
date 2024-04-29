package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList
import org.json.JSONArray
import org.json.JSONObject

data class AttractionListModel(
    @SerializedName("attraction_list") val attractionList: List<AttractionModel>,
) {
    companion object {
        fun empty(): AttractionListModel {
            return AttractionListModel(
                attractionList = emptyList(),
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
        fun fromJson(source: String): AttractionListModel {
            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, String>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return fromMap(list)
        }

        private fun fromMap(data: List<DataMap>): AttractionListModel {
            val attractions =
                data.map { AttractionModel.fromMap(it) }
            return AttractionListModel(attractionList = attractions)
        }
    }
}


fun AttractionListModel.toEntity(): AttractionList {
    val attraction = attractionList.map { it.toEntity() }
    return AttractionList(attraction)
}
