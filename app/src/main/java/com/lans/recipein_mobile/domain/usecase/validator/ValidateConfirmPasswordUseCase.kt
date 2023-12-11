package com.lans.recipein_mobile.domain.usecase.validator

import com.lans.recipein_mobile.domain.model.ValidationResult

interface ValidateConfirmPasswordUseCase {
    fun invoke(password: String, confirmPassword: String): ValidationResult
}