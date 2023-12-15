package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    suspend fun getAll(): Flow<Resource<List<Recipe>>>

}