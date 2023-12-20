package com.lans.recipein_mobile.domain.model

data class RecipeIngredient(
    val id: Int = 0,
    val recipeId: Int = 0,
    val ingredientId: Int = 0,
    val unitId: Int = 0,
    val qty: Int = 0,
    val recipeName: String = "",
    val ingredientName: String = "",
    val unitName: String = "",
)
