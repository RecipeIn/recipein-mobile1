package com.lans.recipein_mobile.domain.model

import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender

data class User(
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
    val avatar: String,
)
