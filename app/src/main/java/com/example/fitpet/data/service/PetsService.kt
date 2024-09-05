package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.SearchPetBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetsService {
    @GET(Endpoints.Pets.SEARCH)
    suspend fun searchDetailBreed(
        @Query("keyword") keyword: String
    ): ApiResponse<SearchPetBreedResponse>
}