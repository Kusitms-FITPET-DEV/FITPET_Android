package com.example.fitpet.data.service

import com.example.fitpet.model.request.LoginRequest
import com.example.fitpet.model.request.ReissueTokenRequest
import com.example.fitpet.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST(Endpoints.Auth.LOGIN)
    suspend fun kakaoLogin(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST(Endpoints.Auth.LOGOUT)
    suspend fun logout(): Response<Unit>

    @POST(Endpoints.Auth.REFRESH)
    suspend fun reissueToken(
        @Body request: ReissueTokenRequest
    ): Response<LoginResponse>
}