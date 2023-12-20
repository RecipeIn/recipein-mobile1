package com.lans.recipein_mobile.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lans.recipein_mobile.data.source.local.dao.FavoriteDao
import com.lans.recipein_mobile.data.source.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}