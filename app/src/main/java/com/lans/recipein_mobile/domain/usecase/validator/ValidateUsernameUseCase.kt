package com.lans.recipein_mobile.domain.usecase.validator

import com.lans.recipein_mobile.domain.model.ValidationResult

interface ValidateUsernameUseCase {
    fun invoke(input: String): ValidationResult
}