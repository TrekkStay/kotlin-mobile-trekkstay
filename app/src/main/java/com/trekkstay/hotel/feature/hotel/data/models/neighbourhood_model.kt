package com.trekkstay.hotel.feature.hotel.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.domain.entities.Neighbourhood
import com.trekkstay.hotel.feature.hotel.domain.entities.Destination

data class NeighbourhoodModel(
    @SerializedName("name") val name: String,
    @SerializedName("distance") val distance:Double,
) {
    companion object {
        fun empty(): NeighbourhoodModel {
            return NeighbourhoodModel(
                name = "_empty.name",
                distance = 0.0,
            )
        }

        fun fromJson(source: String): NeighbourhoodModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): NeighbourhoodModel {
            return NeighbourhoodModel(
                name = map["name"] as String,
                distance = map["lng"] as Double,

                )
        }
    }
}


fun NeighbourhoodModel.toEntity(): Neighbourhood {
    return Neighbourhood( name, distance)
}
