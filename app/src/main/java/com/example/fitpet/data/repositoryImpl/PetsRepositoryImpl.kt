package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.data.service.PetsService
import com.example.fitpet.model.request.RegisterPetRequest
import com.example.fitpet.model.response.SearchPetBreedResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class PetsRepositoryImpl @Inject constructor(
    private val petsService: PetsService
): PetsRepository {
    override suspend fun searchDetailBreed(request: String): Flow<Result<SearchPetBreedResponse>> = flow {
        emit(
            kotlin.runCatching {
                val response = petsService.searchDetailBreed(request)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("SearchDetailBreed Failed ${response.status}")
                }
            }
        )
    }

    override suspend fun registerPet(request: RegisterPetRequest): Flow<Result<Unit>> = flow {
        emit(
            kotlin.runCatching {
                val response = petsService.registerPet(request)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("RegisterPet Failed ${response.status}")
                }
            }
        )
    }
}