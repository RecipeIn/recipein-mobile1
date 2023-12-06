package com.lans.recipein_mobile.domain.repository

import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun isLoggedIn(): Flow<Boolean>
    suspend fun storeSession(userId: Int, accessToken: String, refreshToken: String)
    suspend fun clearEmail()
}