package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.model.Token
import com.lans.recipein_mobile.domain.repository.IAuthRepository
import com.lans.recipein_mobile.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : SignInUseCase {
    override suspend fun invoke(params: Auth): Flow<Resource<Token>> {
        return authRepository.signin(params).flowOn(Dispatchers.IO)
    }
}