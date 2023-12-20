package com.lans.recipein_mobile.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.data.source.local.dao.FavoriteDao
import com.lans.recipein_mobile.data.source.local.entity.FavoriteEntity
import com.lans.recipein_mobile.data.source.network.SafeApiCall
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) : IFavoriteRepository, SafeApiCall {
    override suspend fun create(userId: Int, recipeId: Int) {
        try {
            favoriteDao.create(FavoriteEntity(0, userId, recipeId))
        } catch (exception: SQLiteConstraintException) {
            favoriteDao.delete(recipeId)
        }
    }

    override suspend fun getAllFavorite(userId: Int): Flow<Resource<List<Int>>> {
        return flow {
            emit(Resource.Loading)
            favoriteDao.getAllFavorite(userId).collect { result ->
                if (result != null) {
                    emit(Resource.Success(result.map { it.recipeId }))
                }
            }
        }
    }
}