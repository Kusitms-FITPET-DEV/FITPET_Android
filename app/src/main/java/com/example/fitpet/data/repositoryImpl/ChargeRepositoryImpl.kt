package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.ChargeRepository
import com.example.fitpet.data.service.ChargeService
import com.example.fitpet.model.request.ChargeUploadRequest
import com.example.fitpet.model.response.ChargeUploadResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChargeRepositoryImpl @Inject constructor(
    private val chargeService: ChargeService
): ChargeRepository {

    override suspend fun uploadChargeImg(request: ChargeUploadRequest): Flow<Result<ChargeUploadResponse>> = flow {
        emit(
            runCatching {
                val response = chargeService.uploadChargeImg(request)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("charge Img Upload Failed -> ${response.status}")
                }
            }
        )
    }
}