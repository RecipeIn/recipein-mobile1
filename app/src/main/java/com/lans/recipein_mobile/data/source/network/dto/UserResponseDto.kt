package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender
import com.lans.recipein_mobile.domain.model.User

data class UserResponseDto(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Int,
    val height: Int,
    val weight: Int,
    val activity: Activity,
    val avatar: String
)

fun UserResponseDto.toDomain(): User {
    return User(
        id,
        username,
        password,
        email,
        firstName,
        lastName,
        gender,
        age,
        height,
        weight,
        activity,
        avatar
    )
}
