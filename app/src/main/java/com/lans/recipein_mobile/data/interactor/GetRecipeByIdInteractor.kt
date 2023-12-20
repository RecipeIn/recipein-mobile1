package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import com.lans.recipein_mobile.domain.usecase.GetRecipeByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeByIdInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository
) : GetRecipeByIdUseCase {
    override suspend fun invoke(recipeId: Int): Flow<Resource<Recipe?>> {
        return recipeRepository.getById(recipeId).flowOn(Dispatchers.IO)
    }
}