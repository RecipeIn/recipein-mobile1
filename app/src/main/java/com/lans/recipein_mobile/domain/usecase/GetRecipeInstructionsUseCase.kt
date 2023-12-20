package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.RecipeInstruction
import kotlinx.coroutines.flow.Flow

interface GetRecipeInstructionsUseCase {
    suspend fun invoke(): Flow<Resource<List<RecipeInstruction>>>
}