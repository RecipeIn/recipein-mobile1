package com.lans.recipein_mobile.presentation.profile.profile_signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.model.UserNutrition
import com.lans.recipein_mobile.domain.usecase.GetProfileUseCase
import com.lans.recipein_mobile.domain.usecase.GetUserNutritionUseCase
import com.lans.recipein_mobile.domain.usecase.InsertUserNutritionUseCase
import com.lans.recipein_mobile.domain.usecase.UpdateProfileUseCase
import com.lans.recipein_mobile.domain.usecase.UpdateUserNutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSignInViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getUserNutritionUseCase: GetUserNutritionUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val insertUserNutritionUseCase: InsertUserNutritionUseCase,
    private val updateUserNutritionUseCase: UpdateUserNutritionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileSignInUIState())
    val state: Flow<ProfileSignInUIState> get() = _state

    init {
        loadProfile()
    }

    suspend fun updateProfileAndNutrition(user: User, userNutrition: UserNutrition) {
        val upsert =
            if (_state.value.profile!!.userNutrition != null) updateUserNutritionUseCase.invoke(
                userNutrition
            ) else insertUserNutritionUseCase.invoke(userNutrition)
        combine(
            updateProfileUseCase.invoke(user),
            upsert
        ) { updateProfile, updateUserNutrition ->
            Pair(updateProfile, updateUserNutrition)
        }.collect {
            val updateProfile = it.first
            val updateUserNutrition = it.second
            when (updateProfile) {
                is Resource.Success -> {
                    when (updateUserNutrition) {
                        is Resource.Success -> {
                            _state.value =
                                _state.value.copy(isUpdated = updateProfile.data && updateUserNutrition.data)
                            _state.value = _state.value.copy(isLoading = false)
                            loadProfile()
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(error = updateUserNutrition.message)
                            _state.value = _state.value.copy(isLoading = false)
                        }

                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }

                        else -> Unit
                    }
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = updateProfile.message)
                    _state.value = _state.value.copy(isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                else -> Unit
            }
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getProfileUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(profile = result.data)
                        loadUserNutrition(result.data.id)
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

    private fun loadUserNutrition(userId: Int) {
        viewModelScope.launch {
            getUserNutritionUseCase.invoke(userId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val profile = _state.value.profile!!.copy(userNutrition = result.data)
                        _state.value = _state.value.copy(profile = profile)
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