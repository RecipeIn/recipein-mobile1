package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender
import com.lans.recipein_mobile.domain.model.User
import com.squareup.moshi.Json

data class ProfileResponseDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "password")
    val password: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "first_name")
    val firstName: String?,
    @field:Json(name = "last_name")
    val lastName: String?,
    @field:Json(name = "gender")
    val gender: Gender?,
    @field:Json(name = "age")
    val age: Int?,
    @field:Json(name = "height")
    val height: Int?,
    @field:Json(name = "weight")
    val weight: Int?,
    @field:Json(name = "activity")
    val activity: Activity?,
    @field:Json(name = "avatar")
    val avatar: String?,
)

fun ProfileResponseDto.toDomain() = User(
    id = id,
    username = username,
    password = password,
    email = email,
    firstName = firstName ?: "",
    lastName = lastName ?: "",
    gender = gender ?: Gender.MALE,
    age = age ?: 0,
    height = height ?: 0,
    weight = weight ?: 0,
    activity = activity ?: Activity.LOW,
    avatar = avatar ?: ""
)