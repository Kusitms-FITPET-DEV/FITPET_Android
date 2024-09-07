package com.example.fitpet.data.repository

import com.example.fitpet.model.response.CompensationResponse
import com.example.fitpet.model.response.InsuranceDetailResponse
import kotlinx.coroutines.flow.Flow

interface InsuranceDetailRepository {

    suspend fun getInsuranceCompensation(petId: Int): Flow<Result<CompensationResponse>>

    suspend fun getInsuranceDetail(petId: Int): Flow<Result<InsuranceDetailResponse>>
}