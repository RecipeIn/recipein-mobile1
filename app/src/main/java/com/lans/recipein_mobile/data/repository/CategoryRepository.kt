package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.data.source.network.SafeApiCall
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.data.source.network.dto.toDomain
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: RecipeInApi,
) : ICategoryRepository, SafeApiCall {
    override suspend fun getAll(): Flow<Resource<List<Category>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getCategories()
                if (response.status == 200) {
                    response.data?.map { category ->
                        category.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun getByID(categoryId: Int): Flow<Resource<Category?>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getCategoryById(categoryId)
                if (response.status == 200) {
                    response.data?.toDomain()
                } else {
                    throw Exception()
                }
            })
        }
    }
}