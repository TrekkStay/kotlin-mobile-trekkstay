package com.trekkstay.hotel.feature.authenticate.presentation.states


sealed class AuthAction {}

data class LoginAction(val email: String, val pass: String) : AuthAction()

data class RegisterAction(val name:String,val email: String, val pass: String) : AuthAction()
