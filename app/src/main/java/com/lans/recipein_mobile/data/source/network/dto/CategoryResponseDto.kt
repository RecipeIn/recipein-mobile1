package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.model.Category
import com.squareup.moshi.Json

data class CategoryResponseDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
)

fun CategoryResponseDto.toDomain() = Category(
    id = id,
    name = name
)
