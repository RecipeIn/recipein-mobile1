package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun isLoggedIn(): Flow<Boolean>
    suspend fun storeEmail(email: String)
    suspend fun clearEmail()
}