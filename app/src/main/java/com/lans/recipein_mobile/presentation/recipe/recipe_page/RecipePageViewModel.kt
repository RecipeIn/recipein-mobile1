package com.lans.recipein_mobile.presentation.recipe.recipe_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.usecase.GetFavoriteRecipeUseCase
import com.lans.recipein_mobile.domain.usecase.GetProfileUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeByIdUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeIngredientsUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeInstructionsUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeNutritionsUseCase
import com.lans.recipein_mobile.domain.usecase.InsertFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipePageViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val getRecipeIngredientsUseCase: GetRecipeIngredientsUseCase,
    private val getRecipeInstructionsUseCase: GetRecipeInstructionsUseCase,
    private val getRecipeNutritionsUseCase: GetRecipeNutritionsUseCase,
    private val getFavoriteRecipeUseCase: GetFavoriteRecipeUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RecipePageUIState())
    val state: Flow<RecipePageUIState> get() = _state

    init {
        loadProfile()
        loadRecipeIngredients()
        loadRecipeInstructions()
        loadRecipeNutritions()
    }

    fun insertFavorite(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteRecipeUseCase.invoke(_state.value.userId, recipeId)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getProfileUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(userId = result.data.id)
                        loadFavoriteRecipe(result.data.id)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    else -> Unit
                }
            }
        }
    }

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            getRecipeByIdUseCase.invoke(recipeId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(recipe = result.data)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun loadRecipeIngredients() {
        viewModelScope.launch {
            getRecipeIngredientsUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(recipeIngredients = result.data)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Empty -> {
                        _state.value = _state.value.copy(recipeIngredients = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun loadRecipeInstructions() {
        viewModelScope.launch {
            getRecipeInstructionsUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(recipeInstructions = result.data)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Empty -> {
                        _state.value = _state.value.copy(recipeInstructions = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun loadRecipeNutritions() {
        viewModelScope.launch {
            getRecipeNutritionsUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(recipeNutrisions = result.data)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Empty -> {
                        _state.value = _state.value.copy(recipeNutrisions = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun loadFavoriteRecipe(userId: Int) {
        viewModelScope.launch {
            getFavoriteRecipeUseCase.invoke(userId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(favorite = result.data)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = result.message)
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    else -> Unit
                }
            }
        }
    }
}