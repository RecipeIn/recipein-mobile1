package com.lans.recipein_mobile.presentation.home

import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.Recipe

data class HomeUIState(
    var categories: List<Category> = emptyList(),
    var weeklyFoods: List<Recipe> = emptyList(),
    var weeklyDrinks: List<Recipe> = emptyList(),
    var recipeCollections: List<Category> = emptyList(),
    var newRecipes: List<Recipe> = emptyList(),
    var error: String = "",
    var isLoading: Boolean = false,
)
