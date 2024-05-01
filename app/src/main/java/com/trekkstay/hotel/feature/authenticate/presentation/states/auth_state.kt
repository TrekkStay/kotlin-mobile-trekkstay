package com.trekkstay.hotel.feature.authenticate.presentation.states

import com.trekkstay.hotel.feature.authenticate.domain.entities.LoginRes

sealed class AuthState {
    object Idle : AuthState()

    object LoginCalling : AuthState()
    data class InvalidLogin(val message: String) : AuthState()
    data class SuccessLogin(val res: LoginRes) : AuthState()

    object RegisterCalling : AuthState()
    data class InvalidRegister(val message: String) : AuthState()
    object SuccessRegister : AuthState()

    object ChangePasswordCalling : AuthState()
    data class InvalidChangePassword(val message: String) : AuthState()
    object SuccessChangePassword : AuthState()


    data class AuthenticateError(val message: String) : AuthState()
}