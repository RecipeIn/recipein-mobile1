package com.lans.recipein_mobile.data.repository

import com.lans.recipein_mobile.data.source.network.dto.UserResponseDto
import com.lans.recipein_mobile.data.source.network.dto.toDomain
import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository : IAuthRepository {
    val dummyData = mutableListOf(
        UserResponseDto(
            1,
            "user1",
            "user1234",
            "user1@gmail.com",
            "User1Firstname",
            "User1Lastname",
            Gender.MALE,
            18,
            160,
            54,
            Activity.LOW,
            "user1.jpg"
        ),
        UserResponseDto(
            2,
            "user2",
            "pass5678",
            "user2@gmail.com",
            "User2Firstname",
            "User2Lastname",
            Gender.FEMALE,
            25,
            170,
            65,
            Activity.MEDIUM,
            "user2.jpg"
        ),
        UserResponseDto(
            3,
            "user3",
            "qwerty",
            "user3@gmail.com",
            "User3Firstname",
            "User3Lastname",
            Gender.MALE,
            30,
            175,
            70,
            Activity.HIGH,
            "user3.jpg"
        )
    )

    override suspend fun signin(auth: Auth): Flow<User?> {
        return flow {
            val found = dummyData.firstOrNull { user ->
                auth.email == user.email && auth.password == user.password
            }

            if(found != null) {
                emit(found.toDomain())
            }
            else {
                emit(null)
            }
        }
    }
}