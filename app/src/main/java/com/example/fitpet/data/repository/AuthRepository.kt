package com.example.fitpet.data.repository

import com.example.fitpet.model.request.LoginRequest
import com.example.fitpet.model.request.ReissueTokenRequest
import com.example.fitpet.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun kakaoLogin(request: LoginRequest): Flow<Result<LoginResponse>>
    suspend fun logout(): Flow<Result<Unit>>
    suspend fun reissueToken(request: ReissueTokenRequest): Flow<Result<LoginResponse>>
}