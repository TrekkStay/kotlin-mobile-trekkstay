package com.trekkstay.hotel.feature.hotel.data.datasources

import android.content.Context
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.trekkstay.hotel.feature.hotel.data.models.AttractionListModel
import com.trekkstay.hotel.feature.hotel.data.models.AttractionModel
import com.trekkstay.hotel.feature.hotel.data.models.HotelListModel
import com.trekkstay.hotel.feature.hotel.data.models.HotelModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.AttractionList

interface AttractionRemoteDataSource {
    suspend fun attractionList(
        id: String
    ): Response<AttractionList>
}

const val attractionListEndpoint = "attraction/list"
class AttractionRemoteDataSourceImpl(private val client: Client, private val context: Context) :
    AttractionRemoteDataSource {

    override suspend fun attractionList(id: String): Response<AttractionList> {
        return withContext(Dispatchers.IO){
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://175.41.168.200:8888/api/v1/$attractionListEndpoint?location_code=$id",
                requestBody = null,
            )
            val response =  client.execute<AttractionList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(AttractionListModel.fromJson(responseData))
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
            is AttractionListModel -> responseData.toEntity() as? T
            is AttractionModel -> responseData.toEntity() as? T
            is List<*> -> responseData.mapNotNull { item ->
                when (item) {
                    is AttractionModel -> item.toEntity()
                    else -> null
                }
            } as? T
            else -> null
        }
    }
}