package com.trekkstay.hotel.feature.hotel.data.models


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
import org.json.JSONArray
import org.json.JSONObject

data class HotelListModel(
    @SerializedName("limit") val limit: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("hotel_list") val hotelList: List<HotelModel>,
) {
    companion object {
        fun empty(): HotelListModel {
            return HotelListModel(
                limit = 0,
                page =0,
                hotelList = emptyList(),
            )
        }

        fun fromJson(source: String): HotelListModel {
            println("ok 1")
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }
        private fun JSONObject.toMap(): Map<String, Any> {

            println("ok 2")
            val map = mutableMapOf<String, Any>()
            val keysItr = keys()

            while (keysItr.hasNext()) {
                val key = keysItr.next()
                val value = getString(key)
                map[key] = value
            }

            return map
        }
        private fun fromList(source: String): List<HotelModel>{

            println("ok 3")
            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, Any>> = mutableListOf()

            println("ok 4")
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }

            return list.map { HotelModel.fromMap(it) }
        }


        private fun fromMap(map: DataMap): HotelListModel {
            val gson = Gson()
            return HotelListModel(
            limit = (map["limit"] as? String)?.toInt() ?: 0,
            page = (map["page"] as? String)?.toInt() ?: 0,
            hotelList = fromList(gson.toJson(map["rows"]))
            )
        }
    }
}


fun HotelListModel.toEntity(): HotelList {
    val hotelEntities = hotelList.map { it.toEntity() }
    return HotelList(limit,page,hotelEntities)
}
