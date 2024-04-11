package com.trekkstay.hotel.feature.hotel.data.datasources


import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.feature.hotel.data.models.LocationListModel
import com.trekkstay.hotel.feature.hotel.data.models.MediaModel
import com.trekkstay.hotel.feature.hotel.data.models.toEntity
import com.trekkstay.hotel.feature.hotel.domain.entities.LocationList
import com.trekkstay.hotel.feature.hotel.domain.entities.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import org.json.JSONObject

interface MediaRemoteDataSource {
    suspend fun uploadMedia(media: List<MultipartBody.Part>): Response<Media>

}

const val uploadMediaEndpoint = "upload/media"

class MediaRemoteDataSourceImpl(private val client: Client) : MediaRemoteDataSource {

    override suspend fun uploadMedia(media: List<MultipartBody.Part>): Response<Media> {
        return withContext(Dispatchers.IO) {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .apply {
                    for (part in media) {
                        addPart(part)
                    }
                }
                .build()

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$uploadMediaEndpoint",
                requestBody = requestBody.toString()
            )

            val response = client.execute<Media>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(MediaModel.fromJson(responseData))
                    } else {
                        null
                    }
                }


            )
            println("${response.data} tried doing")
            response
        }
    }


    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is MediaModel -> responseData.toEntity() as? T
            else -> null
        }
    }

}
