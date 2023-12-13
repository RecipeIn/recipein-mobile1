package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.data.source.network.SafeApiCall
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.data.source.network.dto.toDomain
import com.lans.recipein_mobile.domain.model.Recipe
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
                val response = api.getRecipe()
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
}