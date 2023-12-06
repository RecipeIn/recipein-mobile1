package com.lans.recipein_mobile.di

import android.content.Context
import com.lans.recipein_mobile.common.Constants.BASE_URL
import com.lans.recipein_mobile.data.interactor.CheckSessionInteractor
import com.lans.recipein_mobile.data.interactor.SaveSessionInteractor
import com.lans.recipein_mobile.data.interactor.SignInInteractor
import com.lans.recipein_mobile.data.interactor.SignOutInteractor
import com.lans.recipein_mobile.data.interactor.validator.ValidateEmailInteractor
import com.lans.recipein_mobile.data.interactor.validator.ValidatePasswordInteractor
import com.lans.recipein_mobile.data.interactor.validator.ValidatorInteractor
import com.lans.recipein_mobile.data.repository.AuthRepository
import com.lans.recipein_mobile.data.repository.UserRepository
import com.lans.recipein_mobile.data.source.local.DataStoreManager
import com.lans.recipein_mobile.data.source.network.api.RecipeInApi
import com.lans.recipein_mobile.domain.repository.IAuthRepository
import com.lans.recipein_mobile.domain.repository.IUserRepository
import com.lans.recipein_mobile.domain.usecase.CheckSessionUseCase
import com.lans.recipein_mobile.domain.usecase.SaveSessionUseCase
import com.lans.recipein_mobile.domain.usecase.SignInUseCase
import com.lans.recipein_mobile.domain.usecase.SignOutUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidateEmailUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidatePasswordUseCase
import com.lans.recipein_mobile.domain.usecase.validator.ValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeInApi(client: OkHttpClient): RecipeInApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: RecipeInApi): IAuthRepository {
        return AuthRepository(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(dataStoreManager: DataStoreManager): IUserRepository {
        return UserRepository(dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: IAuthRepository): SignInUseCase {
        return SignInInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun provideCheckSessionUseCase(userRepository: IUserRepository): CheckSessionUseCase {
        return CheckSessionInteractor(userRepository)
    }

    @Provides
    @Singleton
    fun provideSaveSessionUseCase(userRepository: IUserRepository): SaveSessionUseCase {
        return SaveSessionInteractor(userRepository)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(userRepository: IUserRepository): SignOutUseCase {
        return SignOutInteractor(userRepository)
    }

    @Provides
    @Singleton
    fun provideValidatorUseCase(
        validateEmailUseCase: ValidateEmailUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase,
    ): ValidatorUseCase {
        return ValidatorInteractor(
            validateEmailUseCase,
            validatePasswordUseCase
        )
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailInteractor()
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase {
        return ValidatePasswordInteractor()
    }
}