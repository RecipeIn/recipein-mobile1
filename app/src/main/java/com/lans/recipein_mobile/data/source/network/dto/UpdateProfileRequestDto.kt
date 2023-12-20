package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender
import retrofit2.http.Query

data class UpdateProfileRequestDto(
    val username: String?,
    val password: String?,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val gender: Gender?,
    val age: Int?,
    val height: Int?,
    val weight: Int?,
    val activity: Activity?,
    val avatar: String?,
)
