package com.example.fitpet.data.repository

import com.example.fitpet.model.domain.FaqType
import com.example.fitpet.model.response.FaqItemVo
import com.example.fitpet.model.response.HospitalRecordItem
import com.example.fitpet.model.response.PetLifeHomeResponse
import kotlinx.coroutines.flow.Flow

interface PetLifeRepository {
    suspend fun getPetLifeHome(): Flow<Result<PetLifeHomeResponse>>
    suspend fun getHospitalRecordList(): Flow<Result<List<HospitalRecordItem>>>
    suspend fun getFaqList(keyword: FaqType): Flow<Result<List<FaqItemVo>>>
}