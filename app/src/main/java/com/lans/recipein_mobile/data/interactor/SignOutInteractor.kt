package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.SignOutUseCase
import javax.inject.Inject

class SignOutInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : SignOutUseCase {
    override suspend fun invoke() {
        userRepository.clearEmail()
    }
}