package com.trekkstay.hotel.feature.hotel.data.datasources


import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.feature.hotel.domain.entities.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import org.json.JSONObject
import java.io.File
import java.util.concurrent.TimeUnit

interface MediaRemoteDataSource {
    suspend fun uploadMedia(media: List<File>,extensions: List<String>): Response<Media>

}

const val uploadMediaEndpoint = "upload/media"

class MediaRemoteDataSourceImpl(private val engine: OkHttpClient) : MediaRemoteDataSource {
    private val client: OkHttpClient

    init {
        // Create OkHttpClient builder
        val clientBuilder = engine.newBuilder()

        // Set the read timeout to 30 seconds (you can adjust this value as needed)
        clientBuilder.readTimeout(60, TimeUnit.SECONDS)

        // Set the write timeout to 30 seconds (you can adjust this value as needed)
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS)

        // Build the OkHttpClient
        client = clientBuilder.build()
    }

    override suspend fun uploadMedia(media: List<File>,extensions: List<String>): Response<Media> {
        return withContext(Dispatchers.IO) {
            val requestBodyBuilder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
            for ((file, extension) in media.zip(extensions)) {
                requestBodyBuilder.addFormDataPart("files", file.name+".$extension",
                    file.asRequestBody("image/$extension".toMediaTypeOrNull())
                )
            }
            val requestBody = requestBodyBuilder.build()
            val request = Request.Builder()
                .url("http://52.163.61.213:8888/api/v1/$uploadMediaEndpoint")
                .post(requestBody)
                .addHeader("accept", "application/json")
                .build()
            try {
                val response = engine.newCall(request).execute()
                val responseBody = response.body?.string()
                val jsonObject = responseBody?.let { JSONObject(it) }
                val statusCode = jsonObject?.getInt("status_code")
                val message = jsonObject?.getString("message")
                val dataObject = jsonObject?.optJSONObject("data")
                val urlsArray = dataObject?.optJSONArray("urls")

                when (statusCode) {
                    200 -> {
                        val urls = mutableListOf<String>()
                        urlsArray?.let {
                            for (i in 0 until it.length()) {
                                urls.add(it.getString(i))
                            }
                        }
                        Response.Success(statusCode.toString(), message ?: "Success", Media(urls))
                    }
                    in 300..399 -> Response.Invalid(statusCode.toString(), message?: "Invalid")
                    else -> Response.Failure(statusCode.toString(), message?: "Failure")
                }
            } catch (e: IOException) {
                Response.Failure("-1", e.message ?: "Unknown error")
            }
        }
    }

}
