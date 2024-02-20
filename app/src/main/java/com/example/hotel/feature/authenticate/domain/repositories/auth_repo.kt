package com.example.hotel.feature.authenticate.domain.repositories

import com.example.hotel.core.typedef.ResultFuture
import com.example.hotel.feature.authenticate.domain.entities.VerifyKey
import com.example.hotel.feature.authenticate.domain.entities.JWTKey


interface AuthRepo {
    suspend fun verifyEmail(email: String): ResultFuture<VerifyKey>
    suspend fun verifyOTP(key: VerifyKey, otp: String): ResultFuture<Unit>
    suspend fun resendOTP(key: VerifyKey): ResultFuture<Unit>
    suspend fun login(email: String, pass: String): ResultFuture<JWTKey>
    suspend fun register(email: String, name: String, pass: String, role: String): ResultFuture<VerifyKey>
    suspend fun logout(): ResultFuture<Unit>
}
