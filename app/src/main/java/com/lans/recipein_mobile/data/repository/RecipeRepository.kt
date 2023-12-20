package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.data.source.network.SafeApiCall
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.data.source.network.dto.toDomain
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.model.RecipeIngredient
import com.lans.recipein_mobile.domain.model.RecipeInstruction
import com.lans.recipein_mobile.domain.model.RecipeNutrition
import com.lans.recipein_mobile.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: RecipeInApi,
) : IRecipeRepository, SafeApiCall {
    override suspend fun getAll(): Flow<Resource<List<Recipe>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getRecipes()
                if (response.status == 200) {
                    response.data?.map { recipe ->
                        recipe.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun getById(recipeId: Int): Flow<Resource<Recipe?>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getRecipe(recipeId)
                if (response.status == 200) {
                    response.data?.toDomain()
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun getRecipeIngredients(): Flow<Resource<List<RecipeIngredient>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getRecipeIngredients()
                if (response.status == 200) {
                    response.data?.map { recipeIngredient ->
                        recipeIngredient.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun getRecipeInstructions(): Flow<Resource<List<RecipeInstruction>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getRecipeInstructions()
                if (response.status == 200) {
                    response.data?.map { recipeInstruction ->
                        recipeInstruction.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun getRecipeNutritions(): Flow<Resource<List<RecipeNutrition>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getRecipeNutritions()
                if (response.status == 200) {
                    response.data?.map { recipeNutrition ->
                        recipeNutrition.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception()
                }
            })
        }
    }
}
