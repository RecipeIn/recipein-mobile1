package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.repository.IFavoriteRepository
import com.lans.recipein_mobile.domain.usecase.GetFavoriteRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoriteRecipeInteractor @Inject constructor(
    private val favoriteRepository: IFavoriteRepository
) : GetFavoriteRecipeUseCase {
    override suspend fun invoke(userId: Int): Flow<Resource<List<Int>>> {
        return favoriteRepository.getAllFavorite(userId).flowOn(Dispatchers.IO)
    }
}