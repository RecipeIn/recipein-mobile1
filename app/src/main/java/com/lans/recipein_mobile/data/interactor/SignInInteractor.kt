package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.repository.IAuthRepository
import com.lans.recipein_mobile.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : SignInUseCase {
    override suspend fun invoke(params: Auth): Flow<Resource<User>> {
        return flow {
            try {
                authRepository.signin(Auth(params.email, params.password)).collect { user ->
                    if (user != null) {
                        emit(Resource.Success(user))
                    } else {
                        emit(Resource.Error("Username or password was wrong"))
                    }
                }
            } catch (ex: Exception) {
                emit(Resource.Error(ex.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}