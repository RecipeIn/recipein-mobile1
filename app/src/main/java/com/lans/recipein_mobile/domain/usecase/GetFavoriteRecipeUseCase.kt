package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavoriteRecipeUseCase {
    suspend fun invoke(userId: Int): Flow<Resource<List<Int>>>
}