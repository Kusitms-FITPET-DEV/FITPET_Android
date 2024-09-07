package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.request.ChargeInsuranceRequest
import com.example.fitpet.model.response.ChargeUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ChargeService {

    @Multipart
    @POST(Endpoints.Charge.UPLOAD)
    suspend fun uploadChargeImg(
        @Part receipt: MultipartBody.Part,
        @Part medicalExpenses: MultipartBody.Part,
        @Part etc: MultipartBody.Part?
    ): ApiResponse<ChargeUploadResponse>

    @POST("${Endpoints.Charge.CHARGE}/{petId}")
    suspend fun chargeInsurance(
        @Path("petId") petId: Int,
        @Body request: ChargeInsuranceRequest
    ): ApiResponse<Unit>
}