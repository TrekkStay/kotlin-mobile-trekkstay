package com.trekkstay.hotel.core.errors

sealed class Invalid(val message: String, val statusCode: String, val data: Any?) {
    abstract val errorMessage: String
}

class ApiInvalid(message: String, statusCode: String, data: Any? = null) : Invalid(message, statusCode, data) {
    override val errorMessage: String
        get() = "$statusCode , Invalid: $message"
}

class StorageInvalid(message: String) : Invalid(message, "-1", null) {
    override val errorMessage: String
        get() = "$statusCode , Invalid: $message"
}