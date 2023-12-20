package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import com.lans.recipein_mobile.domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipesInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository,
) : GetRecipesUseCase {
    override suspend fun invoke(hideSomeCategories: Boolean): Flow<Resource<List<Recipe>>> {
        return flow {
            recipeRepository.getAll().collect { recipe ->
                when (recipe) {
                    is Resource.Success -> {
                        if (recipe.data.isNotEmpty()) {
                            if (hideSomeCategories) {
                                emit(Resource.Success(recipe.data.filter {
                                    !it.category!!.name.contains(
                                        "Rekomendasi"
                                    )
                                }))
                            } else {
                                emit(Resource.Success(recipe.data))
                            }
                        } else {
                            emit(Resource.Empty)
                        }
                    }

                    else -> emit(recipe)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}