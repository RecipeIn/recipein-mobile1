package com.lans.recipein_mobile.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lans.recipein_mobile.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    fun create(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM FavoriteEntity WHERE user_id == :userId")

    fun getAllFavorite(userId: Int): Flow<List<FavoriteEntity>?>

    @Query("DELETE FROM FavoriteEntity WHERE recipe_id = :recipeId")
    fun delete(recipeId: Int)
}