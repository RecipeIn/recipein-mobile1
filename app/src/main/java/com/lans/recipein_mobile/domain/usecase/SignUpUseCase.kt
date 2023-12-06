package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {
    suspend fun invoke(params: Auth): Flow<Resource<Boolean>>
}