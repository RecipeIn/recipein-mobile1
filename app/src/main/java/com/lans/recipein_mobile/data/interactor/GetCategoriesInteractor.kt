package com.lans.recipein_mobile.data.interactor

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.repository.ICategoryRepository
import com.lans.recipein_mobile.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCategoriesInteractor @Inject constructor(
    private val categoryRepository: ICategoryRepository,
) : GetCategoriesUseCase {
    override suspend fun invoke(): Flow<Resource<List<Category>>> {
        return flow {
            categoryRepository.getAll().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data.isNotEmpty()) {
                            emit(Resource.Success(result.data.filter { !it.name.contains("Rekomendasi") }))
                        } else {
                            emit(Resource.Empty)
                        }
                    }

                    else -> emit(result)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}