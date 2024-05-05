package com.trekkstay.hotel.feature.reservation.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.typedef.DataMap
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation

data class ReservationModel(
    @SerializedName("id") val id: String,
    @SerializedName("room_id") val roomId: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("qr_code_url") val qrCodeUrl: String,
    @SerializedName("quantity") val quantity: Double,
    @SerializedName("total_price") val totalPrice: Double,
    @SerializedName("check_in") val checkIn: String,
    @SerializedName("check_out") val checkOut: String,
    @SerializedName("status") val status: String,
    @SerializedName("guess_info") val guestInfo: GuestInfoModel,
    @SerializedName("room") val room: ReservationRoomModel,
    @SerializedName("payment") val payment: PaymentModel?,
) {
    companion object {

        fun fromJson(source: String): ReservationModel {
            println(source)
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val map: Map<String, Any> = Gson().fromJson(source, type)
            return fromMap(map)
        }

        fun fromMap(map: DataMap): ReservationModel {
            return ReservationModel(
                id = map["id"] as String,
                roomId = map["room_id"] as String,
                userId = map["user_id"] as String,
                qrCodeUrl = map["qr_code_url"] as String? ?: "",
                quantity = if (map["quantity"] is String) {
                    println("still ok")
                    (map["quantity"] as String).toDouble()
                } else {
                    println("huh???")
                    map["quantity"] as Double
                },
                totalPrice = if (map["total_price"] is String) {

                    (map["total_price"] as String).toDouble()
                } else {
                    map["total_price"] as Double
                },
                checkIn = map["check_in_date"] as String,
                checkOut = map["check_out_date"] as String,
                status = map["status"] as String,
                guestInfo = GuestInfoModel.fromJson(map["guest_info"].toString()),
                room = ReservationRoomModel.fromJson(map["room"].toString()),
                payment = if (map["payment"] != null) {
                    PaymentModel.fromJson(map["payment"].toString())
                } else {
                    null
                }
            )
        }
    }
}


fun ReservationModel.toEntity(): Reservation {
    return Reservation(
        id,
        roomId,
        userId,
        qrCodeUrl,
        quantity,
        totalPrice,
        checkIn,
        checkOut,
        status,
        guestInfo.toEntity(),
        room.toEntity(),
        payment?.toEntity()
    )
}