package com.lans.recipein_mobile.domain.model

data class RecipeInstruction(
    val id: Int = 0,
    val recipeId: Int = 0,
    val instructionId: Int = 0,
    val instructionDescription: String = "",
)
