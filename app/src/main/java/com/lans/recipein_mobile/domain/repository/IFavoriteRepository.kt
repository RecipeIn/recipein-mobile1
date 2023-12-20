package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    suspend fun create(userId: Int, recipeId: Int)
    suspend fun getAllFavorite(userId: Int): Flow<Resource<List<Int>>>
}