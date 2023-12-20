package com.lans.recipein_mobile.data.source.network.dto

import retrofit2.http.Query

data class SignUpRequestDto(
    val email: String,
    val username: String,
    val password: String,
)
