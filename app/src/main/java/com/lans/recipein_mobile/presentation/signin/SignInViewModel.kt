package com.lans.recipein_mobile.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.usecase.SaveSessionUseCase
import com.lans.recipein_mobile.domain.usecase.SignInUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveSessionUseCase: SaveSessionUseCase,
    private val validatorUseCase: ValidatorUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignInUIState())
    val state: Flow<SignInUIState> get() = _state

    fun signin(email: String, password: String) {
        viewModelScope.launch {
            signInUseCase.invoke(
                Auth().copy(
                    email = email,
                    password = password
                )
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        saveSessionUseCase.invoke(result.data)
                        _state.value = _state.value.copy(isLoggedIn = true)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    else -> Unit
                }
            }
        }
    }

    fun validateEmail(email: String): Boolean {
        val validate = validatorUseCase.email.invoke(email)
        _state.value = _state.value.copy(emailError = validate.errorMessage)
        return validate.isSuccess
    }

    fun validatePassword(password: String): Boolean {
        val validate = validatorUseCase.password.invoke(password)
        _state.value = _state.value.copy(passwordError = validate.errorMessage)
        return validate.isSuccess
    }
}