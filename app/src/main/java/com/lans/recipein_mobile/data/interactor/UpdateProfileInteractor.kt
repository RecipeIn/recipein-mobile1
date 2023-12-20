package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.UpdateProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateProfileInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : UpdateProfileUseCase {
    override suspend fun invoke(user: User): Flow<Resource<Boolean>> {
        return userRepository.updateProfile(user).flowOn(Dispatchers.IO)
    }
}