package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.InsuranceDetailRepository
import com.example.fitpet.data.service.InsuranceDetailService
import com.example.fitpet.model.response.CompensationResponse
import com.example.fitpet.model.response.InsuranceDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsuranceDetailRepositoryImpl @Inject constructor(
    private val insuranceDetailService: InsuranceDetailService
): InsuranceDetailRepository {

    override suspend fun getInsuranceCompensation(petId: Int): Flow<Result<CompensationResponse>> = flow {
        emit(
            runCatching {
                val response = insuranceDetailService.checkCompensation(petId)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("Charge Insurance Compensation Failed -> ${response.status}")
                }
            }
        )
    }

    override suspend fun getInsuranceDetail(petId: Int): Flow<Result<InsuranceDetailResponse>> = flow {
        emit(
            runCatching {
                val response = insuranceDetailService.getDetailInsurance(petId)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("Charge Insurance Detail Failed -> ${response.status}")
                }
            }
        )
    }
}