package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.SleepRoom

data class SleepRoomModel(
    @SerializedName("adults") val adults: Int,
    @SerializedName("children") val children: Int,
){
    companion object {

        fun fromJson(source: String): SleepRoomModel {
            val type = object : TypeToken<Map<String, Int>>() {}.type
            val map: Map<String, Int> = Gson().fromJson(source, type)
            return fromMap(map)
        }


        private fun fromMap(map: DataMap): SleepRoomModel {
            return SleepRoomModel(
                adults = (map["adults"] as? Int ) ?: 0,
                children = (map["children"] as? Int ) ?: 0,
            )
        }

    }
}


fun SleepRoomModel.toEntity(): SleepRoom {
    return SleepRoom(
        adults = adults,
        children = children
    )
}