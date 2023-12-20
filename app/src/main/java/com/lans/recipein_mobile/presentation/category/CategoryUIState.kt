package com.lans.recipein_mobile.presentation.category

import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.Recipe

data class CategoryUIState(
    var category: Category? = null,
    var recipe: List<Recipe> = emptyList(),
    var error: String = "",
    var isLoading: Boolean = false,
)
