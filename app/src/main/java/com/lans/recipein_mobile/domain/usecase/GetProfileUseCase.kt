package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase {
    suspend fun invoke(): Flow<Resource<User>>
}