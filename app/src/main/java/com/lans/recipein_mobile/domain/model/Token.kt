package com.lans.recipein_mobile.domain.model

data class Token(
    val user_id: Int,
    val access_token: String,
    val refresh_token: String,
)
