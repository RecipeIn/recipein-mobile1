package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.model.UserNutrition
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun isLoggedIn(): Flow<Boolean>
    suspend fun getProfile(): Flow<Resource<User>>
    suspend fun updateProfile(user: User) : Flow<Resource<Boolean>>
    suspend fun getUserNutrition(userId: Int): Flow<Resource<UserNutrition?>>
    suspend fun insertUserNutrition(userNutrition: UserNutrition) : Flow<Resource<Boolean>>
    suspend fun updateUserNutrition(userNutrition: UserNutrition) : Flow<Resource<Boolean>>
    suspend fun storeSession(userId: Int, accessToken: String, refreshToken: String)
    suspend fun clearEmail()
}