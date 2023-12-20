package com.lans.recipein_mobile.presentation.signin

data class SignInUIState(
    var isLoggedIn: Boolean = false,
    var error: String = "",
    var isLoading: Boolean = false,
    var emailError: String? = "",
    var passwordError: String? = "",
)
