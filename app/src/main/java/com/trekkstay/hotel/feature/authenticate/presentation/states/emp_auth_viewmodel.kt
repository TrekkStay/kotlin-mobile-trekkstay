package com.trekkstay.hotel.feature.authenticate.presentation.states


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import kotlinx.coroutines.launch

class EmpAuthViewModel(private val authRepo: AuthRepo) : ViewModel() {
    private val _authState = MutableLiveData<EmpAuthState>()
    val authState: LiveData<EmpAuthState> = _authState

    fun processAction(action: EmpAuthAction) {
        viewModelScope.launch {
            when (action) {
                is EmpLoginAction -> {
                    _authState.postValue(EmpAuthState.EmpLoginCalling)
                    val result = authRepo.empLogin(action.email, action.pass)
                    result.fold(
                        { failure -> _authState.postValue(EmpAuthState.InvalidEmpLogin(failure.message)) },
                        { jwtKey->  _authState.postValue(EmpAuthState.SuccessEmpLogin(jwtKey)) }
                    )
                }
                is EmpRegisterAction -> {
                    _authState.postValue(EmpAuthState.EmpRegisterCalling)
                    val result = authRepo.empRegister(action.email, action.name, action.pass)
                    result.fold(
                        { failure -> _authState.postValue(EmpAuthState.InvalidEmpRegister(failure.message)) },
                        { _authState.postValue(EmpAuthState.SuccessEmpRegister) }
                    )
                }
                is EmpCreateAction -> {
                    _authState.postValue(EmpAuthState.EmpCreateCalling)
                    val result = authRepo.empCreate(action.email, action.name, action.phone,action.contract,action.salary)
                    result.fold(
                        { failure -> _authState.postValue(EmpAuthState.InvalidEmpCreate(failure.message)) },
                        { _authState.postValue(EmpAuthState.SuccessEmpCreate) }
                    )
                }
                is ViewEmpAction -> {
                    _authState.postValue(EmpAuthState.ViewEmpCalling)
                    val result = authRepo.viewEmp(action.hotelId)
                    result.fold(
                        { failure -> _authState.postValue(EmpAuthState.InvalidViewEmp(failure.message)) },
                        { list->  _authState.postValue(EmpAuthState.SuccessViewEmp(list)) }
                    )
                }
            }
        }
    }
}


