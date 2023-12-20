package com.lans.recipein_mobile.presentation.favorite

import com.lans.recipein_mobile.domain.model.Recipe

data class FavoriteUIState(
    var favorite: List<Int> = emptyList(),
    var recipes: List<Recipe> = emptyList(),
    var error: String = "",
    var isLoading: Boolean = false,
)