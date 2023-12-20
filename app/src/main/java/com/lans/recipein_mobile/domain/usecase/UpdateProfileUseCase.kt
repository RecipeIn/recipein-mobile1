package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UpdateProfileUseCase {
    suspend fun invoke(user: User): Flow<Resource<Boolean>>
}