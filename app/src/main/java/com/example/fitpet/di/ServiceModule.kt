package com.example.fitpet.di

import com.example.fitpet.data.service.AuthService
import com.example.fitpet.data.service.PetLifeService
import com.example.fitpet.data.service.ChargeService
import com.example.fitpet.data.service.InsuranceDetailService
import com.example.fitpet.data.service.PetsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun providesAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun providesPetsService(retrofit: Retrofit): PetsService {
        return retrofit.create(PetsService::class.java)
    }

    @Provides
    fun providesPetLifeService(retrofit: Retrofit): PetLifeService {
        return retrofit.create(PetLifeService::class.java)
    }

    @Provides
    fun providesChargeService(retrofit: Retrofit): ChargeService {
        return retrofit.create(ChargeService::class.java)
    }

    @Provides
    fun providesInsuranceDetailService(retrofit: Retrofit): InsuranceDetailService {
        return retrofit.create(InsuranceDetailService::class.java)
    }
}