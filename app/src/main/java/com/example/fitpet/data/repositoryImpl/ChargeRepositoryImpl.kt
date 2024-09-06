package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.ChargeRepository
import com.example.fitpet.data.service.ChargeService
import com.example.fitpet.model.request.ChargeUploadRequest
import com.example.fitpet.model.response.ChargeUploadResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class ChargeRepositoryImpl @Inject constructor(
    private val chargeService: ChargeService
): ChargeRepository {

    override suspend fun uploadChargeImg(
        receiptPart: MultipartBody.Part,
        medicalExpensesPart: MultipartBody.Part,
        etcPart: MultipartBody.Part?
    ): Flow<Result<ChargeUploadResponse>> = flow {
        emit(
            runCatching {
                val response = chargeService.uploadChargeImg(
                    receipt = receiptPart,
                    medicalExpenses = medicalExpensesPart,
                    etc = etcPart
                )

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("Charge Img Upload Failed -> ${response.status}")
                }
            }
        )
    }
}