package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.ChargeUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChargeService {

    @Multipart
    @POST(Endpoints.Charge.UPLOAD)
    suspend fun uploadChargeImg(
//        @Body request: ChargeUploadRequest
        @Part receipt: MultipartBody.Part,
        @Part medicalExpenses: MultipartBody.Part,
        @Part etc: MultipartBody.Part?
    ): ApiResponse<ChargeUploadResponse>
}