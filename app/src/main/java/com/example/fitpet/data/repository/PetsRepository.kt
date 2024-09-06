package com.example.fitpet.data.repository

import com.example.fitpet.model.request.RegisterPetRequest
import com.example.fitpet.model.response.SearchPetBreedResponse
import kotlinx.coroutines.flow.Flow

interface PetsRepository {
    suspend fun searchDetailBreed(request: String): Flow<Result<SearchPetBreedResponse>>
    suspend fun registerPet(request: RegisterPetRequest): Flow<Result<Unit>>
}