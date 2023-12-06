package com.lans.recipein_mobile.data.interactor.validator

import com.lans.recipein_mobile.domain.model.ValidationResult
import com.lans.recipein_mobile.domain.usecase.validator.ValidateUsernameUseCase

class ValidateUsernameInteractor : ValidateUsernameUseCase {
    override fun invoke(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Username must be filled"
            )
        }

        return ValidationResult(
            isSuccess = true
        )
    }
}