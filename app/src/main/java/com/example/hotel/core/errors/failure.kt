package com.example.hotel.core.errors

sealed class Failure(val message: String, val statusCode: String) {
    abstract val errorMessage: String
}

class ApiFailure(message: String, statusCode: String) : Failure(message, statusCode) {
    constructor(exception: ApiException) : this(exception.message, exception.statusCode)

    override val errorMessage: String
        get() = "$statusCode Error: $message"
}

class StorageFailure(message: String) : Failure(message, "-1") {
    override val errorMessage: String
        get() = "$statusCode Error: $message"
}
