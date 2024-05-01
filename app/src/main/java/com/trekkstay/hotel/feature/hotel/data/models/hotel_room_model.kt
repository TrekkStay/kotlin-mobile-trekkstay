package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelRoom
import org.json.JSONArray
import org.json.JSONObject

data class HotelRoomModel(
    @SerializedName("id") val id: String,
    @SerializedName("hotel_id") val hotelId: String,
    @SerializedName("type") val type: String,
    @SerializedName("description") val description: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("discount_rate") val discountRate: Int,
    @SerializedName("original_price") val originalPrice: Int,
    @SerializedName("videos") val videos: List<String>,
    @SerializedName("images") val images: List<String>,) {
    companion object {


        fun fromJson(source: String): HotelRoomModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)

            return fromMap(map)
        }
        fun fromList(source: String): List<HotelRoomModel>{
            val jsonArray = JSONArray(source)
            val list: MutableList<Map<String, Any>> = mutableListOf()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val map = jsonObject.toMap()
                list.add(map)
            }

            return list.map { fromMap(it) }
        }
        private fun JSONObject.toMap(): Map<String, Any> {
            val map = mutableMapOf<String, Any>()
            val keysItr = keys()

            while (keysItr.hasNext()) {
                val key = keysItr.next()
                val value = getString(key)
                map[key] = value
            }

            return map
        }

        fun fromMap(map: DataMap): HotelRoomModel {
            return HotelRoomModel(
                id = map["id"] as? String ?: "",
                hotelId = map["hotel_id"] as? String ?: "",
                type = map["type"] as? String ?: "",
                description = map["description"] as? String ?: "",
                quantity = (map["quantity"] as? String)?.toInt() ?: 0,
                discountRate = (map["discount_rate"] as? String)?.toInt() ?: 0,
                originalPrice = (map["original_price"] as? String)?.toInt() ?: 0,
                videos = (map["videos"] as? DataMap)?.get("urls") as? List<String> ?: emptyList(),
                images = (map["images"] as? DataMap)?.get("urls") as? List<String> ?: emptyList(),

            )
        }


    }
}


fun HotelRoomModel.toEntity(): HotelRoom {
    return HotelRoom(
        id = id,
        hotelId = hotelId,
        type = type,
        description = description,
        quantity = quantity,
        discountRate = discountRate,
        originalPrice = originalPrice,
        videos = videos,
        images = images,
    )
}
