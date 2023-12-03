package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.data.source.local.DataStoreManager
import com.lans.recipein_mobile.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val dataStoreManager: DataStoreManager
): IUserRepository {
    override suspend fun isLoggedIn(): Flow<Boolean> {
        return flow {
            dataStoreManager.email.collect { email ->
                emit(email.isNotBlank())
            }
        }
    }

    override suspend fun storeEmail(email: String) {
        dataStoreManager.storeData(DataStoreManager.EMAIL, email)
    }

    override suspend fun clearEmail() {
        dataStoreManager.clear()
    }
}