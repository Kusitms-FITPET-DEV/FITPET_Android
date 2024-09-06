package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.request.ChargeUploadRequest
import com.example.fitpet.model.response.ChargeUploadResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChargeService {

    @POST(Endpoints.Charge.UPLOAD)
    suspend fun uploadChargeImg(
        @Body request: ChargeUploadRequest
    ): ApiResponse<ChargeUploadResponse>
}