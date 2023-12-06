package com.lans.recipein_mobile.data.source.network.dto

import retrofit2.http.Query

data class SignUpRequestDto(
    @Query("email") val email: String,
    @Query("username") val username: String,
    @Query("password") val password: String,
)
