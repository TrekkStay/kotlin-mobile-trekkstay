package com.trekkstay.hotel.core.network.client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.request.PreparedRequest
import com.trekkstay.hotel.core.network.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class Client(private val engine: OkHttpClient) {

    suspend fun <T> execute(
        request: RequestQuery,
        parser: ((Any?) -> T?)? = null,
        onSendProgress: ((Long, Long) -> Unit)? = null,
        onReceiveProgress: (() -> Unit)? = null
    ): Response<T> {
        val preparedRequest = PreparedRequest(
            request,
            parser,
            onSendProgress,
            onReceiveProgress,
            request.headers
        )
        return executeRequest(preparedRequest)
    }

    private suspend fun <T> executeRequest(request: PreparedRequest<T>): Response<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = engine.newCall(buildOkHttpRequest(request)).execute()
                val responseBody = response.body?.string()

                Response.whenResponse {
                    if (response.isSuccessful) {
                        val parsedData = request.parser?.let { it(responseBody?.toByteArray()) }
                        success(response.header("status_code") ?: "200", response.message, parsedData)
                    } else {
                        val statusCode = response.header("status_code")
                        val errorMessage = response.message
                        if ((statusCode != null) && (statusCode.toIntOrNull() != null) && (statusCode.toInt() >= 400)) {
                            invalid(statusCode, errorMessage)
                        } else {
                            failure(statusCode ?: "500", errorMessage)
                        }
                    }
                }
            } catch (e: Exception) {
                Response.whenResponse { failure("-1", "Error: $e") }
            }
        }
    }





    private fun buildOkHttpRequest(request: PreparedRequest<*>): Request {
        val requestBuilder = Request.Builder()
            .url(request.request.path)

        when (request.request.method) {
            RequestMethod.GET -> requestBuilder.get()
            RequestMethod.POST -> buildRequestBody(request)?.let { requestBuilder.post(it) }
            RequestMethod.PUT -> buildRequestBody(request)?.let { requestBuilder.put(it) }
            RequestMethod.PATCH -> buildRequestBody(request)?.let { requestBuilder.patch(it) }
            RequestMethod.DELETE -> requestBuilder.delete(buildRequestBody(request))
        }

        request.request.queryParams?.let { queryParams ->
            for ((key, value) in queryParams) {
                requestBuilder.addHeader(key, value.toString())
            }
        }

        request.headers?.let { headers ->
            for ((key, value) in headers) {
                requestBuilder.addHeader(key, value)
            }
        }

        return requestBuilder.build()
    }

    private fun buildRequestBody(request: PreparedRequest<*>): okhttp3.RequestBody? {
        val mediaType = "application/json".toMediaTypeOrNull()
        return request.request.requestBody?.toString()?.toRequestBody(mediaType)
    }
}