package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.enum.RecipeStatus
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.Recipe
import com.squareup.moshi.Json

data class RecipeResponseDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "user_id")
    val userId: Int,
    @field:Json(name = "user_username")
    val username: String,
    @field:Json(name = "category_id")
    val categoryId: Int,
    @field:Json(name = "category_name")
    val categoryName: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "rating")
    val rating: Float,
    @field:Json(name = "preparation_time")
    val preparationTime: Int,
    @field:Json(name = "cooking_time")
    val cookingTime: Int,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "image")
    val image: String,
)

fun RecipeResponseDto.toDomain() = Recipe(
    id = id,
    userId = userId,
    username = username,
    category = Category(categoryId, categoryName),
    name = name,
    description = description,
    rating = rating,
    preparationTime = preparationTime,
    cookingTime = cookingTime,
    status = RecipeStatus.valueOf(status),
    image = image
)