package com.lans.recipein_mobile.data.source.network.api

import com.lans.recipein_mobile.data.source.network.dto.SignInRequestDto
import com.lans.recipein_mobile.data.source.network.dto.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RecipeInApi {
    @POST("/api/login")
    suspend fun signin(
        @Body requestBody: SignInRequestDto,
    ): SignInResponseDto
}