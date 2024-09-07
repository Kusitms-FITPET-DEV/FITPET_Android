package com.example.fitpet.data.repository

import com.example.fitpet.model.request.RegisterPetRequest
import com.example.fitpet.model.response.EstimateList

import com.example.fitpet.model.response.GetPetInsuranceResponse
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import com.example.fitpet.model.response.SearchPetBreedResponse
import kotlinx.coroutines.flow.Flow

interface PetsRepository {
    suspend fun searchDetailBreed(request: String): Flow<Result<SearchPetBreedResponse>>
    suspend fun registerPet(request: RegisterPetRequest): Flow<Result<Unit>>
    suspend fun getPetMainInfo(priceRate: String, petId: Int): Flow<Result<PetInsuranceResponse>>
    suspend fun getPetAllMainInfo(): Flow<Result<PetResponse>>
    suspend fun getPetInsuranceInfo(petId: Int, priceId: Int): Flow<Result<GetPetInsuranceResponse>>
}