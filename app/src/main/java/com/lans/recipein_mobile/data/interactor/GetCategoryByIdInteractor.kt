package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.repository.ICategoryRepository
import com.lans.recipein_mobile.domain.usecase.GetCategoryByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCategoryByIdInteractor @Inject constructor(
    private val categoryRepository: ICategoryRepository
) : GetCategoryByIdUseCase {
    override suspend fun invoke(categoryId: Int): Flow<Resource<Category?>> {
        return categoryRepository.getByID(categoryId).flowOn(Dispatchers.IO)
    }
}