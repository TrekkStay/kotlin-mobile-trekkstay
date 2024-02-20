package com.example.hotel.feature.authenticate.domain.usecases

import com.example.hotel.core.typedef.ResultFuture
import com.example.hotel.feature.authenticate.domain.entities.JWTKey
import com.example.hotel.feature.authenticate.domain.repositories.AuthRepo
import android.os.Parcelable
import com.example.hotel.core.usecase.UseCaseWithParams
import kotlinx.parcelize.Parcelize

class LoginUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<JWTKey, LoginUseCaseParams> {

    override suspend fun call(params: LoginUseCaseParams): ResultFuture<JWTKey> {
        return repository.login(params.email, params.pass)
    }
}

@Parcelize
data class LoginUseCaseParams(val email: String, val pass: String) : Parcelable