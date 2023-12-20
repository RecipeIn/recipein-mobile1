package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.repository.IAuthRepository
import com.lans.recipein_mobile.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
    private val authRepository: IAuthRepository,
) : SignUpUseCase {
    override suspend fun invoke(params: Auth): Flow<Resource<Boolean>> {
        return authRepository.signup(params).flowOn(Dispatchers.IO)
    }
}