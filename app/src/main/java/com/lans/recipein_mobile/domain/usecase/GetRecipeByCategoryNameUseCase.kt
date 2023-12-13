package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GetRecipeByCategoryNameUseCase {
    suspend fun invoke(name: String): Flow<Resource<List<Recipe>>>
}