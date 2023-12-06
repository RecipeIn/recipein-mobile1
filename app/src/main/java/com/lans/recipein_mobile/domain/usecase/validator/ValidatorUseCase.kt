package com.lans.recipein_mobile.domain.usecase.validator

interface ValidatorUseCase {
    val email: ValidateEmailUseCase
    val username: ValidateUsernameUseCase
    val password: ValidatePasswordUseCase
    val confirmPassword: ValidateConfirmPasswordUseCase
}