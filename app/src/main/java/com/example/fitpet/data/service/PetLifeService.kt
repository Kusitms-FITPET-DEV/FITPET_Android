package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.HospitalRecordItem
import com.example.fitpet.model.response.PetLifeHomeResponse
import retrofit2.http.GET

interface PetLifeService {
    @GET(Endpoints.PetLife.HOME)
    suspend fun getPetLifeHome(): ApiResponse<PetLifeHomeResponse>

    @GET(Endpoints.PetLife.JOURNAL)
    suspend fun getHospitalRecordList(): ApiResponse<List<HospitalRecordItem>>
}