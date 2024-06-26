package com.trekkstay.hotel.feature.hotel.data.datasources

import android.content.Context
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.core.storage.LocalStore
import com.trekkstay.hotel.feature.hotel.data.models.DestinationListModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.DestinationList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SearchRemoteDataSource {
    suspend fun viewDestination(): Response<DestinationList>
}

const val viewDestinationEndpoint = "destination/list"

class SearchRemoteDataSourceImpl(private val client: Client, private val context: Context) : SearchRemoteDataSource {
    override suspend fun viewDestination(): Response<DestinationList> {
        return withContext(Dispatchers.IO) {
            val jwtKey = LocalStore.getKey(context, "jwtKey", "")
            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://175.41.168.200:8888/api/v1/$viewDestinationEndpoint",
                headers = mapOf("Authorization" to "Bearer $jwtKey"),
                requestBody = null,
            )


            val response = client.execute<DestinationList>(request = request,parser = { responseData ->
                if (responseData is String) {
                    parseResponse(DestinationListModel.fromJson(responseData))
                } else {
                    null
                }
            })
            response
        }
    }
    

    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is DestinationListModel -> responseData.toEntity() as? T
            else -> null
        }
    }

}
