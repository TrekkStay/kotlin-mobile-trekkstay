package com.trekkstay.hotel.feature.hotel.data.datasources


import android.content.Context
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.authenticate.data.models.EmployeeModel
import com.trekkstay.hotel.feature.authenticate.data.models.LoginResModel
import com.trekkstay.hotel.feature.authenticate.data.models.toEmployee
import com.trekkstay.hotel.feature.authenticate.data.models.toLoginRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

interface RoomRemoteDataSource {
    suspend fun createRoom(
        hotelId: String,
        type: String,
        description: String,
        airConditioner: Boolean,
        balcony: Boolean,
        bathTub: Boolean,
        hairDryer: Boolean,
        kitchen: Boolean,
        nonSmoking: Boolean,
        shower: Boolean,
        slippers: Boolean,
        television: Boolean,
        numberOfBed: Int,
        roomSize: Int,
        adults: Int,
        children: Int,
        view: String,
        videos: List<String>,
        images: List<String>,
        quantities: Int,
        discountRate: Int,
        originalPrice: Int
    ): Response<Unit>
}

const val createRoomEndpoint = "hotel-room/create"

class RoomRemoteDataSourceImpl(private val client: Client, private val context: Context) : RoomRemoteDataSource {

    override suspend fun createRoom(
        hotelId: String,
        type: String,
        description: String,
        airConditioner: Boolean,
        balcony: Boolean,
        bathTub: Boolean,
        hairDryer: Boolean,
        kitchen: Boolean,
        nonSmoking: Boolean,
        shower: Boolean,
        slippers: Boolean,
        television: Boolean,
        numberOfBed: Int,
        roomSize: Int,
        adults: Int,
        children: Int,
        view: String,
        videos: List<String>,
        images: List<String>,
        quantities: Int,
        discountRate: Int,
        originalPrice: Int
    ): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val requestBody = JSONObject()
            val facilities = JSONObject()
            val sleeps = JSONObject()
            val videosArray = JSONArray(videos)
            val imagesArray = JSONArray(images)

            requestBody.put("hotel_id", hotelId)
            requestBody.put("type", type)
            requestBody.put("description", description)
            requestBody.put("quantity", quantities)
            requestBody.put("discount_rate", discountRate)
            requestBody.put("original_price", originalPrice)

            requestBody.put("videos", JSONObject().put("urls", videosArray))
            requestBody.put("images", JSONObject().put("urls", imagesArray))

            facilities.put("air_conditioner", airConditioner)
            facilities.put("balcony", balcony)
            facilities.put("bath_tub", bathTub)
            facilities.put("hair_dryer", hairDryer)
            facilities.put("kitchen", kitchen)
            facilities.put("non_smoking", nonSmoking)
            facilities.put("number_of_bed", numberOfBed)
            facilities.put("room_size", roomSize)
            facilities.put("shower", shower)

            sleeps.put("adults", adults)
            sleeps.put("children", children)

            facilities.put("sleeps", sleeps)

            facilities.put("slippers", slippers)
            facilities.put("television", television)
            facilities.put("view", view)

            requestBody.put("facilities", facilities)

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$createRoomEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBody.toString()
            )

            val response = client.execute<Unit>(request = request)
            println("${response.data} tried doing")
            response
        }
    }

    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        println("check for function call")
        return when (responseData) {
            is LoginResModel -> responseData.toLoginRes() as? T
            is EmployeeModel -> responseData.toEmployee() as T
            else -> null
        }
    }

}
