package com.lans.recipein_mobile.presentation.advanced_search.result

import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.model.RecipeIngredient

data class AdvancedSearchResultUIState(
    var recipes: List<Recipe> = emptyList(),
    var recipeIngredients: List<RecipeIngredient> = emptyList(),
    var error: String = "",
    var isLoading: Boolean = false,
)
