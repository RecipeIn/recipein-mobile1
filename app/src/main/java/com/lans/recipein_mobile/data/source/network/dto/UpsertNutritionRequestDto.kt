package com.lans.recipein_mobile.data.source.network.dto

data class UpsertNutritionRequestDto(
    val user_id: Int,
    val calories: Int,
    val protein: Int,
    val carbohydrate: Int,
    val vitamin: Int = 0,
    val fiber: Int,
    val fat: Int,
)
