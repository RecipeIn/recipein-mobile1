package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.domain.repository.IFavoriteRepository
import com.lans.recipein_mobile.domain.usecase.InsertFavoriteRecipeUseCase
import javax.inject.Inject

class InsertFavoriteRecipeInteractor @Inject constructor(
    private val favoriteRepository: IFavoriteRepository
) : InsertFavoriteRecipeUseCase {
    override suspend fun invoke(userId: Int, recipeId: Int) {
        favoriteRepository.create(userId, recipeId)
    }
}