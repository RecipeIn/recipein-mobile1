package com.lans.recipein_mobile.domain.model

data class RecipeNutrition(
    val id: Int = 0,
    val recipeId: Int = 0,
    val nutritionId: Int = 0,
    val qty: Int = 0,
    val nutritionDescription: String = "",
)
