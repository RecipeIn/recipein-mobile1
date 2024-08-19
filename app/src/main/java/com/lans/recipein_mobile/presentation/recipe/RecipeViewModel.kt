package com.lans.recipein_mobile.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.usecase.GetCategoriesUseCase
import com.lans.recipein_mobile.domain.usecase.GetProfileUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeByCategoryIdUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipesUseCase
import com.lans.recipein_mobile.domain.usecase.InsertFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRecipeByCategoryIdUseCase: GetRecipeByCategoryIdUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RecipeUIState())
    val state: Flow<RecipeUIState> get() = _state

    init {
        loadProfile()
        loadRecipes()
        loadCategories()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getProfileUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(userId = result.data.id)
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

    fun insertFavorite(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteRecipeUseCase.invoke(_state.value.userId, recipeId)
        }
    }

    fun loadRecipes() {
        viewModelScope.launch {
            getRecipesUseCase.invoke(true).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(recipes = result.data)
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
                        _state.value = _state.value.copy(recipes = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }

    fun loadRecipesByCategoryId(categoryId: Int) {
        viewModelScope.launch {
            getRecipeByCategoryIdUseCase.invoke(categoryId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(recipes = result.data)
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
                        _state.value = _state.value.copy(recipes = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(categories = result.data)
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
                        _state.value = _state.value.copy(recipes = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }
}