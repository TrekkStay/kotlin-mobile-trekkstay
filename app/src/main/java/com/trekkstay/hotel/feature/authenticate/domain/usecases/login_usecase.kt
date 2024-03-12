package com.trekkstay.hotel.feature.authenticate.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes

class LoginUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<LoginRes, LoginUseCaseParams> {

    override suspend fun call(params: LoginUseCaseParams): ResultFuture<LoginRes> {
        return repository.login(params.email, params.pass)
    }
}
data class LoginUseCaseParams(val email: String, val pass: String)