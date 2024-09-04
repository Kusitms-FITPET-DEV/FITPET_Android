package com.example.fitpet.model.request

data class LoginRequest(
    val accessToken: String,
    val accessTokenExpiresAt: String,
    val refreshToken: String,
    val refreshTokenExpiresAt: String,
    val idToken: String
)
