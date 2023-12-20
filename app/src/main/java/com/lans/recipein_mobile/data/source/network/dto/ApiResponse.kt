package com.lans.recipein_mobile.data.source.network.dto

import com.squareup.moshi.Json

data class ApiResponse<T>(
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "data")
    val data: T?,
    @field:Json(name = "user")
    val user: ProfileResponseDto?,
)
