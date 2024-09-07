package com.example.fitpet.data.repository

import com.example.fitpet.model.request.ChargeInsuranceRequest
import com.example.fitpet.model.response.ChargeUploadResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface ChargeRepository {

    suspend fun uploadChargeImg(
        receiptPart: MultipartBody.Part,
        medicalExpensesPart: MultipartBody.Part,
        etcPart: MultipartBody.Part?
    ): Flow<Result<ChargeUploadResponse>>

    suspend fun chargeInsurance(petId: Int, request: ChargeInsuranceRequest): Flow<Result<Unit>>
}