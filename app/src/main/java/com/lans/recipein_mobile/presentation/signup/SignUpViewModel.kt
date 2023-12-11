package com.lans.recipein_mobile.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.usecase.SignUpUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validatorUseCase: ValidatorUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpUIState())
    val state: Flow<SignUpUIState> get() = _state

    fun signup(email: String, username: String, password: String) {
        viewModelScope.launch {
            signUpUseCase.invoke(
                Auth().copy(
                    email = email,
                    username = username,
                    password = password
                )
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(isSuccess = true)
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

    fun validateUsername(username: String): Boolean {
        val validate = validatorUseCase.username.invoke(username)
        _state.value = _state.value.copy(usernameError = validate.errorMessage)
        return validate.isSuccess
    }

    fun validatePassword(password: String): Boolean {
        val validate = validatorUseCase.password.invoke(password)
        _state.value = _state.value.copy(passwordError = validate.errorMessage)
        return validate.isSuccess
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        val validate = validatorUseCase.confirmPassword.invoke(password, confirmPassword)
        _state.value = _state.value.copy(confirmPasswordError = validate.errorMessage)
        return validate.isSuccess
    }
}