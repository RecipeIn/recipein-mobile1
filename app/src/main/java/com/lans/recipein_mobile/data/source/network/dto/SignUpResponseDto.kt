package com.lans.recipein_mobile.data.source.network.dto

import com.squareup.moshi.Json

data class SignUpResponseDto(
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "user_id")
    val user_id: Int
)
