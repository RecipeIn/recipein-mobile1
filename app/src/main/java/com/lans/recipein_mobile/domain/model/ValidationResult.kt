package com.lans.recipein_mobile.domain.model

data class ValidationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)
