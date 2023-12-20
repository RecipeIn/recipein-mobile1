package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.RecipeIngredient
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import com.lans.recipein_mobile.domain.usecase.GetRecipeIngredientsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeIngredientsInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository
) : GetRecipeIngredientsUseCase {
    override suspend fun invoke(): Flow<Resource<List<RecipeIngredient>>> {
        return recipeRepository.getRecipeIngredients().flowOn(Dispatchers.IO)
    }
}