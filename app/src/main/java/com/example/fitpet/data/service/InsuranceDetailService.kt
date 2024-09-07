package com.example.fitpet.data.service

import androidx.datastore.preferences.protobuf.Api
import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.CompensationResponse
import com.example.fitpet.model.response.InsuranceDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface InsuranceDetailService {

    @GET("${Endpoints.Insurance.COMPENSATIONS}/{petId}")
    suspend fun checkCompensation(
        @Path("petId") petId: Int
    ): ApiResponse<CompensationResponse>

    @GET("${Endpoints.Insurance.INSURANCES}/{petId}")
    suspend fun getDetailInsurance(
        @Path("petId") petId: Int
    ): ApiResponse<InsuranceDetailResponse>
}