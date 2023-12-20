package com.lans.recipein_mobile.data.source.network

import com.lans.recipein_mobile.common.Constants.BASE_URL
import com.lans.recipein_mobile.data.source.local.DataStoreManager
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.data.source.network.dto.RefreshTokenDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : Authenticator, SafeApiCall {
    override fun authenticate(route: Route?, response: Response): Request {
        val token = runBlocking {
            dataStoreManager.refreshToken.first()
        }
        return runBlocking {
            val newToken = refreshToken(token)
            if (newToken.status != 200) {
                dataStoreManager.clear()
            }
            newToken.let {
                dataStoreManager.storeData(DataStoreManager.ACCESS_TOKEN, it.accessToken)
                dataStoreManager.storeData(DataStoreManager.REFRESH_TOKEN, it.refreshToken)
                dataStoreManager.storeData(
                    DataStoreManager.TOKEN_EXPIRED_IN,
                    System.currentTimeMillis() + (60 * 60 * 100)
                )
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun refreshToken(refreshToken: String): RefreshTokenDto {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(RecipeInApi::class.java)
        return service.refreshToken("Bearer $refreshToken")
    }
}