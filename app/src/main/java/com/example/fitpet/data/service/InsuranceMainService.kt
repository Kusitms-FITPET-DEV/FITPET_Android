package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.SearchPetBreedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface InsuranceMainService {
    @GET(Endpoints.Pets.SEARCH)
    suspend fun searchPetInsurance(
        @Query("keyword") keyword: String
    ): ApiResponse<SearchPetBreedResponse>
}