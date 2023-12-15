package com.lans.recipein_mobile.domain.model

data class Recipe(
    val image: Int,
    val category: String,
    val recipe_tittle: String,
    val description: String,
    val author: String,
    val time: String,
)