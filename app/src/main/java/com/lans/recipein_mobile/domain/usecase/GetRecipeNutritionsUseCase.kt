package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.RecipeNutrition
import kotlinx.coroutines.flow.Flow

interface GetRecipeNutritionsUseCase {
    suspend fun invoke(): Flow<Resource<List<RecipeNutrition>>>
}