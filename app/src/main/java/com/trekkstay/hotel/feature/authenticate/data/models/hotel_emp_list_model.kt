package com.trekkstay.hotel.feature.authenticate.data.models

import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.authenticate.domain.entities.HotelEmpList
import org.json.JSONArray
import org.json.JSONObject

data class HotelEmpListModel(
    @SerializedName("hotel_emp_list") val empList: List<HotelEmpModel>,
) {
    companion object {
        fun empty(): HotelEmpListModel {
            return HotelEmpListModel(
                empList = emptyList(),
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
        fun fromJson(source: String): HotelEmpListModel {

            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, String>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return fromMap(list)
        }

        private fun fromMap(data: List<DataMap>): HotelEmpListModel {
            val empList =
                data.map { HotelEmpModel.fromMap(it) }

            return HotelEmpListModel(empList = empList)
        }
    }
}


fun HotelEmpListModel.toEntity(): HotelEmpList {
    val empList = empList.map { it.toEntity() }
    return HotelEmpList(empList)
}
