package com.trekkstay.hotel.core.typedef

import com.trekkstay.hotel.core.errors.Invalid
import arrow.core.Either
import java.util.concurrent.CompletableFuture

typealias ResultFuture<T> = CompletableFuture<Either<Invalid, T>>

typealias ResultVoid = ResultFuture<Unit>

fun <T> mapResult(result: Either<Any, T>): Either<Invalid, T> {
    @Suppress("UNCHECKED_CAST")
    return result as Either<Invalid, T>
}

typealias DataMap = Map<String, Any>

data class DataMapContainer(val data: DataMap)