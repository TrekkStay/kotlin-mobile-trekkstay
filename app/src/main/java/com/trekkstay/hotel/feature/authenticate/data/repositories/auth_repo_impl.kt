package com.trekkstay.hotel.feature.authenticate.data.repositories

import com.trekkstay.hotel.core.errors.ApiException
import com.trekkstay.hotel.core.errors.ApiInvalid
import com.trekkstay.hotel.core.network.response.Response
import arrow.core.right
import arrow.core.left
import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.authenticate.data.datasources.AuthRemoteDataSource
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo


class AuthRepoImpl(private val remoteDataSource: AuthRemoteDataSource) : AuthRepo {

    override suspend fun login(email: String, pass: String): ResultFuture<LoginRes> {
        return when (val response= remoteDataSource.login(email, pass)) {
            is Response.Success -> response.data!!.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }

    override suspend fun register(email: String, name: String, pass: String): ResultVoid {
        return when (val response = remoteDataSource.register(email, name, pass)) {
            is Response.Success -> Unit.right()
            is Response.Invalid -> ApiInvalid(response.message ?: "Unknown error", response.status ?: "-1").left()
            is Response.Failure -> throw ApiException(response.message ?: "Unknown error", response.status ?: "-1")
        }
    }

    override suspend fun logout(): ResultVoid {
        TODO("Not yet implemented")
    }


}

