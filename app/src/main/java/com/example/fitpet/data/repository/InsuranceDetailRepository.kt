package com.example.fitpet.data.repository

import com.example.fitpet.model.response.CompensationResponse
import kotlinx.coroutines.flow.Flow

interface InsuranceDetailRepository {

    suspend fun getInsuranceDetail(petId: Int): Flow<Result<CompensationResponse>>
}