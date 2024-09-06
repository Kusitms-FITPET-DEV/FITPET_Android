package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.TokenProvider
import com.example.fitpet.data.service.AuthService
import com.example.fitpet.model.request.ReissueTokenRequest
import com.example.fitpet.model.response.LoginResponse
import javax.inject.Inject

class TokenProviderImpl @Inject constructor(
    private val authService: AuthService,
) : TokenProvider {
    override suspend fun refreshTokens(refreshToken: String): LoginResponse? {
        val response = authService.reissueToken(ReissueTokenRequest(refreshToken))
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}