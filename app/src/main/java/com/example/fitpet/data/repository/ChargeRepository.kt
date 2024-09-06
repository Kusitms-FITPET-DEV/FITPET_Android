package com.example.fitpet.data.repository

import com.example.fitpet.model.request.ChargeUploadRequest
import com.example.fitpet.model.response.ChargeUploadResponse
import kotlinx.coroutines.flow.Flow

interface ChargeRepository {

    suspend fun uploadChargeImg(request: ChargeUploadRequest): Flow<Result<ChargeUploadResponse>>
}