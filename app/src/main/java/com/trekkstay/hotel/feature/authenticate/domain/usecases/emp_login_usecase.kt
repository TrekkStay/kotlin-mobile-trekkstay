package com.trekkstay.hotel.feature.authenticate.domain.usecases


import com.trekkstay.hotel.core.typedef.ResultFuture
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.core.usecase.UseCaseWithParams
import com.trekkstay.hotel.feature.authenticate.domain.entities.Employee

class EmpLoginUseCase(private val repository: AuthRepo) :
    UseCaseWithParams<Employee, EmpLoginUseCaseParams> {

    override suspend fun call(params: EmpLoginUseCaseParams): ResultFuture<Employee> {
        return repository.empLogin(params.email, params.pass)
    }
}
data class EmpLoginUseCaseParams(val email: String, val pass: String)