package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {
    suspend fun invoke(params: Auth): Flow<Resource<User>>
}