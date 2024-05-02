package com.trekkstay.hotel.feature.hotel.data.datasources

import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.feature.hotel.data.models.LocationListModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocationRemoteDataSource {
    suspend fun viewProvince(): Response<LocationList>
    suspend fun viewDistrict(code: String): Response<LocationList>
    suspend fun viewWard(code: String): Response<LocationList>

}

const val viewDistrictEndpoint = "region/list-district"
const val viewProvinceEndpoint = "region/list-province"
const val viewWardEndpoint = "region/list-ward"

class LocationRemoteDataSourceImpl(private val client: Client) : LocationRemoteDataSource {

    override suspend fun viewDistrict(code:String): Response<LocationList> {
        return withContext(Dispatchers.IO) {

            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://175.41.168.200:8888/api/v1/$viewDistrictEndpoint?province_code=${code}",
                requestBody = null
            )

            val response = client.execute<LocationList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(LocationListModel.fromJson(responseData))
                    } else {
                        null
                    }
                }


            )
            response
        }
    }

    override suspend fun viewProvince(): Response<LocationList> {
        return withContext(Dispatchers.IO) {


            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://175.41.168.200:8888/api/v1/$viewProvinceEndpoint",
                requestBody = null
            )

            val response = client.execute<LocationList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(LocationListModel.fromJson(responseData))
                    } else {
                        null
                    }
                }
            )

            response
        }
    }

    override suspend fun viewWard(code: String): Response<LocationList> {
        return withContext(Dispatchers.IO) {


            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "http://175.41.168.200:8888/api/v1/$viewWardEndpoint?district_code=${code}",
                requestBody = null
            )

            val response = client.execute<LocationList>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(LocationListModel.fromJson(responseData))
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
            is LocationListModel -> responseData.toEntity() as? T
            else -> null
        }
    }

}
