package com.trekkstay.hotel.feature.authenticate.domain.repositories

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.core.typedef.ResultVoid
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes


interface AuthRepo {
    suspend fun login(email: String, pass: String): ResultFuture<LoginRes>
    suspend fun register(email: String, name: String, pass: String): ResultVoid
    suspend fun logout(): ResultVoid
}
