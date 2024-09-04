package com.example.fitpet.data.service

import com.example.fitpet.model.request.ReissueTokenRequest
import com.example.fitpet.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @GET(Endpoints.Auth.LOGIN)
    suspend fun kakaoLogin(
        @Query("code") code: String
    ): Response<LoginResponse>

    @POST(Endpoints.Auth.LOGOUT)
    suspend fun logout(): Response<Unit>

    @POST(Endpoints.Auth.REFRESH)
    suspend fun reissueToken(
        @Body request: ReissueTokenRequest
    ): Response<LoginResponse>
}