package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import com.lans.recipein_mobile.domain.usecase.GetRecipeByCategoryIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeByCategoryIdInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository,
) : GetRecipeByCategoryIdUseCase {
    override suspend fun invoke(categoryId: Int): Flow<Resource<List<Recipe>>> {
        return flow {
            recipeRepository.getAll().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data.isNotEmpty()) {
                            emit(Resource.Success(result.data.filter { it.category!!.id == categoryId }))
                        } else {
                            emit(Resource.Empty)
                        }
                    }

                    else -> emit(result)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}