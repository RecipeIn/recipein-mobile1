package com.lans.recipein_mobile.data.interactor.validator

import com.lans.recipein_mobile.domain.model.ValidationResult
import com.lans.recipein_mobile.domain.usecase.validator.ValidateConfirmPasswordUseCase

class ValidateConfirmPasswordInteractor : ValidateConfirmPasswordUseCase {
    override fun invoke(password: String, confirmPassword: String): ValidationResult {
        if (confirmPassword.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Confirm password must be filled"
            )
        }

        if (confirmPassword.length < 8) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Confirm password must be at least 8 characters long"
            )
        }

        if (password != confirmPassword) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Confirm password must be same as password"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}