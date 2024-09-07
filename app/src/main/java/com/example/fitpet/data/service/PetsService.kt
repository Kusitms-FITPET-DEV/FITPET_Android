package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.request.RegisterPetRequest
import com.example.fitpet.model.response.EstimateList
import com.example.fitpet.model.response.GetPetInsuranceResponse
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import com.example.fitpet.model.response.SearchPetBreedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PetsService {
    @GET(Endpoints.Pets.SEARCH)
    suspend fun searchDetailBreed(
        @Query("keyword") keyword: String
    ): ApiResponse<SearchPetBreedResponse>

    @POST(Endpoints.Pets.PETS)
    suspend fun registerPet(
        @Body request: RegisterPetRequest
    ): ApiResponse<Unit>

    @GET("${Endpoints.Pets.PETS}/{petId}")
    suspend fun getPetMainInfo(
        @Path("petId") petId: Int,
        @Query("priceRate") priceRate: String
    ): ApiResponse<PetInsuranceResponse>

    @GET(Endpoints.Pets.PETS)
    suspend fun getPetAllMainInfo(
    ): ApiResponse<PetResponse>

    @GET("${Endpoints.Pets.PETS}/{petId}/{priceId}")
    suspend fun getPetInsuranceInfo(
        @Path("petId") petId: Int,
        @Path("priceId") priceId: Int,
    ): ApiResponse<GetPetInsuranceResponse>
}