package com.lans.recipein_mobile.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.usecase.GetCategoriesUseCase
import com.lans.recipein_mobile.domain.usecase.GetFavoriteRecipeUseCase
import com.lans.recipein_mobile.domain.usecase.GetProfileUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeByCategoryIdUseCase
import com.lans.recipein_mobile.domain.usecase.InsertFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRecipeByCategoryIdUseCase: GetRecipeByCategoryIdUseCase,
    private val getFavoriteRecipeUseCase: GetFavoriteRecipeUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUIState())
    val state: Flow<HomeUIState> get() = _state

    init {
        loadProfile()
        loadCategories()
        loadWeeklyFoods()
        loadWeeklyDrinks()
        loadNewRecipes()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getProfileUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data.firstName.isNotBlank()) {
                            _state.value = _state.value.copy(name = result.data.firstName)
                        } else {
                            _state.value = _state.value.copy(name = result.data.username)
                        }
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

    fun insertFavorite(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteRecipeUseCase.invoke(_state.value.userId, recipeId)
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
                        _state.value = _state.value.copy(categories = emptyList())
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun loadWeeklyFoods() {
        viewModelScope.launch {
            getRecipeByCategoryIdUseCase.invoke(6)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(weeklyFoods = result.data)
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
                            _state.value = _state.value.copy(categories = emptyList())
                            _state.value = _state.value.copy(isLoading = false)
                        }
                    }
                }
        }
    }

    private fun loadWeeklyDrinks() {
        viewModelScope.launch {
            getRecipeByCategoryIdUseCase.invoke(7)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(weeklyDrinks = result.data)
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
                            _state.value = _state.value.copy(categories = emptyList())
                            _state.value = _state.value.copy(isLoading = false)
                        }
                    }
                }
        }
    }

    private fun loadNewRecipes() {
        viewModelScope.launch {
            getRecipeByCategoryIdUseCase.invoke(15)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(newRecipes = result.data)
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
                            _state.value = _state.value.copy(categories = emptyList())
                            _state.value = _state.value.copy(isLoading = false)
                        }
                    }
                }
        }
    }
}