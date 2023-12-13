package com.lans.recipein_mobile.data.source.network.api

import com.lans.recipein_mobile.data.source.network.dto.CategoryListResponseDto
import com.lans.recipein_mobile.data.source.network.dto.CategoryResponseDto
import com.lans.recipein_mobile.data.source.network.dto.RecipeListResponseDto
import com.lans.recipein_mobile.data.source.network.dto.SignInRequestDto
import com.lans.recipein_mobile.data.source.network.dto.SignInResponseDto
import com.lans.recipein_mobile.data.source.network.dto.SignUpRequestDto
import com.lans.recipein_mobile.data.source.network.dto.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RecipeInApi {
    @POST("login")
    suspend fun signin(
        @Body requestBody: SignInRequestDto,
    ): SignInResponseDto

    @POST("signup")
    suspend fun signup(
        @Body requestBody: SignUpRequestDto,
    ): SignUpResponseDto

    @GET("category")
    suspend fun getCategory(): CategoryListResponseDto

    @GET("recipe")
    suspend fun getRecipe(): RecipeListResponseDto
}