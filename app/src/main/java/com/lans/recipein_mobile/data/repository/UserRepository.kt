package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.data.source.local.DataStoreManager
import com.lans.recipein_mobile.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val dataStoreManager: DataStoreManager,
) : IUserRepository {
    override suspend fun isLoggedIn(): Flow<Boolean> {
        return flow {
            dataStoreManager.userId.collect { userId ->
                emit(userId != 0)
            }
        }
    }

    override suspend fun storeSession(userId: Int, accessToken: String, refreshToken: String) {
        dataStoreManager.storeData(DataStoreManager.USER_ID, userId)
        dataStoreManager.storeData(DataStoreManager.ACCESS_TOKEN, accessToken)
        dataStoreManager.storeData(DataStoreManager.REFRESH_TOKEN, refreshToken)
    }

    override suspend fun clearEmail() {
        dataStoreManager.clear()
    }
}