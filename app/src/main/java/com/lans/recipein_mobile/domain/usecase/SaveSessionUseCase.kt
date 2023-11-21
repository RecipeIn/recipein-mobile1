package com.lans.recipein_mobile.domain.usecase

interface SaveSessionUseCase {
    suspend fun invoke(params: String)
}