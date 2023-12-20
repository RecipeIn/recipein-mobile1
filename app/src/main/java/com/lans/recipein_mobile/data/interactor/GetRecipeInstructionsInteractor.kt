package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.RecipeInstruction
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import com.lans.recipein_mobile.domain.usecase.GetRecipeInstructionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeInstructionsInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository
) : GetRecipeInstructionsUseCase {
    override suspend fun invoke(): Flow<Resource<List<RecipeInstruction>>> {
        return recipeRepository.getRecipeInstructions().flowOn(Dispatchers.IO)
    }
}