package com.example.hotel.core.usecase

import com.example.hotel.core.typedef.ResultFuture

interface UseCaseWithoutParams<Type> {
    fun call(): ResultFuture<Type>
}

interface UseCaseWithParams<Type, Params> {
    suspend fun call(params: Params): ResultFuture<Type>
}
