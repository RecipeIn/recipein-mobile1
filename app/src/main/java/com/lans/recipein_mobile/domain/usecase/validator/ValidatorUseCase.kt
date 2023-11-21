package com.lans.recipein_mobile.domain.usecase.validator

interface ValidatorUseCase {
    val email: ValidateEmailUseCase
    val password: ValidatePasswordUseCase
}