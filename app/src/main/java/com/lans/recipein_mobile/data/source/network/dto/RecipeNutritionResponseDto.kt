package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.model.RecipeNutrition
import com.squareup.moshi.Json

data class RecipeNutritionResponseDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "recipe_id")
    val recipeId: Int,
    @field:Json(name = "nutrition_id")
    val nutritionId: Int,
    @field:Json(name = "qty")
    val qty: Int,
    @field:Json(name = "nutrition_description")
    val nutritionDescription: String
)

fun RecipeNutritionResponseDto.toDomain() = RecipeNutrition(
    id = id,
    recipeId = recipeId,
    nutritionId = nutritionId,
    qty = qty,
    nutritionDescription = nutritionDescription
)
