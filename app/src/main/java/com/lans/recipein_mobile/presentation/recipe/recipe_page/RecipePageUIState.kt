package com.lans.recipein_mobile.presentation.recipe.recipe_page

import com.lans.recipein_mobile.domain.model.RecipeIngredient
import com.lans.recipein_mobile.domain.model.RecipeInstruction
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.model.RecipeNutrition

data class RecipePageUIState(
    var userId: Int = 0,
    var recipe: Recipe? = null,
    var recipeIngredients: List<RecipeIngredient> = emptyList(),
    var recipeInstructions: List<RecipeInstruction> = emptyList(),
    var recipeNutrisions: List<RecipeNutrition> = emptyList(),
    var favorite: List<Int>? = null,
    var error: String = "",
    var isLoading: Boolean = false,
)