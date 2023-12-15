package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GetCategoryByIdUseCase {
    suspend fun invoke(categoryId: Int): Flow<Resource<Category>>
}