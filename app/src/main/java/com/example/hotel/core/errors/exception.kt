package com.example.hotel.core.errors


import java.lang.Exception

class ApiException(override val message: String, val statusCode: String) : Exception()

class StorageException(override val message: String) : Exception()
