package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.CheckSessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CheckSessionInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : CheckSessionUseCase {
    override suspend fun invoke(): Flow<Resource<Boolean>> {
        return flow {
            try {
                userRepository.isLoggedIn().collect { user ->
                    emit(Resource.Success(user))
                }
            } catch (ex: Exception) {
                emit(Resource.Error(ex.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}