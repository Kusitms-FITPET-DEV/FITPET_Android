package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.CompensationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface InsuranceDetailService {

    @GET("${Endpoints.Compensation.COMPENSATIONS}/{petId}")
    suspend fun checkCompensation(
        @Path("petId") petId: Int
    ): ApiResponse<CompensationResponse>
}