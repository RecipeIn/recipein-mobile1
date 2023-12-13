package com.lans.recipein_mobile.data.source.network.dto

import com.squareup.moshi.Json

data class RecipeListResponseDto(
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "data")
    val data: List<RecipeResponseDto>?,
)
