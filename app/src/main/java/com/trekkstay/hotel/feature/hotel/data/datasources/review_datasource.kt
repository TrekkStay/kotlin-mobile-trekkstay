package com.trekkstay.hotel.feature.hotel.data.datasources

import android.content.Context
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.data.models.AttractionListModel
import com.trekkstay.hotel.feature.hotel.data.models.ReviewListModel
import com.trekkstay.hotel.feature.hotel.data.models.ReviewModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.Review
import com.trekkstay.hotel.feature.hotel.domain.entities.ReviewList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

interface ReviewRemoteDataSource {
    suspend fun createReview(
        hotelId: String,
        title: String,
        typeOfTraveller: String,
        point: Int,
        summary: String
    ): Response<Unit>

    suspend fun reviewList(
        hotelId: String
    ): Response<ReviewList>
}

const val createReviewEndpoint = "rating/create"
const val reviewListEndpoint = "rating/filter"
class ReviewRemoteDataSourceImpl(private val client: Client, private val context: Context) :
    ReviewRemoteDataSource {
    override suspend fun createReview(
        hotelId: String,
        title: String,
        typeOfTraveller: String,
        point: Int,
        summary: String
    ): Response<Unit> {
        return withContext(Dispatchers.IO){
            val requestBodyJson = JSONObject().apply {
                put("hotel_id", hotelId)
                put("title", title)
                put("type_of_traveler", typeOfTraveller)
                put("point", point)
                put("summary", summary)
            }
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://175.41.168.200:8888/api/v1/$createReviewEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = requestBodyJson.toString(),
            )
            val response =  client.execute<Unit>(
                request = request
            )
            response
        }
    }

    override suspend fun reviewList(hotelId: String): Response<ReviewList> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://175.41.168.200:8888/api/v1/$reviewListEndpoint?hotel_id=$hotelId",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = null,
            )
            val response = client.execute<ReviewList>(
                request = request,
                        parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(ReviewListModel.fromJson(responseData))
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
            is ReviewListModel -> responseData.toEntity() as? T
            is ReviewModel -> responseData.toEntity() as? T
            is List<*> -> responseData.mapNotNull { item ->
                when (item) {
                    is ReviewModel -> item.toEntity()
                    else -> null
                }
            } as? T
            else -> null
        }
    }
}