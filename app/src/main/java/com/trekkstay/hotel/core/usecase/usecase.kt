package com.trekkstay.hotel.core.usecase

import com.trekkstay.hotel.core.typedef.ResultFuture

interface UseCaseWithoutParams<Type> {
    fun call(): ResultFuture<Type>
}

interface UseCaseWithParams<Type, Params> {
    suspend fun call(params: Params): ResultFuture<Type>
}
