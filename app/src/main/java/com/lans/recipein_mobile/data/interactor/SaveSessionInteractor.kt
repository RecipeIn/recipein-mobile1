package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.SaveSessionUseCase
import javax.inject.Inject

class SaveSessionInteractor @Inject constructor(
    private val userRepository: IUserRepository
): SaveSessionUseCase {
    override suspend fun invoke(params: String) {
        userRepository.storeEmail(params)
    }
}