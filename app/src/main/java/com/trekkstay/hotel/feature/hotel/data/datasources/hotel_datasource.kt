package com.trekkstay.hotel.feature.hotel.data.datasources

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.data.models.HotelListModel
import com.trekkstay.hotel.feature.hotel.data.models.HotelModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.Hotel
import com.trekkstay.hotel.feature.hotel.domain.entities.HotelList
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

    suspend fun updateHotel(name: String,
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

    suspend fun getHotelId(): Response<String>
    suspend fun viewHotel(
         name: String?, provinceCode: String?, districtCode: String?, wardCode: String?,  priceOrder: String?
    ): Response<HotelList>
    suspend fun searchHotel(
        locationCode: String?,  priceOrder: String?, checkInDate: String?, checkOutDate: String?, adults: Int?, children: Int?, numOfRoom: Int?, limit: Int?, page: Int?
    ): Response<HotelList>
    suspend fun hotelDetail(
        id:String
    ): Response<Hotel>
    suspend fun viewHotelNear( coordinate: LatLng,  maxRange: Double): Response<List<Hotel>>

}

const val createHotelEndpoint = "hotel/create"
const val updateHotelEndpoint = "hotel/update"
const val getHotelIdEndpoint = "hotel/my-hotel"
const val viewHotelEndpoint = "hotel/filter"
const val searchHotelEndpoint = "hotel/search"
const val hotelDetailEndpoint = "hotel/"
const val viewHotelNearEndpoint = "hotel/filter/near-me"



class HotelRemoteDataSourceImpl(private val client: Client, private val context: Context) : HotelRemoteDataSource {

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

            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$createHotelEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request,
            )
            println(request.toString())
            println(response.status)
            println(response.message)
            response
        }
    }


    override suspend fun updateHotel(name: String,
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
                put("id",LocalStore.getKey(context, "hotelId", ""))
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

            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/${updateHotelEndpoint}",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request,
            )
            response
        }
    }


    override suspend fun getHotelId(): Response<String> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://52.163.61.213:8888/api/v1/$getHotelIdEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = null,
            )

            val response = client.execute<String>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        val type = object : TypeToken<Map<String, Any>>() {}.type
                        val map: Map<String, Any> = Gson().fromJson(responseData, type)
                        parseResponse(map["id"])
                    } else {
                        null
                    }
                }
            )
            response
        }

    }

    override suspend fun viewHotel( name: String?, provinceCode: String?, districtCode: String?, wardCode: String?,  priceOrder: String?): Response<HotelList> {
        return withContext(Dispatchers.IO) {

            val queryParams = buildString {
                append(viewHotelEndpoint)

                if (name != null) {
                    append("?name=$name")
                }
                if (provinceCode != null) {
                    append(if (containsQueryParams(this.toString())) "&province_code=$provinceCode" else "?province_code=$provinceCode")
                }
                if (districtCode != null) {
                    append(if (containsQueryParams(this.toString())) "&district_code=$districtCode" else "?district_code=$districtCode")
                }
                if (wardCode != null) {
                    append(if (containsQueryParams(this.toString())) "&ward_code=$wardCode" else "?ward_code=$wardCode")
                }
                if (priceOrder != null) {
                    append(if (containsQueryParams(this.toString())) "&price_order=$priceOrder" else "?price_order=$priceOrder")
                }
            }
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://52.163.61.213:8888/api/v1/$queryParams",
                requestBody = null,
            )

            val response = client.execute<HotelList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(HotelListModel.fromJson(responseData))
                    } else {
                        null
                    }
                }
            )
            response
        }

    }


    override suspend fun searchHotel(
        locationCode: String?,  priceOrder: String?, checkInDate: String?, checkOutDate: String?, adults: Int?, children: Int?, numOfRoom: Int?, limit: Int?, page: Int?): Response<HotelList> {
        return withContext(Dispatchers.IO) {

            val queryParams = buildString {
                append(searchHotelEndpoint)

                if (locationCode != null) {
                    append("?location_code=$locationCode")
                }
                if (priceOrder != null) {
                    append(if (containsQueryParams(this.toString())) "&price_order=$priceOrder" else "?price_order=$priceOrder")
                }
                if (checkInDate != null) {
                    append(if (containsQueryParams(this.toString())) "&check_in_date=$checkInDate" else "?check_in_date=$checkInDate")
                }
                if (checkOutDate != null) {
                    append(if (containsQueryParams(this.toString())) "&check_out_date=$checkOutDate" else "?check_out_date=$checkOutDate")
                }
                if (adults != null) {
                    append(if (containsQueryParams(this.toString())) "&adults=$adults" else "?adults=$adults")
                }
                if (children != null) {
                    append(if (containsQueryParams(this.toString())) "&children=$children" else "?children=$children")
                }
                if (numOfRoom != null) {
                    append(if (containsQueryParams(this.toString())) "&num_of_rooms=$numOfRoom" else "?num_of_rooms=$numOfRoom")
                }
                if (limit != null) {
                    append(if (containsQueryParams(this.toString())) "&limit=$limit" else "?limit=$limit")
                }
                if (page != null) {
                    append(if (containsQueryParams(this.toString())) "&page=$page" else "?page=$page")
                }
            }
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://52.163.61.213:8888/api/v1/$queryParams",
                requestBody = null,
            )

            val response = client.execute<HotelList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(HotelListModel.fromJson(responseData))
                    } else {
                        null
                    }
                }
            )
            response
        }

    }


    override suspend fun viewHotelNear(
        coordinate: LatLng, maxRange: Double): Response<List<Hotel>> {
        return withContext(Dispatchers.IO) {

            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://52.163.61.213:8888/api/v1/$viewHotelNearEndpoint?lat=${coordinate.latitude}&lng=${coordinate.longitude}&max_distance=$maxRange",
                requestBody = null,
            )

            val response = client.execute<List<Hotel>>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(HotelModel.fromList(responseData))
                    } else {
                        null
                    }
                }
            )
            println(request)
            println(response.message)
            response
        }

    }
    private fun containsQueryParams(queryParams: String): Boolean {
        return queryParams.contains("?")
    }


    override suspend fun hotelDetail(id:String): Response<Hotel> {
        return withContext(Dispatchers.IO) {
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://52.163.61.213:8888/api/v1/$hotelDetailEndpoint$id",
                requestBody = null,
            )

            val response = client.execute<Hotel>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(HotelModel.fromJson(responseData))
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
            is String -> responseData as? T
            is HotelListModel -> responseData.toEntity() as? T
            is HotelModel -> responseData.toEntity() as? T
            is List<*> -> responseData.mapNotNull { item ->
                when (item) {
                    is HotelModel -> item.toEntity()
                    else -> null
                }
            } as? T
            else -> null
        }
    }

}
