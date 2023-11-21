package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import kotlinx.coroutines.flow.Flow

interface CheckSessionUseCase {
    suspend fun invoke(): Flow<Resource<Boolean>>
}