package com.trekkstay.hotel.feature.authenticate.data.repositories

import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.Either
import arrow.core.right
import arrow.core.left
import com.trekkstay.hotel.core.errors.Invalid
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.core.typedef.mapResult
import com.trekkstay.hotel.feature.authenticate.data.datasources.AuthRemoteDataSource
import com.trekkstay.hotel.feature.authenticate.domain.entities.VerifyKey
import com.example.hotel.feature.authenticate.domain.entities.JWTKey
import com.example.hotel.feature.authenticate.domain.repositories.AuthRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture


class AuthRepoImpl(private val remoteDataSource: AuthRemoteDataSource) : AuthRepo {

    override suspend fun login(email: String, pass: String): ResultFuture<JWTKey> {
        val res = remoteDataSource.login(email, pass)
        return handleResponse(res)
    }

    override suspend fun register(email: String, name: String, pass: String, role: String): ResultFuture<VerifyKey> {
        val res = remoteDataSource.register(email, name, pass, role)
        return handleResponse(res)
    }

    override suspend fun logout(): ResultFuture<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyEmail(email: String): ResultFuture<VerifyKey> {
        val res = remoteDataSource.verifyEmail(email)
        return handleResponse(res)
    }

    override suspend fun verifyOTP(key: VerifyKey, otp: String): ResultVoid {
        val res = remoteDataSource.verifyOTP(key, otp)
        return handleResponse(res)
    }

    override suspend fun resendOTP(key: VerifyKey): ResultVoid {
        val res = remoteDataSource.resendOTP(key)
        return handleResponse(res)
    }


    private fun <T> handleResponse(response: Response<T>): ResultFuture<T> {
        val future = CompletableFuture<Either<Invalid, T>>()

        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val result = when (response) {
                is Response.Success -> response.data!!.right()
                is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
                is Response.Failure -> ApiException(response.message ?: "Unknown error", response.status ?: "-1").left()
            }
            future.complete(mapResult(result))
        }

        return future
    }


}

