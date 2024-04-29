package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.Attraction
import com.trekkstay.hotel.feature.hotel.domain.entities.Destination

data class AttractionModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat:String,
    @SerializedName("lng") val lng:String,
) {
    companion object {
        fun empty(): AttractionModel {
            return AttractionModel(
                id = "_empty.id",
                name = "_empty.name",
                lat = "_empty.lat",
                lng = "_empty.lng"
            )
        }

        fun fromJson(source: String): AttractionModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): AttractionModel {
            return AttractionModel(
                id = map["id"] as String,
                name = map["name"] as String,
                lat = map["lat"] as String,
                lng = map["lng"] as String,

                )
        }
    }
}


fun AttractionModel.toEntity(): Attraction {
    return Attraction(id, name, lat, lng)
}
