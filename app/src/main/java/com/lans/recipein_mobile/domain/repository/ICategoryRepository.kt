package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun getAll(): Flow<Resource<List<Category>>>
    suspend fun getByID(categoryId: Int): Flow<Resource<Category?>>
}