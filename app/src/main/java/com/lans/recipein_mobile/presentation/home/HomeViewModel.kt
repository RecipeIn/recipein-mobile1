package com.lans.recipein_mobile.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.usecase.GetCategoriesUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeByCategoryIdUseCase
import com.lans.recipein_mobile.domain.usecase.GetRecipeByCategoryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRecipeByCategoryIdUseCase: GetRecipeByCategoryIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUIState())
    val state: Flow<HomeUIState> get() = _state

    init {
        loadCategories()
        loadWeeklyFoods()
        loadWeeklyDrinks()
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
}