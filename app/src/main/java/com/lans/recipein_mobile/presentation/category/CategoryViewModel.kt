package com.lans.recipein_mobile.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.usecase.GetCategoryByIdUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeByCategoryIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val getRecipeByCategoryIdUseCase: GetRecipeByCategoryIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CategoryUIState())
    val state: Flow<CategoryUIState> get() = _state

    fun loadCategory(categoryId: Int) {
        viewModelScope.launch {
            getCategoryByIdUseCase.invoke(categoryId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(category = result.data)
                        loadRecipe(categoryId)
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

    private fun loadRecipe(categoryId: Int) {
        viewModelScope.launch {
            getRecipeByCategoryIdUseCase.invoke(categoryId).collect { result ->
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
}