package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.model.UserNutrition
import com.squareup.moshi.Json

data class UserNutritionResponseDto(
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "user_id")
    val userId: Int = 0,
    @field:Json(name = "calories")
    val calories: Int = 0,
    @field:Json(name = "protein")
    val protein: Int = 0,
    @field:Json(name = "carbohydrate")
    val carbohydrate: Int = 0,
    @field:Json(name = "fiber")
    val fiber: Int = 0,
    @field:Json(name = "fat")
    val fat: Int = 0,
)

fun UserNutritionResponseDto.toDomain() = UserNutrition(
    id = id,
    userId = userId,
    calories = calories,
    protein = protein,
    carbohydrate = carbohydrate,
    fiber = fiber,
    fat = fat
)