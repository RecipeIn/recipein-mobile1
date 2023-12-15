package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    suspend fun invoke(): Flow<Resource<List<Category>>>
}