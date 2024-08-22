package com.example.fitpet.di

import android.content.Context
import com.example.fitpet.data.FitPetDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesFitPetDataStore(@ApplicationContext context: Context): FitPetDataStore {
        return FitPetDataStore(context)
    }
}