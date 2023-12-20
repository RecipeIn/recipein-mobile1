package com.lans.recipein_mobile.data.source.network.api

import com.lans.recipein_mobile.data.source.network.dto.ApiResponse
import com.lans.recipein_mobile.data.source.network.dto.CategoryResponseDto
import com.lans.recipein_mobile.data.source.network.dto.ProfileResponseDto
import com.lans.recipein_mobile.data.source.network.dto.RecipeIngredientResponseDto
import com.lans.recipein_mobile.data.source.network.dto.RecipeInstructionResponseDto
import com.lans.recipein_mobile.data.source.network.dto.RecipeNutritionResponseDto
import com.lans.recipein_mobile.data.source.network.dto.RecipeResponseDto
import com.lans.recipein_mobile.data.source.network.dto.RefreshTokenDto
import com.lans.recipein_mobile.data.source.network.dto.SignInRequestDto
import com.lans.recipein_mobile.data.source.network.dto.SignInResponseDto
import com.lans.recipein_mobile.data.source.network.dto.SignUpRequestDto
import com.lans.recipein_mobile.data.source.network.dto.SignUpResponseDto
import com.lans.recipein_mobile.data.source.network.dto.UpdateNutritionResponseDto
import com.lans.recipein_mobile.data.source.network.dto.UpdateProfileRequestDto
import com.lans.recipein_mobile.data.source.network.dto.UpsertNutritionRequestDto
import com.lans.recipein_mobile.data.source.network.dto.UserNutritionResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("refresh")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): RefreshTokenDto

    @GET("profile")
    suspend fun getProfile(): ApiResponse<ProfileResponseDto>

    @PUT("update-profile")
    suspend fun updateProfile(
        @Body requestBody: UpdateProfileRequestDto,
    ): ApiResponse<Boolean>

    @GET("user-nutrition/user/{id}")
    suspend fun getUserNutrition(
        @Path("id") userId: Int,
    ): ApiResponse<List<UserNutritionResponseDto>>

    @POST("user-nutrition")
    suspend fun insertUserNutrition(
        @Body requestBody: UpsertNutritionRequestDto,
    ): UpdateNutritionResponseDto

    @PUT("user-nutrition/{id}")
    suspend fun updateUserNutrition(
        @Path("id") id: Int,
        @Body requestBody: UpsertNutritionRequestDto,
    ): UpdateNutritionResponseDto

    @GET("category")
    suspend fun getCategories(): ApiResponse<List<CategoryResponseDto>>

    @GET("category/{id}")
    suspend fun getCategoryById(
        @Path("id") id: Int
    ): ApiResponse<CategoryResponseDto>

    @GET("recipe")
    suspend fun getRecipes(): ApiResponse<List<RecipeResponseDto>>

    @GET("recipe-ingredient")
    suspend fun getRecipeIngredients(): ApiResponse<List<RecipeIngredientResponseDto>>

    @GET("recipe-instruction")
    suspend fun getRecipeInstructions(): ApiResponse<List<RecipeInstructionResponseDto>>

    @GET("recipe-nutrition")
    suspend fun getRecipeNutritions(): ApiResponse<List<RecipeNutritionResponseDto>>

    @GET("recipe/{id}")
    suspend fun getRecipe(
        @Path("id") recipeId: Int,
    ): ApiResponse<RecipeResponseDto>
}