package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.UserNutrition
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.InsertUserNutritionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertUserNutritionInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : InsertUserNutritionUseCase {
    override suspend fun invoke(userNutrition: UserNutrition): Flow<Resource<Boolean>> {
        return userRepository.insertUserNutrition(userNutrition).flowOn(Dispatchers.IO)
    }
}