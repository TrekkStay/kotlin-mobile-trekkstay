package com.trekkstay.hotel.feature.reservation.data.datasources

import android.content.Context
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.reservation.data.models.ReservationListModel
import com.trekkstay.hotel.feature.reservation.data.models.ReservationModel
import com.trekkstay.hotel.feature.reservation.data.models.toEntity
import com.trekkstay.hotel.feature.reservation.domain.entities.GuestInfo
import com.trekkstay.hotel.feature.reservation.domain.entities.Reservation
import com.trekkstay.hotel.feature.reservation.domain.entities.ReservationList
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

    suspend fun listReservation(
        hotelId: String?,
        status: String,
        dayPicked: String
    ): Response<ReservationList>

    suspend fun viewDetailReservation(
        reservationId: String
    ): Response<Reservation>

    suspend fun createPayment(
        amount: String,
        method: String,
        reservationId: String,
        status: String
    ): Response<Unit>
}

const val createReservationEndpoint = "reservation/create"
const val listReservationEndpoint = "reservation/filter"
const val createPaymentEndpoint = "payment/create"

class ReservationRemoteDataSourceImpl(private val client: Client, private val context: Context) :
    ReservationRemoteDataSource {

    override suspend fun createReservation(
        roomId: String,
        checkIn: String,
        checkOut: String,
        quantity: Int,
        guestInfo: GuestInfo
    ): Response<Reservation> {
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
                method = RequestMethod.POST,
                path = "http://175.41.168.200:8888/api/v1/$createReservationEndpoint",
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

    override suspend fun listReservation(
        hotelId: String?,
        status: String,
        dayPicked: String
    ): Response<ReservationList> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            var path =
                "http://175.41.168.200:8888/api/v1/$listReservationEndpoint?hotel_id=$hotelId&status=$status"

            if (hotelId == null) {
                path =
                    "http://175.41.168.200:8888/api/v1/$listReservationEndpoint?status=$status"
            }
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = path,
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = null
            )

            println(request)

            val response = client.execute<ReservationList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(ReservationListModel.fromJson(responseData))
                    } else {
                        null
                    }
                }
            )
            response
        }
    }

    override suspend fun viewDetailReservation(reservationId: String): Response<Reservation> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://175.41.168.200:8888/api/v1/reservation/$reservationId",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = null
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


    override suspend fun createPayment(
        amount: String,
        method: String,
        reservationId: String,
        status: String
    ): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("amount", amount.toInt())
                put("method", method)
                put("reservation_id", reservationId)
                put("status", status)
            }


            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://175.41.168.200:8888/api/v1/$createPaymentEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request,
            )
            response
        }
    }
    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is ReservationModel -> responseData.toEntity() as? T
            is ReservationListModel -> responseData.toEntity() as? T
            else -> null
        }
    }

}