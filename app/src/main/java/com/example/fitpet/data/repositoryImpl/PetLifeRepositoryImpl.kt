package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.PetLifeRepository
import com.example.fitpet.data.service.PetLifeService
import com.example.fitpet.model.response.PetLifeHomeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PetLifeRepositoryImpl @Inject constructor(
    private val petLifeService: PetLifeService
) : PetLifeRepository {
    override suspend fun getPetLifeHome(): Flow<Result<PetLifeHomeResponse>> = flow {
        emit(
            kotlin.runCatching {
                val response = petLifeService.getPetLifeHome()

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("GetPetLifeHome Failed ${response.status}")
                }
            }
        )
    }
}