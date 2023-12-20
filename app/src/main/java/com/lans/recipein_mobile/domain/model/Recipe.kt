package com.lans.recipein_mobile.domain.model

import com.lans.recipein_mobile.domain.enum.RecipeStatus

data class Recipe(
    val id: Int = 0,
    val userId: Int = 0,
    val username: String = "",
    val category: Category? = null,
    val name: String = "",
    val description: String = "",
    val rating: Float = 0f,
    val preparationTime: Int = 0,
    val cookingTime: Int = 0,
    val status: RecipeStatus = RecipeStatus.Rejected,
    val image: String? = "",
    var isLiked: Boolean = false,
)
