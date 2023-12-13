package com.lans.recipein_mobile.domain.model

data class Token(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
)
