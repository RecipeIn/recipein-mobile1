package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.domain.model.RecipeIngredient
import com.lans.recipein_mobile.domain.model.RecipeInstruction
import com.lans.recipein_mobile.domain.model.RecipeNutrition
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    suspend fun getAll(): Flow<Resource<List<Recipe>>>
    suspend fun getById(recipeId: Int): Flow<Resource<Recipe?>>
    suspend fun getRecipeIngredients(): Flow<Resource<List<RecipeIngredient>>>
    suspend fun getRecipeInstructions(): Flow<Resource<List<RecipeInstruction>>>
    suspend fun getRecipeNutritions(): Flow<Resource<List<RecipeNutrition>>>
}