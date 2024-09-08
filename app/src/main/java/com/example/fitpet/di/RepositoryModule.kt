package com.example.fitpet.di

import com.example.fitpet.data.repository.AuthRepository
import com.example.fitpet.data.repository.PetLifeRepository
import com.example.fitpet.data.repository.ChargeRepository
import com.example.fitpet.data.repository.InsuranceDetailRepository
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.data.repositoryImpl.AuthRepositoryImpl
import com.example.fitpet.data.repositoryImpl.PetLifeRepositoryImpl
import com.example.fitpet.data.repositoryImpl.ChargeRepositoryImpl
import com.example.fitpet.data.repositoryImpl.InsuranceDetailRepositoryImpl
import com.example.fitpet.data.repositoryImpl.PetsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providesAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun providesPetsRepository(repositoryImpl: PetsRepositoryImpl): PetsRepository

    @Singleton
    @Binds
    abstract fun providesPetLifeRepository(repositoryImpl: PetLifeRepositoryImpl): PetLifeRepository

    @Singleton
    @Binds
    abstract fun providesChargeUploadRepository(repositoryImpl: ChargeRepositoryImpl): ChargeRepository

    @Singleton
    @Binds
    abstract fun proveidesInsuranceDetailRepository(repositoryImpl: InsuranceDetailRepositoryImpl): InsuranceDetailRepository
}