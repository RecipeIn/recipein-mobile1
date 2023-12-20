package com.lans.recipein_mobile.presentation.recipe

import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.Recipe

data class RecipeUIState(
    var recipes: List<Recipe> = emptyList(),
    var categories: List<Category> = emptyList(),
    var error: String = "",
    var isLoading: Boolean = false,
)
