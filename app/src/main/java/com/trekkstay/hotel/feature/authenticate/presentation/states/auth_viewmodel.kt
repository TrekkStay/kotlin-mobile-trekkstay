package com.trekkstay.hotel.feature.authenticate.presentation.states

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepo: AuthRepo) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun processAction(action: AuthAction) {
        viewModelScope.launch {
            when (action) {
                is LoginAction -> {
                    _authState.postValue(AuthState.LoginCalling)
                    val result = authRepo.login(action.email, action.pass)
                    result.fold(
                        { failure -> _authState.postValue(AuthState.InvalidLogin(failure.message)) },
                        { jwtKey->  _authState.postValue(AuthState.SuccessLogin(jwtKey)) }
                    )
                }
                is RegisterAction -> {
                    _authState.postValue(AuthState.RegisterCalling)
                    val result = authRepo.register(action.email, action.name, action.pass)
                    result.fold(
                        { failure -> _authState.postValue(AuthState.InvalidRegister(failure.message)) },
                        { _authState.postValue(AuthState.SuccessRegister) }
                    )
                }
            }
        }
    }
}


