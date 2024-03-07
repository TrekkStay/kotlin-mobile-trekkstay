package com.trekkstay.hotel.feature.authenticate.presentation.states

import com.trekkstay.hotel.feature.authenticate.domain.entities.VerifyKey
import com.trekkstay.hotel.feature.authenticate.domain.entities.JWTKey

sealed class AuthState {
    object Idle : AuthState()

    object LoginCalling : AuthState()
    data class InvalidLogin(val message: String) : AuthState()
    data class SuccessLogin(val jwtKey: JWTKey) : AuthState()

    object RegisterCalling : AuthState()
    data class InvalidRegister(val message: String) : AuthState()
    object SuccessRegister : AuthState()


    data class AuthenticateError(val message: String) : AuthState()
}