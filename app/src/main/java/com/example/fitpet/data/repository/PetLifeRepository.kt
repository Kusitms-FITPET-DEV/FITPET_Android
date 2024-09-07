package com.example.fitpet.data.repository

import com.example.fitpet.model.response.PetLifeHomeResponse
import kotlinx.coroutines.flow.Flow

interface PetLifeRepository {
    suspend fun getPetLifeHome(): Flow<Result<PetLifeHomeResponse>>
}