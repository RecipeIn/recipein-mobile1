package com.lans.recipein_mobile.domain.usecase

import com.lans.recipein_mobile.domain.model.Token

interface SaveSessionUseCase {
    suspend fun invoke(params: Token)
}