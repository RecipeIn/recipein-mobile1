package com.lans.recipein_mobile.domain.usecase

interface InsertFavoriteRecipeUseCase {
    suspend fun invoke(userId: Int, recipeId: Int)
}