package com.trekkstay.hotel.feature.reservation.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationList
import org.json.JSONArray
import org.json.JSONObject


data class ReservationListModel(
    @SerializedName("reservation_list") val reservationList: List<ReservationModel>,
) {
    companion object {
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

        private fun fromList(source: String): List<ReservationModel> {
            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, Any>> = mutableListOf()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return list.map { ReservationModel.fromMap(it) }
        }

        fun fromJson(source: String): ReservationListModel {
            println(source)
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        private fun fromMap(map: DataMap): ReservationListModel {
            val gson = Gson()
            return ReservationListModel(
                reservationList = fromList(gson.toJson(map["rows"]))
            )
        }
    }
}

fun ReservationListModel.toEntity(): ReservationList {
    val reservation = reservationList.map { it.toEntity() }
    return ReservationList("", reservation)
}
