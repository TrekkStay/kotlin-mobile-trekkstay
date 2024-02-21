package com.trekkstay.hotel.feature.authenticate.data.datasources

import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.core.network.method.RequestMethod
import com.trekkstay.hotel.core.network.request.RequestQuery
import com.trekkstay.hotel.core.network.response.Response
import com.trekkstay.hotel.env.Env
import com.example.hotel.feature.authenticate.data.models.JWTKeyModel
import com.example.hotel.feature.authenticate.data.models.VerifyKeyModel
import com.example.hotel.feature.authenticate.data.models.toJWTKey
import com.example.hotel.feature.authenticate.data.models.toVerifyKey
import com.trekkstay.hotel.feature.authenticate.domain.entities.VerifyKey
import com.example.hotel.feature.authenticate.domain.entities.JWTKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRemoteDataSource {
    suspend fun login(email: String, pass: String): Response<JWTKey>
    suspend fun register(email: String, name: String, pass: String, role: String): Response<VerifyKey>
    suspend fun verifyEmail(email: String): Response<VerifyKey>
    suspend fun verifyOTP(key: VerifyKey, otp: String): Response<Unit>
    suspend fun resendOTP(key: VerifyKey): Response<Unit>
}

const val loginEndpoint = "user/login"
const val registerEndpoint = "user/signup"
const val verifyEmailEndpoint = "user/verify"
const val verifyOTPEndpoint = "user/confirm-verify"
const val resendOTPEndpoint = "user/otp-resend"

class AuthRemoteDataSourceImpl(private val client: Client) : AuthRemoteDataSource {

    override suspend fun login(email: String, pass: String): Response<JWTKey> {
        return withContext(Dispatchers.IO) {
            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "${Env.apiKey}$loginEndpoint",
                requestBody = mapOf("email" to email, "password" to pass)
            )

            val response = client.execute(
                request = request,
                parser = { responseData -> parseResponse<JWTKey>(responseData) }
            )

            response
        }
    }

    override suspend fun register(email: String, name: String, pass: String, role: String): Response<VerifyKey> {
        return withContext(Dispatchers.IO) {
            val request = RequestQuery(
                method = RequestMethod.POST,
                path = "${Env.apiKey}$registerEndpoint",
                requestBody = mapOf("email" to email, "name" to name, "password" to pass, "role" to role)
            )

            val response = client.execute<VerifyKey>(
                request = request,
                parser = { responseData -> parseResponse(responseData) }
            )

            response
        }
    }

    override suspend fun verifyEmail(email: String): Response<VerifyKey> {
        return withContext(Dispatchers.IO) {
            val request = RequestQuery(
                method = RequestMethod.GET,
                path = "${Env.apiKey}$verifyEmailEndpoint?email=$email",
                requestBody = emptyMap()
            )

            val response = client.execute<VerifyKey>(
                request = request,
                parser = { responseData -> parseResponse(responseData) }
            )

            response
        }
    }


    override suspend fun verifyOTP(key: VerifyKey, otp: String): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val request = RequestQuery(
                headers = mapOf("authorization" to "Bearer ${key.key}"),
                method = RequestMethod.POST,
                path = "${Env.apiKey}$verifyOTPEndpoint?otp=$otp",
                requestBody = emptyMap()
            )
            client.execute(request, parser = null)
        }
    }

    override suspend fun resendOTP(key: VerifyKey): Response<Unit> {
        return withContext(Dispatchers.IO) {
            val request = RequestQuery(
                headers = mapOf("authorization" to "Bearer ${key.key}"),
                method = RequestMethod.GET,
                path = "${Env.apiKey}$resendOTPEndpoint",
                requestBody = emptyMap()
            )
            client.execute(request, parser = null)
        }
    }

    private fun <T> parseResponse(responseData: Any?): T? {
        return when (responseData) {
            is JWTKeyModel -> responseData.toJWTKey() as T
            is VerifyKeyModel -> responseData.toVerifyKey() as T
            else -> null
        }
    }

}
