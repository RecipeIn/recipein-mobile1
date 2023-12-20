package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.RecipeNutrition
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import com.lans.recipein_mobile.domain.usecase.GetRecipeNutritionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeNutritionsInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository
) : GetRecipeNutritionsUseCase {
    override suspend fun invoke(): Flow<Resource<List<RecipeNutrition>>> {
        return recipeRepository.getRecipeNutritions().flowOn(Dispatchers.IO)
    }
}