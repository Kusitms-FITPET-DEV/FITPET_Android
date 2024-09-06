package com.example.fitpet.data.repository

import com.example.fitpet.model.response.LoginResponse

interface TokenProvider {
    suspend fun refreshTokens(refreshToken: String): LoginResponse?
}