package com.trekkstay.hotel.core.network.client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.request.PreparedRequest
import com.trekkstay.hotel.core.network.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

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
                print(responseBody)
                val jsonResponse = responseBody?.let { JSONObject(it) }
                jsonResponse?.let { json ->
                    val data = json.opt("data")
                    if (data != null) {
                        print(data.toString())
                    }
                    Response.whenResponse {
                        if (response.isSuccessful && data != null) {
                            success(response.header("status_code") ?: "201", response.message,  request.parser?.invoke(data.toString()))
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
                } ?: Response.whenResponse {
                    failure("-1", "Error: Response body is null")
                }
            } catch (e: Exception) {
                Response.whenResponse { failure("-1", "Error: ${e.message}") }
            }
        }

    }

    private fun buildOkHttpRequest(request: PreparedRequest<*>): Request {
        val urlBuilder = request.request.path.toHttpUrlOrNull()?.newBuilder()
            ?: throw IllegalArgumentException("Invalid URL")

        request.request.queryParams?.forEach { (key, value) ->
            urlBuilder.addQueryParameter(key, value.toString())
        }

        val requestBuilder = Request.Builder()

        val requestBody = buildRequestBody(request)
        when (request.request.method) {
            RequestMethod.GET -> requestBuilder.get()
            RequestMethod.POST -> requestBody?.let { requestBuilder.post(it) }
            RequestMethod.PUT -> requestBody?.let { requestBuilder.put(it) }
            RequestMethod.PATCH -> requestBody?.let { requestBuilder.patch(it) }
            RequestMethod.DELETE -> requestBuilder.delete()
        }

        request.headers?.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        return requestBuilder.url(urlBuilder.build()).build()
    }

    private fun buildRequestBody(request: PreparedRequest<*>): okhttp3.RequestBody? {
        val mediaType = "application/json".toMediaTypeOrNull()
        val requestBodyString = request.request.requestBody
        return if (mediaType != null && requestBodyString != null) {
            requestBodyString.toRequestBody(mediaType)
        } else {
            null
        }
    }


}