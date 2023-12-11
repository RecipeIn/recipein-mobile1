package com.lans.recipein_mobile.presentation.signup

data class SignUpUIState(
    var isSuccess: Boolean = false,
    var error: String = "",
    var isLoading: Boolean = false,
    var emailError: String? = "",
    var usernameError: String? = "",
    var passwordError: String? = "",
    var confirmPasswordError: String? = "",
)
