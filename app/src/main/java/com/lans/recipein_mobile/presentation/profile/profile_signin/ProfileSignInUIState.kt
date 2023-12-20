package com.lans.recipein_mobile.presentation.profile.profile_signin

import com.lans.recipein_mobile.domain.model.User

data class ProfileSignInUIState(
    var profile: User? = null,
    var isUpdated: Boolean = false,
    var error: String = "",
    var isLoading: Boolean = false,
)
