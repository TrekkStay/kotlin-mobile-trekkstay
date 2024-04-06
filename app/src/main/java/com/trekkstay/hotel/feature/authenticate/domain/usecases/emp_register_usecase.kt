package com.trekkstay.hotel.feature.authenticate.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.core.usecase.UseCaseWithParams

class EmpRegisterUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<Unit, EmpRegisterUseCaseParams> {

    override suspend fun call(params: EmpRegisterUseCaseParams): ResultFuture<Unit> {
        return repository.empRegister(params.email,params.name, params.pass)
    }
}
data class EmpRegisterUseCaseParams(val email: String, val name: String,val pass: String)