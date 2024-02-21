package com.example.hotel.feature.authenticate.presentation.states

import com.example.hotel.feature.authenticate.domain.entities.VerifyKey
import com.example.hotel.feature.authenticate.domain.entities.JWTKey

sealed class AuthState {
    object AuthInitial : AuthState()

    object Login : AuthState()
    object LoginCalling : AuthState()
    data class InvalidLogin(val message: String) : AuthState()
    data class SuccessLogin(val jwtKey: JWTKey) : AuthState()

    object RegisterCalling : AuthState()
    data class InvalidRegister(val message: String) : AuthState()
    data class SuccessRegister(val verifyKey: VerifyKey) : AuthState()

    object VerifyEmailCalling : AuthState()
    data class InvalidEmailVerify(val message: String) : AuthState()
    data class SuccessEmailVerify(val verifyKey: VerifyKey) : AuthState()

    object VerifyOTPCalling : AuthState()
    data class InvalidOTPVerify(val message: String) : AuthState()
    object SuccessOTPVerify : AuthState()

    object ResendOTPCalling : AuthState()
    data class InvalidOTPResend(val message: String) : AuthState()
    object SuccessOTPResend : AuthState()

    data class AuthenticateError(val message: String) : AuthState()
}