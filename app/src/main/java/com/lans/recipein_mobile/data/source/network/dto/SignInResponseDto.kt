package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.model.Token
import com.squareup.moshi.Json

data class SignInResponseDto(
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "user_id")
    val user_id: Int,
    @field:Json(name = "access_token")
    val access_token: String,
    @field:Json(name = "refresh_token")
    val refresh_token: String,
)

fun SignInResponseDto.toDomain() = Token(
    user_id = user_id,
    access_token = access_token,
    refresh_token = refresh_token
)