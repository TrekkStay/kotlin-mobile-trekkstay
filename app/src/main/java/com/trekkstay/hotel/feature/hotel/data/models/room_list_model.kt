package com.trekkstay.hotel.feature.hotel.data.models

import com.trekkstay.hotel.feature.hotel.domain.entities.RoomList

import com.google.gson.annotations.SerializedName
import com.trekkstay.hotel.core.typedef.DataMap
import org.json.JSONArray
import org.json.JSONObject

data class RoomListModel(
    @SerializedName("room_list") val roomList: List<RoomModel>,
) {
    companion object {
        fun empty(): RoomListModel {
            return RoomListModel(
                roomList = emptyList(),
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
        fun fromJson(source: String): RoomListModel {

            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, String>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }
            return fromMap(list)
        }

        private fun fromMap(data: List<DataMap>): RoomListModel {
            val locations =
                data.map { RoomModel.fromMap(it) }

            return RoomListModel(roomList = locations)
        }
    }
}


fun RoomListModel.toEntity(): RoomList {
    val roomEntities = roomList.map { it.toEntity() }
    return RoomList(roomEntities)
}
