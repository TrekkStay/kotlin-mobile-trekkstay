package com.example.hotel.core.network.response
sealed class Response<T>(val status: String?, val message: String?, val data: T?) {

    class Success<T>(status: String, message: String, data: T?) : Response<T>(status, message, data)

    class Invalid<T>(status: String, message: String) : Response<T>(status, message, null)

    class Failure<T>(status: String, message: String) : Response<T>(status, message, null)

    companion object {

        inline fun <T> whenResponse(builder: ResponseBuilder<T>.() -> Unit): Response<T> {
            val responseBuilder = ResponseBuilder<T>()
            responseBuilder.builder()
            return responseBuilder.build()
        }
    }
}

class ResponseBuilder<T> {
    private var data: T? = null
    private var status: String? = null
    private var message: String? = null

    fun success(status: String, message: String, data: T?): Response<T> {
        this.status = status
        this.message = message
        this.data = data
        return Response.Success(status, message, data)
    }

    fun invalid(status: String, message: String): Response<T> {
        this.status = status
        this.message = message
        return Response.Invalid(status, message)
    }

    fun failure(status: String, message: String): Response<T> {
        this.status = status
        this.message = message
        return Response.Failure(status, message)
    }

    fun build(): Response<T> {
        return if (data != null) {
            success(status ?: "", message ?: "", data)
        } else if (status != null && status!!.toIntOrNull() != null) {
            if (status!!.toInt() >= 400) {
                invalid(status!!, message ?: "")
            } else {
                failure(status!!, message ?: "")
            }
        } else {
            failure("-1", "Unknown error")
        }
    }
}
