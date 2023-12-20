package com.lans.recipein_mobile.domain.model

import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender

data class User(
    val id: Int = 0,
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: Gender = Gender.MALE,
    val age: Int = 0,
    val height: Int = 0,
    val weight: Int = 0,
    val activity: Activity = Activity.LOW,
    val avatar: String = "",
    var userNutrition: UserNutrition? = null
)
