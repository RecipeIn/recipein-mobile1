package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.UserNutrition
import kotlinx.coroutines.flow.Flow

interface GetUserNutritionUseCase {
    suspend fun invoke(userId: Int): Flow<Resource<UserNutrition?>>
}