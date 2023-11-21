package com.lans.recipein_mobile.presentation.signin

import com.lans.recipein_mobile.domain.model.User

data class SignInUIState(
    var user: User? = null,
    var error: String = "",
    var isLoading: Boolean = false,
    var emailError: String? = "",
    var passwordError: String? = ""
)
