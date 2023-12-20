package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.UserNutrition
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.UpdateUserNutritionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateUserNutritionInteractor @Inject() constructor(
    private val userRepository: IUserRepository
) : UpdateUserNutritionUseCase {
    override suspend fun invoke(userNutrition: UserNutrition): Flow<Resource<Boolean>> {
        return userRepository.updateUserNutrition(userNutrition).flowOn(Dispatchers.IO)
    }
}