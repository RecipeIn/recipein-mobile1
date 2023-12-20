package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.UserNutrition
import kotlinx.coroutines.flow.Flow

interface InsertUserNutritionUseCase {
    suspend fun invoke(userNutrition: UserNutrition): Flow<Resource<Boolean>>
}