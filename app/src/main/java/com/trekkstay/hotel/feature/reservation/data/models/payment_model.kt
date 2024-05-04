package com.trekkstay.hotel.feature.reservation.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.reservation.domain.entities.Payment

data class PaymentModel(
    @SerializedName("reservation_id") val reservationId: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("userId") val userId: String,
    @SerializedName("method") val method: String,
    @SerializedName("status") val status: String
) {
    companion object {
        fun empty(): PaymentModel {
            return PaymentModel(
                reservationId = "",
                amount = 0,
                userId = "",
                method = "",
                status = ""
            )
        }

        private fun parseInputString(input: String): Map<String, Any> {
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

        fun fromJson(source: String): PaymentModel {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            return try {
                val map: Map<String, Any> = Gson().fromJson(source, type)
                fromMap(map)
            } catch (e: Exception) {
                val map: Map<String, Any> = ReservationRoomModel.parseInputString(source)
                fromMap(map)
            }
        }

        fun fromMap(map: DataMap): PaymentModel {

            return PaymentModel(
                reservationId = map["reservation_id"] as String,
                amount = map["amount"] as Int,
                userId = map["user_id"] as String,
                method = map["method"] as String,
                status = map["status"] as String
            )
        }
    }
}

fun PaymentModel.toEntity(): Payment {
    return Payment(reservationId = reservationId, amount, userId, method, status)
}