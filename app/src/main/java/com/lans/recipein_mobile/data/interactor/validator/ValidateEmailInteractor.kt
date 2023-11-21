package com.lans.recipein_mobile.data.interactor.validator

import android.util.Patterns
import com.lans.recipein_mobile.domain.model.ValidationResult
import com.lans.recipein_mobile.domain.usecase.validator.ValidateEmailUseCase

class ValidateEmailInteractor: ValidateEmailUseCase {
    override fun invoke(input: String): ValidationResult {
        if(input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Email must be filled"
            )
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Email address is not valid"
            )
        }

        return ValidationResult(
            isSuccess = true
        )
    }
}