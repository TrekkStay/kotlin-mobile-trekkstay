package com.trekkstay.hotel.feature.authenticate.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.core.usecase.UseCaseWithParams

class RegisterUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<Unit, RegisterUseCaseParams> {

    override suspend fun call(params: RegisterUseCaseParams): ResultFuture<Unit> {
        return repository.register(params.email,params.name, params.pass)
    }
}
data class RegisterUseCaseParams(val email: String, val name: String,val pass: String)