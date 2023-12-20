package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.UserNutrition
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.GetUserNutritionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserNutritionInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : GetUserNutritionUseCase {
    override suspend fun invoke(userId: Int): Flow<Resource<UserNutrition?>> {
        return userRepository.getUserNutrition(userId).flowOn(Dispatchers.IO)
    }
}