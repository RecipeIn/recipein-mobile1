package com.lans.recipein_mobile.data.source.network.dto

import retrofit2.http.Query

data class UpdateNutritionResponseDto(
    @Query("status") val status: Int
)