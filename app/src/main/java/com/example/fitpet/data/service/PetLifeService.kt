package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.domain.FaqType
import com.example.fitpet.model.response.FaqItemVo
import com.example.fitpet.model.response.HospitalRecordItem
import com.example.fitpet.model.response.PetLifeHomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PetLifeService {
    @GET(Endpoints.PetLife.HOME)
    suspend fun getPetLifeHome(): ApiResponse<PetLifeHomeResponse>

    @GET(Endpoints.PetLife.JOURNAL)
    suspend fun getHospitalRecordList(): ApiResponse<List<HospitalRecordItem>>

    @GET(Endpoints.PetLife.QUESTIONS)
    suspend fun getFaqList(
        @Query("keyword") keyword: String
    ): ApiResponse<List<FaqItemVo>>
}