package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.data.source.network.dto.UserResponseDto
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun signin(auth: Auth): Flow<User?>
}