package com.trekkstay.hotel.feature.reservation.data.models


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.hotel.data.models.MediaModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationRoom


data class ReservationRoomModel(
    @SerializedName("hotel_id") val hotelId: String,
    @SerializedName("hotel_name") val hotelName: String,
    @SerializedName("location") val location: String,
    @SerializedName("type") val type: String,
    @SerializedName("original_price") val originalPrice: Int,
    @SerializedName("booking_price") val bookingPrice: Int,
    @SerializedName("images") val images: MediaModel,
) {
    companion object {

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

        private fun parseValue(value: String): Any {
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

        fun fromJson(source: String): ReservationRoomModel {
            println("Source Room >>>>>>>>>>  $source")
            val type = object : TypeToken<Map<String, Any>>() {}.type
            return try {
                val map: Map<String, Any> = Gson().fromJson(source, type)
                fromMap(map)
            } catch (e: Exception) {
                val map: Map<String, Any> = parseInputString(source)
                fromMap(map)
            }
        }

        fun fromMap(map: DataMap): ReservationRoomModel {
            return ReservationRoomModel(
                hotelId = map["hotel_id"] as String,
                hotelName = map["hotel_name"] as String,
                location = map["location"] as String,
                type = map["type"] as String,
                originalPrice = (map["original_price"].toString().toDouble()).toInt(),
                bookingPrice = (map["booking_price"].toString().toDouble()).toInt(),
                images = MediaModel.fromJson(Gson().toJson(map["images"])),
            )
        }
    }
}


fun ReservationRoomModel.toEntity(): ReservationRoom {
    return ReservationRoom(
        hotelId,
        hotelName,
        location,
        type,
        originalPrice,
        bookingPrice,
        images.toEntity()
    )
}