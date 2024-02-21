package com.trekkstay.hotel.feature.authenticate.presentation.states
import com.trekkstay.hotel.feature.authenticate.domain.entities.VerifyKey
import com.example.hotel.feature.authenticate.domain.entities.JWTKey


sealed class AuthAction {
    object LoginCalling : AuthAction()
    data class LoginSuccess(val jwtKey: JWTKey) : AuthAction()
    data class LoginFailure(val error: String) : AuthAction()

    object RegisterCalling : AuthAction()
    data class RegisterSuccess(val verifyKey: VerifyKey) : AuthAction()
    data class RegisterFailure(val error: String) : AuthAction()


}