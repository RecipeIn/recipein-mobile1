package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.data.source.local.DataStoreManager
import com.lans.recipein_mobile.data.source.network.SafeApiCall
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.data.source.network.dto.UpdateProfileRequestDto
import com.lans.recipein_mobile.data.source.network.dto.UpsertNutritionRequestDto
import com.lans.recipein_mobile.data.source.network.dto.toDomain
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.model.UserNutrition
import com.lans.recipein_mobile.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val api: RecipeInApi,
    private val dataStoreManager: DataStoreManager,
) : IUserRepository, SafeApiCall {
    override suspend fun isLoggedIn(): Flow<Boolean> {
        return flow {
            dataStoreManager.accessToken.collect { token ->
                emit(token != "")
            }
        }
    }

    override suspend fun getProfile(): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading)
            emit(
                safeCall {
                val response = api.getProfile()
                if (response.status == 200) {
                    response.user!!.toDomain()
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun updateProfile(user: User): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.updateProfile(
                    UpdateProfileRequestDto(
                        username = user.username,
                        password = user.password,
                        email = user.email,
                        first_name = user.firstName,
                        last_name = user.lastName,
                        gender = user.gender,
                        age = user.age,
                        height = user.height,
                        weight = user.weight,
                        activity = user.activity,
                        avatar = user.avatar
                    )
                ).status == 200
            })
        }
    }

    override suspend fun getUserNutrition(userId: Int): Flow<Resource<UserNutrition?>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getUserNutrition(userId)
                if (response.status == 200) {
                    if (response.data?.isNotEmpty() == true) {
                        response.data[0].toDomain()
                    } else {
                        null
                    }
                } else {
                    throw Exception()
                }
            })
        }
    }

    override suspend fun insertUserNutrition(userNutrition: UserNutrition): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.insertUserNutrition(
                    UpsertNutritionRequestDto(
                        user_id = userNutrition.userId,
                        calories = userNutrition.calories,
                        protein = userNutrition.protein,
                        carbohydrate = userNutrition.carbohydrate,
                        fiber = userNutrition.fiber,
                        fat = userNutrition.fat
                    )
                ).status == 201
            })
        }
    }

    override suspend fun updateUserNutrition(userNutrition: UserNutrition): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.updateUserNutrition(
                    userNutrition.id,
                    UpsertNutritionRequestDto(
                        user_id = userNutrition.userId,
                        calories = userNutrition.calories,
                        protein = userNutrition.protein,
                        carbohydrate = userNutrition.carbohydrate,
                        fiber = userNutrition.fiber,
                        fat = userNutrition.fat
                    )
                ).status == 200
            })
        }
    }

    override suspend fun storeSession(userId: Int, accessToken: String, refreshToken: String) {
        dataStoreManager.storeData(DataStoreManager.USER_ID, userId)
        dataStoreManager.storeData(DataStoreManager.ACCESS_TOKEN, accessToken)
        dataStoreManager.storeData(DataStoreManager.REFRESH_TOKEN, refreshToken)
        dataStoreManager.storeData(
            DataStoreManager.TOKEN_EXPIRED_IN,
            System.currentTimeMillis() + (60 * 60 * 100)
        )
    }

    override suspend fun clearEmail() {
        dataStoreManager.clear()
    }
}