package com.trekkstay.hotel.feature.authenticate.data.datasources

import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.env.Env
import com.trekkstay.hotel.feature.authenticate.data.models.JWTKeyModel
import com.trekkstay.hotel.feature.authenticate.data.models.VerifyKeyModel
import com.trekkstay.hotel.feature.authenticate.data.models.toJWTKey
import com.trekkstay.hotel.feature.authenticate.data.models.toVerifyKey
import com.trekkstay.hotel.feature.authenticate.domain.entities.JWTKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

interface AuthRemoteDataSource {
    suspend fun login(email: String, pass: String): Response<JWTKey>
    suspend fun register(email: String, name: String, pass: String): Response<Unit>
}

const val loginEndpoint = "user/login"
const val registerEndpoint = "user/signup"

class AuthRemoteDataSourceImpl(private val client: Client) : AuthRemoteDataSource {

    override suspend fun login(email: String, pass: String): Response<JWTKey> {
        return withContext(Dispatchers.IO) {
            val requestBodyJson = JSONObject().apply {
                put("email", email)
                put("password", pass)
            }

            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "${Env.apiKey}$loginEndpoint",
                requestBody = requestBodyJson.toString()
            )

            val response = client.execute<JWTKey>(
                request = request,
                parser = { responseData -> parseResponse(responseData) }
            )

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
                request = request,
                parser = { responseData -> parseResponse(responseData) }
            )

            response
        }
    }


    private inline fun <reified T : Any> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is JWTKeyModel -> responseData.toJWTKey() as? T
            is VerifyKeyModel -> responseData.toVerifyKey() as? T
            else -> null
        }
    }



}
