package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.R
import com.example.fitpet.data.repository.AuthRepository
import com.example.fitpet.data.service.AuthService
import com.example.fitpet.model.request.LoginRequest
import com.example.fitpet.model.request.ReissueTokenRequest
import com.example.fitpet.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {
    override suspend fun kakaoLogin(request: LoginRequest): Flow<Result<LoginResponse>> = flow {
        emit(
            kotlin.runCatching {
                val response = authService.kakaoLogin(request)
                if (response.isSuccessful) {
                    response.body() ?: throw NullPointerException("Response Body가 null입니다")
                } else {
                    throw HttpException(response)
                }
            }
        )
    }

    override suspend fun logout(): Flow<Result<Unit>> = flow {
        emit(
            kotlin.runCatching {
                val response = authService.logout()
                if (response.isSuccessful) {
                    response.body() ?: throw NullPointerException("Response Body가 null입니다")
                } else {
                    throw HttpException(response)
                }
            }
        )
    }

    override suspend fun reissueToken(request: ReissueTokenRequest): Flow<Result<LoginResponse>> = flow {
        emit(
            kotlin.runCatching {
                val response = authService.reissueToken(request)
                if (response.isSuccessful) {
                    response.body() ?: throw NullPointerException("Response Body가 null입니다")
                } else {
                    throw HttpException(response)
                }
            }
        )
    }
}