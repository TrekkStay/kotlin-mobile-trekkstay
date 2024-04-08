package com.trekkstay.hotel.feature.hotel.data.datasources

import com.google.android.gms.maps.model.LatLng
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.env.Env
import com.trekkstay.hotel.feature.authenticate.data.models.EmployeeModel
import com.trekkstay.hotel.feature.authenticate.data.models.LoginResModel
import com.trekkstay.hotel.feature.authenticate.data.models.toEmployee
import com.trekkstay.hotel.feature.authenticate.data.models.toLoginRes
import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

interface HotelRemoteDataSource {
    suspend fun createHotel(name: String,
                            description: String,
                            airportTransfer: Boolean,
                            conferenceRoom: Boolean,
                            fitnessCenter: Boolean,
                            foodService: Boolean,
                            freeWifi: Boolean,
                            laundryService: Boolean,
                            motorBikeRental: Boolean,
                            parkingArea: Boolean,
                            spaService: Boolean,
                            swimmingPool: Boolean,
                            coordinates: LatLng,
                            videos: List<String>,
                            images: List<String>,
                            email: String,
                            phone: String,
                            checkInTime: String,
                            checkOutTime: String,
                            provinceCode: String,
                            districtCode: String,
                            wardCode: String,
                            addressDetail: String,
                            ): Response<Unit>
}

const val createHotelEndpoint = "hotel/create"

class HotelRemoteDataSourceImpl(private val client: Client) : HotelRemoteDataSource {

    override suspend fun createHotel(name: String,
                                     description: String,
                                     airportTransfer: Boolean,
                                     conferenceRoom: Boolean,
                                     fitnessCenter: Boolean,
                                     foodService: Boolean,
                                     freeWifi: Boolean,
                                     laundryService: Boolean,
                                     motorBikeRental: Boolean,
                                     parkingArea: Boolean,
                                     spaService: Boolean,
                                     swimmingPool: Boolean,
                                     coordinates: LatLng,
                                     videos: List<String>,
                                     images: List<String>,
                                     email: String,
                                     phone: String,
                                     checkInTime: String,
                                     checkOutTime: String,
                                     provinceCode: String,
                                     districtCode: String,
                                     wardCode: String,
                                     addressDetail: String,
                                     ): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val facilitiesJson = JSONObject().apply {
                put("airport_transfer", airportTransfer)
                put("conference_room", conferenceRoom)
                put("fitness_center", fitnessCenter)
                put("food_service", foodService)
                put("free_wifi", freeWifi)
                put("laundry_service", laundryService)
                put("motor_bike_rental", motorBikeRental)
                put("parking_area", parkingArea)
                put("spa_service", spaService)
                put("swimming_pool", swimmingPool)
            }

            val coordinatesJson = JSONObject().apply {
                put("lat", coordinates.latitude)
                put("lng", coordinates.longitude)
            }

            val videosJson = JSONObject().apply {
                put("urls", JSONArray(videos))
            }

            val imagesJson = JSONObject().apply {
                put("urls", JSONArray(images))
            }

            val requestBodyJson = JSONObject().apply {
                put("name", name)
                put("description", description)
                put("facilities", facilitiesJson)
                put("coordinates", coordinatesJson)
                put("videos", videosJson)
                put("images", imagesJson)
                put("email", email)
                put("phone", phone)
                put("check_in_time", checkInTime)
                put("check_out_time", checkOutTime)
                put("province_code", provinceCode)
                put("district_code", districtCode)
                put("ward_code", wardCode)
                put("address_detail", addressDetail)
            }


            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$createHotelEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request,
            )
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
