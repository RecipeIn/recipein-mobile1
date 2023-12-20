package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.RecipeIngredient
import kotlinx.coroutines.flow.Flow

interface GetRecipeIngredientsUseCase {
    suspend fun invoke(): Flow<Resource<List<RecipeIngredient>>>
}