package com.trekkstay.hotel.feature.authenticate.data.datasources

import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.env.Env
import com.trekkstay.hotel.feature.authenticate.data.models.LoginResModel
import com.trekkstay.hotel.feature.authenticate.data.models.toLoginRes
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

interface AuthRemoteDataSource {
    suspend fun login(email: String, pass: String): Response<LoginRes>
    suspend fun register(email: String, name: String, pass: String): Response<Unit>
}

const val loginEndpoint = "user/login"
const val registerEndpoint = "user/signup"

class AuthRemoteDataSourceImpl(private val client: Client) : AuthRemoteDataSource {

    override suspend fun login(email: String, pass: String): Response<LoginRes> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("email", email)
                put("password", pass)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$loginEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<LoginRes>(
                request = request,
                parser = { responseData ->
                    if (responseData is String) {
                        parseResponse(LoginResModel.fromJson(responseData))
                    } else {
                        null // Handle the case where responseData is not a String
                    }
                }


            )
            println("${response.data} tried doing")
            response
        }
    }

    override suspend fun register(email: String, name: String, pass: String): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("full_name", name)
                put("email", email)
                put("phone", "")
                put("password", pass)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "http://52.163.61.213:8888/api/v1/$registerEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<Unit>(
                request = request
            )

            response
        }
    }


    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        println("check for function call")
        return when (responseData) {
            is LoginResModel -> responseData.toLoginRes() as? T
            else -> null
        }
    }

}
