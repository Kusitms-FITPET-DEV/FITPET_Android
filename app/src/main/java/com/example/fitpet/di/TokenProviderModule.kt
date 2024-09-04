package com.example.fitpet.di

import com.example.fitpet.data.FitPetDataStore
import com.example.fitpet.data.repository.TokenProvider
import com.example.fitpet.data.repositoryImpl.TokenProviderImpl
import com.example.fitpet.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenProviderModule {

    @Singleton
    @Provides
    fun provideTokenProvider(
        authService: AuthService
    ): TokenProvider {
        return TokenProviderImpl(authService)
    }
}