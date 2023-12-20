package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GetRecipeByIdUseCase {
    suspend fun invoke(recipeId: Int): Flow<Resource<Recipe?>>
}