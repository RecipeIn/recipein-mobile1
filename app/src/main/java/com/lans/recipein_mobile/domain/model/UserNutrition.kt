package com.lans.recipein_mobile.domain.model

data class UserNutrition(
    val id: Int = 0,
    val userId: Int = 0,
    val calories: Int = 0,
    val protein: Int = 0,
    val carbohydrate: Int = 0,
    val fiber: Int = 0,
    val fat: Int = 0,
)