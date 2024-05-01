package com.trekkstay.hotel.feature.authenticate.domain.usecases

import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.core.usecase.UseCaseWithParams

class EmpCreateUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<Unit, EmpCreateUseCaseParams> {

    override suspend fun call(params: EmpCreateUseCaseParams): ResultFuture<Unit> {
        return repository.empCreate(params.email,params.name, params.phone,params.contract,params.salary)
    }
}
data class EmpCreateUseCaseParams(val email: String, val name: String, val phone: String,val contract:String,val salary: Int)