package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProfileInteractor @Inject constructor(
    private val userRepository: IUserRepository
): GetProfileUseCase {
    override suspend fun invoke(): Flow<Resource<User>> {
        return userRepository.getProfile().flowOn(Dispatchers.IO)
    }
}