package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.model.RecipeIngredient
import com.squareup.moshi.Json

data class RecipeIngredientResponseDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "recipe_id")
    val recipeId: Int,
    @field:Json(name = "ingredient_id")
    val ingredientId: Int,
    @field:Json(name = "unit_id")
    val unitId: Int,
    @field:Json(name = "qty")
    val qty: Int,
    @field:Json(name = "recipe_name")
    val recipeName: String,
    @field:Json(name = "ingredient_name")
    val ingredientName: String,
    @field:Json(name = "unit_name")
    val unitName: String,
)

fun RecipeIngredientResponseDto.toDomain() = RecipeIngredient(
    id = id,
    recipeId = recipeId,
    ingredientId = ingredientId,
    unitId = unitId,
    qty = qty,
    recipeName = recipeName,
    ingredientName = ingredientName,
    unitName = unitName
)
