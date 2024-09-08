package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.PetLifeRepository
import com.example.fitpet.data.service.PetLifeService
import com.example.fitpet.model.domain.FaqType
import com.example.fitpet.model.response.FaqItemVo
import com.example.fitpet.model.response.HospitalRecordItem
import com.example.fitpet.model.response.PetLifeHomeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PetLifeRepositoryImpl @Inject constructor(
    private val petLifeService: PetLifeService
) : PetLifeRepository {
    override suspend fun getPetLifeHome(): Flow<Result<PetLifeHomeResponse>> = flow {
        emit(
            kotlin.runCatching {
                val response = petLifeService.getPetLifeHome()

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("GetPetLifeHome Failed ${response.status}")
                }
            }
        )
    }

    override suspend fun getHospitalRecordList(): Flow<Result<List<HospitalRecordItem>>> = flow {
        emit(
            kotlin.runCatching {
                val response = petLifeService.getHospitalRecordList()

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("GetHospitalRecordList Failed ${response.status}")
                }
            }
        )
    }

    override suspend fun getFaqList(keyword: FaqType): Flow<Result<List<FaqItemVo>>> = flow {
        emit(
            kotlin.runCatching {
                val response = petLifeService.getFaqList(keyword.name)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("GetFaqList Failed ${response.status}")
                }
            }
        )
    }
}