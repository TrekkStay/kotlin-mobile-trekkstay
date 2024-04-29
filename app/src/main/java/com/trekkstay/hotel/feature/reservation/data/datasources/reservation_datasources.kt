package com.trekkstay.hotel.feature.reservation.data.datasources

import android.content.Context
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.reservation.data.models.ReservationModel
import com.trekkstay.hotel.feature.reservation.data.models.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

interface ReservationRemoteDataSource {
    suspend fun createReservation(
        roomId: String,
        checkIn: String,
        checkOut: String,
        quantity: Int,
        guestInfo: GuestInfo,
        ): Response<Reservation>

}

const val createReservationEndpoint = "reservation/create"

class ReservationRemoteDataSourceImpl(private val client: Client,private val context: Context) : ReservationRemoteDataSource {

    override suspend fun createReservation(
        roomId: String,
        checkIn: String,
        checkOut: String,
        quantity: Int,
        guestInfo: GuestInfo): Response<Reservation> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("room_id", roomId)
                put("check_in_date", checkIn)
                put("check_out_date", checkOut)
                put("quantity", quantity)
                put("guest_info", JSONObject().apply {
                    put("full_name", guestInfo.name)
                    put("contact", guestInfo.contact)
                    put("adults", guestInfo.adult)
                    put("children", guestInfo.children)
                })
            }


            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://52.163.61.213:8888/api/v1/$createReservationEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Reservation>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(ReservationModel.fromJson(responseData))
                    } else {
                        null
                    }
                }


            )
            response
        }
    }

    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is ReservationModel -> responseData.toEntity() as? T
            else -> null
        }
    }

}
