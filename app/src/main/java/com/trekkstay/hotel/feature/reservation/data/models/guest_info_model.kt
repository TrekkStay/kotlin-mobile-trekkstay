package com.trekkstay.hotel.feature.reservation.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo

data class GuestInfoModel(
    @SerializedName("full_name") val name: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("adults") val adults: Int,
    @SerializedName("children") val children: Int,
) {
    companion object {
        fun empty(): GuestInfoModel {
            return GuestInfoModel(
                name = "_empty.name",
                contact = "_empty.contact",
                adults = 0,
                children = 0,
            )
        }

        fun parseInputString(input: String): Map<String, Any> {
            val map = mutableMapOf<String, Any>()

            val regex = Regex("""(\w+)\s*=\s*(\{[^{}]*\}|\[.*\]|[^,{}]+)""")
            regex.findAll(input).forEach { matchResult ->
                val key = matchResult.groupValues[1]
                val value = parseValue(matchResult.groupValues[2])
                map[key] = value
            }

            return map
        }

        fun parseValue(value: String): Any {
            return when {
                value.startsWith("{") && value.endsWith("}") -> parseInputString(
                    value.drop(1).dropLast(1)
                )

                value.startsWith("[") && value.endsWith("]") -> {
                    val elements = value.drop(1).dropLast(1).split(",").map { it.trim() }
                    elements.map { parseValue(it) }
                }

                else -> value
            }
        }

        fun fromJson(source: String): GuestInfoModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            try {
                val map: Map<String, Any> = Gson().fromJson(source, type)
                return fromMap(map)
            } catch (e: Exception) {
                val map: Map<String, Any> = ReservationRoomModel.parseInputString(source)
                return fromMap(map)
            }
        }

        fun fromMap(map: DataMap): GuestInfoModel {

            return GuestInfoModel(
                name = map["full_name"] as String,
                contact = map["contact"] as String,
                adults = (map["adults"].toString().toDouble()).toInt(),
                children = (map["children"].toString().toDouble()).toInt()

            )
        }
    }
}

fun GuestInfoModel.toEntity(): GuestInfo {
    return GuestInfo(name, contact, adults, children)
}