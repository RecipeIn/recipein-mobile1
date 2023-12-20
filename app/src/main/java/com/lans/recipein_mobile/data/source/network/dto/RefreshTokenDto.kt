package com.lans.recipein_mobile.data.source.network.dto

import com.squareup.moshi.Json

data class RefreshTokenDto (
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "refresh_token")
    val refreshToken: String,
)