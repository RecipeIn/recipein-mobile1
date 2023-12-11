package com.lans.recipein_mobile.domain.repository

import com.lans.recipein_mobile.common.Resource
import com.lans.recipein_mobile.domain.model.Auth
import com.lans.recipein_mobile.domain.model.Token
import com.lans.recipein_mobile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun signin(auth: Auth): Flow<Resource<Token>>
    suspend fun signup(auth: Auth): Flow<Resource<Boolean>>
}