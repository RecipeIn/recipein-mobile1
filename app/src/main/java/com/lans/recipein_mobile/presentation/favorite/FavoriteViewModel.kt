package com.lans.recipein_mobile.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.usecase.GetFavoriteRecipeUseCase
import com.lans.recipein_mobile.domain.usecase.GetProfileUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getFavoriteRecipeUseCase: GetFavoriteRecipeUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteUIState())
    val state: Flow<FavoriteUIState> get() = _state

    init {
        loadProfile()
        loadRecipes()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getProfileUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
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

    private fun loadRecipes() {
        viewModelScope.launch {
            getRecipesUseCase.invoke(false).collect { result ->
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

                    else -> Unit
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