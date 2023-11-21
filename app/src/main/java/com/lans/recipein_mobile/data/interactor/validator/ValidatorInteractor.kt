package com.lans.recipein_mobile.data.interactor.validator

import com.lans.recipein_mobile.domain.usecase.validator.ValidateEmailUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidatePasswordUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidatorUseCase
import javax.inject.Inject

class ValidatorInteractor @Inject constructor(
    override val email: ValidateEmailUseCase,
    override val password: ValidatePasswordUseCase
) : ValidatorUseCase